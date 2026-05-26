package io.github.semandrics.shop.sales.infrastructure;

import io.github.semandrics.shop.sales.domain.order.Order;
import io.github.semandrics.shop.sales.domain.order.OrderId;
import io.github.semandrics.shop.sales.domain.order.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<OrderId, Order> orders = new ConcurrentHashMap<>();

    @Override
    public void save(Order order) {
        orders.put(order.id(), order);
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return Optional.ofNullable(orders.get(id));
    }
}
