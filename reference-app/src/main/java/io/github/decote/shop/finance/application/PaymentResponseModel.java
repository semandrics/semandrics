package io.github.semandrics.shop.finance.application;

import java.util.UUID;

public record PaymentResponseModel(UUID paymentId, String status) {
}
