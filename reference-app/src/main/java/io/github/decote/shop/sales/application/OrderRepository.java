package io.github.decote.shop.sales.application;

import io.github.decote.CoreBoundary;
import io.github.decote.shop.sales.domain.Order;
import java.util.Optional;
import java.util.UUID;

@CoreBoundary
public interface OrderRepository {
    void save(Order order);
    Optional<Order> findById(UUID id);
}
