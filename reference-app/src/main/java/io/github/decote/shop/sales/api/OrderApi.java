package io.github.semandrics.shop.sales.api;

import io.github.semandrics.shop.sales.application.SalesService;
import io.github.semandrics.shop.sales.domain.Order;
import io.github.semandrics.shop.sales.domain.Result;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderApi {
    private final SalesService salesService;

    public OrderApi(SalesService salesService) {
        this.salesService = salesService;
    }

    @PostMapping("/{cartId}")
    public Result<Order> checkout(@PathVariable UUID cartId) {
        return salesService.checkout(cartId);
    }
}
