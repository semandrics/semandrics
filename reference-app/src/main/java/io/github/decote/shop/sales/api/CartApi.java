package io.github.decote.shop.sales.api;

import io.github.decote.shop.sales.application.SalesService;
import io.github.decote.shop.sales.domain.Cart;
import io.github.decote.shop.sales.domain.Result;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/cart")
public class CartApi {
    private final SalesService salesService;

    public CartApi(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping
    public Cart createCart() {
        return salesService.createCart();
    }

    @PostMapping("/{cartId}/items")
    public Result<Cart> addItem(@PathVariable UUID cartId, @RequestBody CartItemRequest item) {
        return salesService.addToCart(cartId, item.sku(), item.quantity());
    }

    public record CartItemRequest(String sku, int quantity) {}
}
