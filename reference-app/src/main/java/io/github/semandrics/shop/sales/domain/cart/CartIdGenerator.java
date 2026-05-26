package io.github.semandrics.shop.sales.domain.cart;

import io.github.semandrics.ExternalSystem;

@ExternalSystem(boundedContext = "Sales")
public interface CartIdGenerator {

    CartId generateCartId();
}
