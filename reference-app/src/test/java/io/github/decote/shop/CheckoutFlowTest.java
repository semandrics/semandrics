package io.github.decote.shop;

import io.github.decote.shop.catalog.application.CatalogueService;
import io.github.decote.shop.catalog.domain.Product;
import io.github.decote.shop.sales.application.SalesService;
import io.github.decote.shop.sales.domain.Cart;
import io.github.decote.shop.sales.domain.Order;
import io.github.decote.shop.sales.domain.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CheckoutFlowTest {

    @Autowired
    private SalesService salesService;

    @Autowired
    private CatalogueService catalogueService;

    @Test
    void shouldCompleteCheckoutSuccessfully() {
        // Arrange
        var sku = "PROD-1";
        catalogueService.defineProduct(new Product(sku, "Test Product", new BigDecimal("100.00")));

        var cart = salesService.createCart();
        salesService.addToCart(cart.id(), sku, 1);

        // Act
        var result = salesService.checkout(cart.id());

        // Assert
        assertTrue(result.isOk(), "Checkout should be successful but failed with: " + (result.isErr() ? result.getError() : ""));
        var order = result.get();
        assertEquals(Order.Status.PAID, order.status());
        assertEquals(new BigDecimal("100.00"), order.amount());
    }

    @Test
    void shouldFailCheckoutWhenStockInsufficient() {
        // Arrange
        var sku = "PROD-NO-STOCK";
        catalogueService.defineProduct(new Product(sku, "No Stock Product", new BigDecimal("50.00")));
        // Note: InMemoryWarehouseSystem doesn't have stock for this SKU by default

        var cart = salesService.createCart();
        salesService.addToCart(cart.id(), sku, 1);

        // Act
        var result = salesService.checkout(cart.id());

        // Assert
        assertTrue(result.isErr());
        assertTrue(result.getError().contains("Stock reservation failed"));
    }
}
