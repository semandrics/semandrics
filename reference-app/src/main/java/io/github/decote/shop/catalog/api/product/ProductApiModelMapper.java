package io.github.decote.shop.catalog.api.product;

import org.springframework.stereotype.Service;

@Service
public class ProductApiModelMapper {

    public ProductApiModel toResponseModel(io.github.decote.shop.catalog.domain.Product product) {
        return new ProductApiModel(product.sku(), product.name(), product.price());
    }
}
