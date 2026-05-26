package io.github.semandrics.shop.sales.domain.order;

import java.math.BigDecimal;
import java.util.Map;

public record Order(OrderId id, Map<String, Integer> items, BigDecimal amount, Status status) {
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
