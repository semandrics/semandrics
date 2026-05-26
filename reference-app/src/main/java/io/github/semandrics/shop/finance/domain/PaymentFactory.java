package io.github.semandrics.shop.finance.domain;

import io.github.semandrics.shop.sales.domain.order.OrderId;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentFactory {
    private final PaymentIdGenerator paymentIdGenerator;

    public PaymentFactory(PaymentIdGenerator paymentIdGenerator) {
        this.paymentIdGenerator = paymentIdGenerator;
    }

    public Payment createPayment(OrderId orderId, BigDecimal amount) {
        var paymentId = paymentIdGenerator.generatePaymentId();
        return new Payment(paymentId, orderId, amount, Payment.Status.PENDING);
    }
}
