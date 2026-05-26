package io.github.semandrics.shop.catalog.api.product;

import io.github.semandrics.shop.catalog.domain.product.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductApiModelMapper {

    public ProductApiModel toResponseModel(Product product) {
        return new ProductApiModel(product.sku(), product.name(), product.price());
    }
}
