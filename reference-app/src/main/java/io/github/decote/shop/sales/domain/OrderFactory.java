package io.github.semandrics.shop.sales.domain;

import io.github.semandrics.std.random.RandomService;
import org.springframework.stereotype.Service;

@Service
public class OrderFactory {
    private final RandomService randomService;

    public OrderFactory(RandomService randomService) {
        this.randomService = randomService;
    }

    public Order createOrderFromCart(Cart cart) {
        var orderId = randomService.generateUuid();
        return new Order(orderId, cart.items(), cart.totalPrice(), Order.Status.CREATED);
    }
}
