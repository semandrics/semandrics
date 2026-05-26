package io.github.semandrics.shop.sales.domain.order;

import io.github.semandrics.ExternalSystem;

import java.util.Optional;

@ExternalSystem(boundedContext = "Sales")
public interface OrderRepository {

    void save(Order order);
    Optional<Order> findById(OrderId id);
}


