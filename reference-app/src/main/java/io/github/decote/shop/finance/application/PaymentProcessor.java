package io.github.semandrics.shop.finance.application;

import io.github.semandrics.CoreBoundary;
import io.github.semandrics.shop.finance.domain.Payment;
import io.github.semandrics.shop.sales.domain.Result;

@CoreBoundary
public interface PaymentProcessor {
    Result<Payment> charge(Payment payment);
}
