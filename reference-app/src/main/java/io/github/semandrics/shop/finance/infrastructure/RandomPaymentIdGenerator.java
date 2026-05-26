package io.github.semandrics.shop.finance.infrastructure;

import io.github.semandrics.shop.finance.domain.PaymentId;
import io.github.semandrics.shop.finance.domain.PaymentIdGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomPaymentIdGenerator implements PaymentIdGenerator {

    @Override
    public PaymentId generatePaymentId() {
        return new PaymentId(UUID.randomUUID());
    }
}
