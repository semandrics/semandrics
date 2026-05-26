package io.github.semandrics.shop.sales.domain.cart;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;

@Service
public class CartFactory {
    private final CartIdGenerator cartIdGenerator;

    public CartFactory(CartIdGenerator cartIdGenerator) {
        this.cartIdGenerator = cartIdGenerator;
    }

    public Cart createCart() {
        return new Cart(cartIdGenerator.generateCartId(), new HashMap<>(), new BigDecimal(0));
    }
}
