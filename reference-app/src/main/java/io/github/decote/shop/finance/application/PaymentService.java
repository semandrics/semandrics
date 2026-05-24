package io.github.semandrics.shop.finance.application;

import io.github.semandrics.shop.finance.domain.Payment;
import io.github.semandrics.shop.finance.domain.PaymentFactory;
import io.github.semandrics.shop.sales.domain.Result;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentFactory paymentFactory;
    private final PaymentProcessor paymentProcessor;

    public PaymentService(
            PaymentFactory paymentFactory,
            PaymentProcessor paymentProcessor) {

        this.paymentFactory = paymentFactory;
        this.paymentProcessor = paymentProcessor;
    }

    public Result<Payment> processPayment(UUID orderId, BigDecimal amount) {
        var payment = paymentFactory.createPayment(orderId, amount);
        return paymentProcessor.charge(payment);
    }
}
