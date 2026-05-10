package io.github.decote.shop.sales.infrastructure;

import io.github.decote.shop.sales.application.OrderRepository;
import io.github.decote.shop.sales.domain.Order;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryOrderRepository implements OrderRepository {
    private final Map<UUID, Order> orders = new ConcurrentHashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.id(), order);
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return Optional.ofNullable(orders.get(id));
    }
}
