package io.github.semandrics.shop.finance.application;

import io.github.semandrics.shop.finance.domain.Payment;
import io.github.semandrics.shop.finance.domain.PaymentFactory;
import io.github.semandrics.shop.sales.domain.Result;
import io.github.semandrics.shop.sales.domain.order.OrderId;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    public Result<Payment> processPayment(OrderId orderId, BigDecimal amount) {
        var payment = paymentFactory.createPayment(orderId, amount);
        return paymentProcessor.charge(payment);
    }
}
