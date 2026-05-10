package io.github.decote.shop.catalog.domain;

import java.math.BigDecimal;

public record Product(String sku, String name, BigDecimal price) {
}
