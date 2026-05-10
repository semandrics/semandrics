package io.github.decote.shop.finance.domain;

import io.github.decote.std.random.RandomService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class PaymentFactory {
    private final RandomService randomService;

    public PaymentFactory(RandomService randomService) {
        this.randomService = randomService;
    }

    public Payment createPayment(UUID orderId, BigDecimal amount) {
        var paymentId = randomService.generateUuid();
        return new Payment(paymentId, orderId, amount, Payment.Status.PENDING);
    }
}
