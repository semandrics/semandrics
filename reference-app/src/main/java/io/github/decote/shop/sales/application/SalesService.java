package io.github.semandrics.shop.sales.application;

import io.github.semandrics.CoreEntry;
import io.github.semandrics.shop.catalog.application.CatalogueService;
import io.github.semandrics.shop.finance.application.PaymentService;
import io.github.semandrics.shop.sales.domain.Cart;
import io.github.semandrics.shop.sales.domain.Order;
import io.github.semandrics.shop.sales.domain.OrderFactory;
import io.github.semandrics.shop.sales.domain.Result;
import io.github.semandrics.shop.store.application.ReservationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@CoreEntry
@Service
public class SalesService {
    private final CartRepository cartRepository;
    private final CatalogueService catalogueService;
    private final ReservationService reservationService;
    private final OrderFactory orderFactory;
    private final OrderRepository orderRepository;
    private final PaymentService paymentService;

    public SalesService(
            CartRepository cartRepository,
            CatalogueService catalogueService,
            ReservationService reservationService,
            OrderFactory orderFactory,
            OrderRepository orderRepository,
            PaymentService paymentService) {

        this.cartRepository = cartRepository;
        this.catalogueService = catalogueService;
        this.reservationService = reservationService;
        this.orderFactory = orderFactory;
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    public Cart createCart() {
        var cart = Cart.create();
        cartRepository.save(cart);
        return cart;
    }

    public Result<Cart> addToCart(UUID cartId, String sku, int quantity) {

        var cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isEmpty()) return Result.err("Cart not found");

        var productOpt = catalogueService.getProduct(sku);
        if (productOpt.isEmpty())
            return Result.err("Product not found");

        var updatedCart = cartOpt.get().add(sku, quantity, productOpt.get().price());
        cartRepository.save(updatedCart);
        return Result.ok(updatedCart);
    }

    public Result<Order> checkout(UUID cartId) {

        var cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isEmpty())
            return Result.err("Cart not found");
        var cart = cartOpt.get();

        var priceVerification = verifyPrice(cart);
        if (priceVerification.isErr())
            return Result.err(priceVerification.getError());

        var reservationResult = reservationService.reserveStock(cart.items());
        if (reservationResult.isErr())
            return Result.err("Stock reservation failed: " + reservationResult.getError());

        var reservationId = reservationResult.get().reservationId();

        var order = orderFactory.createOrderFromCart(cart);
        var paymentRes = paymentService.processPayment(order.id(), order.amount());

        if (paymentRes.isErr()) {
            reservationService.releaseReservation(reservationId);
            order = order.cancel();
            orderRepository.save(order);
            return Result.err("Payment failed: " + paymentRes.getError());
        }

        order = order.pay();
        orderRepository.save(order);

        reservationService.commitReservation(reservationId);

        return Result.ok(order);
    }

    private Result<Void> verifyPrice(Cart cart) {

        var recalculatedPrice = cart.items().entrySet().stream()
                .map(entry -> catalogueService.getProduct(entry.getKey())
                        .map(p -> p.price().multiply(BigDecimal.valueOf(entry.getValue())))
                        .orElse(null))
                .reduce(BigDecimal.ZERO, (a, b) -> (a == null || b == null) ? null : a.add(b));

        if (recalculatedPrice == null) {
            return Result.err("One or more products in the cart no longer exist");
        }

        if (recalculatedPrice.compareTo(cart.totalPrice()) != 0) {
            return Result.err("Price mismatch. Please refresh your cart.");
        }
        return Result.ok(null);
    }
}
