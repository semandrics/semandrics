package io.github.decote.shop.sales.application;

import io.github.decote.CoreBoundary;
import io.github.decote.shop.sales.domain.Cart;
import java.util.Optional;
import java.util.UUID;

@CoreBoundary
public interface CartRepository {
    void save(Cart cart);
    Optional<Cart> findById(UUID id);
}
