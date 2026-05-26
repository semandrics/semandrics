package io.github.semandrics.shop.catalog.application;

import io.github.semandrics.EntryPoints;
import io.github.semandrics.shop.catalog.domain.product.Product;
import io.github.semandrics.shop.catalog.domain.product.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@EntryPoints(boundedContext = "Catalog")
@Service
public class CatalogService {
    private final ProductRepository productRepository;

    public CatalogService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProduct(String sku) {
        return productRepository.findBySku(sku);
    }

    public void defineProduct(Product product) {
        productRepository.save(product);
    }
}
