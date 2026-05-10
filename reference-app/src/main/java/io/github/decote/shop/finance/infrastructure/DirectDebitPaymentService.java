package io.github.decote.shop.finance.infrastructure;

import io.github.decote.shop.finance.application.PaymentProcessor;
import io.github.decote.shop.finance.domain.Payment;
import io.github.decote.shop.sales.domain.Result;
import org.springframework.stereotype.Service;

@Service
public class DirectDebitPaymentService implements PaymentProcessor {
    @Override
    public Result<Payment> charge(Payment payment) {
        // Simple logic: always succeed unless amount is zero or negative
        if (payment.amount().signum() <= 0) {
            return Result.err("Invalid amount for payment");
        }
        return Result.ok(payment.complete());
    }
}
