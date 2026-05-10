package io.github.decote.shop.sales.infrastructure;

import io.github.decote.shop.sales.application.CartRepository;
import io.github.decote.shop.sales.domain.Cart;
import org.springframework.stereotype.Repository;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCartRepository implements CartRepository {
    private final Map<UUID, Cart> carts = new ConcurrentHashMap<>();

    @Override
    public void save(Cart cart) {
        carts.put(cart.id(), cart);
    }

    @Override
    public Optional<Cart> findById(UUID id) {
        return Optional.ofNullable(carts.get(id));
    }
}
