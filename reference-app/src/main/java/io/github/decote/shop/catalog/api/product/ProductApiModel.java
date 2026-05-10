package io.github.decote.shop.catalog.api.product;

import java.math.BigDecimal;

public record ProductApiModel(
        String sku,
        String name,
        BigDecimal price) {
}
