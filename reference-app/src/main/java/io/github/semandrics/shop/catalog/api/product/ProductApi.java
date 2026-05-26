package io.github.semandrics.shop.catalog.api.product;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductApi {
    private final io.github.semandrics.shop.catalog.application.CatalogService catalogService;
    private final ProductApiModelMapper productApiModelMapper;

    public ProductApi(
            io.github.semandrics.shop.catalog.application.CatalogService catalogService,
            ProductApiModelMapper productApiModelMapper) {
        this.catalogService = catalogService;
        this.productApiModelMapper = productApiModelMapper;
    }

    @GetMapping
    public List<ProductApiModel> getAllProducts() {
        return catalogService.getAllProducts().stream()
                .map(productApiModelMapper::toResponseModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{sku}")
    public ProductApiModel getProduct(@PathVariable String sku) {
        return catalogService.getProduct(sku)
                .map(productApiModelMapper::toResponseModel)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
