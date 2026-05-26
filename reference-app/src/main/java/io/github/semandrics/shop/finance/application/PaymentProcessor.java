package io.github.semandrics.shop.finance.application;

import io.github.semandrics.ExternalSystem;
import io.github.semandrics.shop.finance.domain.Payment;
import io.github.semandrics.shop.sales.domain.Result;

@ExternalSystem(boundedContext = "Finance")
public interface PaymentProcessor {
    Result<Payment> charge(Payment payment);
}
