package io.github.semandrics.shop.catalog.domain;

import io.github.semandrics.CoreBoundary;

import java.util.List;
import java.util.Optional;

@CoreBoundary
public interface ProductRepository {
    List<Product> findAll();

    Optional<Product> findBySku(String sku);

    void save(Product product);
}
