package io.github.decote.shop.catalog.application;

import io.github.decote.CoreEntry;
import io.github.decote.shop.catalog.domain.Product;
import io.github.decote.shop.catalog.domain.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@CoreEntry
@Service
public class CatalogueService {
    private final ProductRepository productRepository;

    public CatalogueService(ProductRepository productRepository) {
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
