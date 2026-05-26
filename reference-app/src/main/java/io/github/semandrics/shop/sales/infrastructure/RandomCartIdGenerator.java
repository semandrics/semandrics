package io.github.semandrics.shop.sales.infrastructure;

import io.github.semandrics.shop.sales.domain.cart.CartId;
import io.github.semandrics.shop.sales.domain.cart.CartIdGenerator;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RandomCartIdGenerator implements CartIdGenerator {

    @Override
    public CartId generateCartId() {
        return new CartId(UUID.randomUUID());
    }
}
