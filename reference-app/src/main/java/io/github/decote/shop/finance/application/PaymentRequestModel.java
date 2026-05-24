package io.github.semandrics.shop.finance.application;

import java.math.BigDecimal;
import java.util.UUID;

public record PaymentRequestModel(UUID orderId, BigDecimal amount) {
}
