package io.github.semandrics.shop.catalog.domain.product;

import io.github.semandrics.EntryPoint;
import io.github.semandrics.ExternalSystem;

import java.util.List;
import java.util.Optional;

@ExternalSystem(boundedContext = "Catalog")
public interface ProductRepository {

    @EntryPoint(name = "findAllProducts", boundedContext = "Catalog")
    List<Product> findAll();

    @EntryPoint(name = "findProductBySku", boundedContext = "Catalog")
    Optional<Product> findBySku(String sku);

    void save(Product product);
}
