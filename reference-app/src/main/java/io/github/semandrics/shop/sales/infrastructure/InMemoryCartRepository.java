package io.github.semandrics.shop.sales.infrastructure;

import io.github.semandrics.shop.sales.domain.cart.Cart;
import io.github.semandrics.shop.sales.domain.cart.CartId;
import io.github.semandrics.shop.sales.domain.cart.CartRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryCartRepository implements CartRepository {

    private final Map<CartId, Cart> carts = new ConcurrentHashMap<>();

    @Override
    public void save(Cart cart) {
        carts.put(cart.id(), cart);
    }

    @Override
    public Optional<Cart> findById(CartId id) {
        return Optional.ofNullable(carts.get(id));
    }
}
