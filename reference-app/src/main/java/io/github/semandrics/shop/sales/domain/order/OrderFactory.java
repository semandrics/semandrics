package io.github.semandrics.shop.sales.domain.order;

import io.github.semandrics.shop.sales.domain.cart.Cart;
import org.springframework.stereotype.Service;

@Service
public class OrderFactory {
    private final OrderIdGenerator orderIdGenerator;

    public OrderFactory(OrderIdGenerator orderIdGenerator) {
        this.orderIdGenerator = orderIdGenerator;
    }

    public Order createOrderFromCart(Cart cart) {
        var orderId = orderIdGenerator.generateOrderId();
        return new Order(orderId, cart.items(), cart.totalPrice(), Order.Status.CREATED);
    }
}
