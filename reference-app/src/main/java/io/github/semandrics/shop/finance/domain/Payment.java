package io.github.semandrics.shop.finance.domain;

import io.github.semandrics.shop.sales.domain.order.OrderId;

import java.math.BigDecimal;

public record Payment(PaymentId id, OrderId orderId, BigDecimal amount, Status status) {

    public enum Status {
        PENDING, SUCCESS, FAILED
    }

    public Payment complete() {
        return new Payment(id, orderId, amount, Status.SUCCESS);
    }

    public Payment fail() {
        return new Payment(id, orderId, amount, Status.FAILED);
    }
}
