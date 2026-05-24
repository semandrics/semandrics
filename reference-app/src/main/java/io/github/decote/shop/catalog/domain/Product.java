package io.github.semandrics.shop.catalog.domain;

import java.math.BigDecimal;

public record Product(String sku, String name, BigDecimal price) {
}
