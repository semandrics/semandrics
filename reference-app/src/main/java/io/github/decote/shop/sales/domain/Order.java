package io.github.decote.shop.sales.domain;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

public record Order(UUID id, Map<String, Integer> items, BigDecimal amount, Status status) {
    public enum Status {
        CREATED, PAID, CANCELLED
    }

    public Order pay() {
        return new Order(id, items, amount, Status.PAID);
    }

    public Order cancel() {
        return new Order(id, items, amount, Status.CANCELLED);
    }
}
