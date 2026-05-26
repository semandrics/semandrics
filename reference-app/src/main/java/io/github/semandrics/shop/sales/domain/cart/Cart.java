package io.github.semandrics.shop.sales.domain.cart;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public record Cart(CartId id, Map<String, Integer> items, BigDecimal totalPrice) {

    public Cart add(String sku, int quantity, BigDecimal pricePerUnit) {
        var newItems = new HashMap<>(items);
        newItems.put(sku, newItems.getOrDefault(sku, 0) + quantity);
        var newTotal = totalPrice.add(pricePerUnit.multiply(BigDecimal.valueOf(quantity)));
        return new Cart(id, Collections.unmodifiableMap(newItems), newTotal);
    }
}
