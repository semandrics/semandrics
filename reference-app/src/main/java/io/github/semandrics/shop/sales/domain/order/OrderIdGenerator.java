package io.github.semandrics.shop.sales.domain.order;

import io.github.semandrics.ExternalSystem;

@ExternalSystem(boundedContext = "Sales")
public interface OrderIdGenerator {
    OrderId generateOrderId();
}
