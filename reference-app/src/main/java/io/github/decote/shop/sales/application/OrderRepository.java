package io.github.semandrics.shop.sales.application;

import io.github.semandrics.CoreBoundary;
import io.github.semandrics.shop.sales.domain.Order;
import java.util.Optional;
import java.util.UUID;

@CoreBoundary
public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(UUID id);
}
