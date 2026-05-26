package io.github.semandrics.shop.sales.domain.cart;

import io.github.semandrics.ExternalSystem;

import java.util.Optional;

@ExternalSystem(boundedContext = "Sales")
public interface CartRepository {

    void save(Cart cart);

    Optional<Cart> findById(CartId id);
}
