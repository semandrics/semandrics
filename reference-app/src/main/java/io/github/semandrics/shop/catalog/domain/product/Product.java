package io.github.semandrics.shop.catalog.domain.product;

import java.math.BigDecimal;

public record Product(String sku, String name, BigDecimal price) {
}
