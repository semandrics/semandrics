package io.github.semandrics.shop.sales.domain;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public record Cart(UUID id, Map<String, Integer> items, BigDecimal totalPrice) {
    public static Cart create() {
        return new Cart(UUID.randomUUID(), Collections.emptyMap(), BigDecimal.ZERO);
    }

    public Cart add(String sku, int quantity, BigDecimal pricePerUnit) {
        var newItems = new HashMap<>(items);
        newItems.put(sku, newItems.getOrDefault(sku, 0) + quantity);
        var newTotal = totalPrice.add(pricePerUnit.multiply(BigDecimal.valueOf(quantity)));
        return new Cart(id, Collections.unmodifiableMap(newItems), newTotal);
    }
}
