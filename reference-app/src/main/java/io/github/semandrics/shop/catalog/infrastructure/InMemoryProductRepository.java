package io.github.semandrics.shop.catalog.infrastructure;

import io.github.semandrics.shop.catalog.domain.product.Product;
import io.github.semandrics.shop.catalog.domain.product.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository implements ProductRepository {
    private final List<Product> products = new ArrayList<>();

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return products.stream().filter(p -> p.sku().equals(sku)).findFirst();
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }
}
