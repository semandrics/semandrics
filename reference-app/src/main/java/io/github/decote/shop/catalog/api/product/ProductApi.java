package io.github.semandrics.shop.catalog.api.product;

import io.github.semandrics.shop.catalog.application.CatalogueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductApi {
    private final CatalogueService catalogueService;
    private final ProductApiModelMapper productApiModelMapper;

    public ProductApi(
            CatalogueService catalogueService,
            ProductApiModelMapper productApiModelMapper) {
        this.catalogueService = catalogueService;
        this.productApiModelMapper = productApiModelMapper;
    }

    @GetMapping
    public List<ProductApiModel> getAllProducts() {
        return catalogueService.getAllProducts().stream()
                .map(productApiModelMapper::toResponseModel)
                .collect(Collectors.toList());
    }

    @GetMapping("/{sku}")
    public ProductApiModel getProduct(@PathVariable String sku) {
        return catalogueService.getProduct(sku)
                .map(productApiModelMapper::toResponseModel)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
