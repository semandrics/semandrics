package io.github.semandrics.shop.sales.application;

import io.github.semandrics.CoreBoundary;
import io.github.semandrics.shop.sales.domain.Cart;
import java.util.Optional;
import java.util.UUID;

@CoreBoundary
public interface CartRepository {
    void save(Cart cart);
    Optional<Cart> findById(UUID id);
}
