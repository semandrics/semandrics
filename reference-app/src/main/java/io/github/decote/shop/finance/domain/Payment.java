package io.github.decote.shop.finance.domain;

import java.math.BigDecimal;
import java.util.UUID;

public record Payment(UUID id, UUID orderId, BigDecimal amount, Status status) {
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
