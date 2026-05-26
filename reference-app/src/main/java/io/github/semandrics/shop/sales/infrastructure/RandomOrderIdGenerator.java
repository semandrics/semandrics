package io.github.semandrics.shop.sales.infrastructure;

import io.github.semandrics.shop.sales.domain.order.OrderId;
import io.github.semandrics.shop.sales.domain.order.OrderIdGenerator;
import org.springframework.stereotype.Service;

@Service
public class RandomOrderIdGenerator implements OrderIdGenerator {

    @Override
    public OrderId generateOrderId() {
        return new OrderId(java.util.UUID.randomUUID());
    }
}
