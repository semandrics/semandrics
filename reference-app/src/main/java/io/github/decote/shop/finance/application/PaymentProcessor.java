package io.github.decote.shop.finance.application;

import io.github.decote.CoreBoundary;
import io.github.decote.shop.finance.domain.Payment;
import io.github.decote.shop.sales.domain.Result;

@CoreBoundary
public interface PaymentProcessor {
    Result<Payment> charge(Payment payment);
}
