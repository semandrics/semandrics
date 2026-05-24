package io.github.semandrics.shop;

import io.github.semandrics.shop.sales.application.SalesService;
import io.github.semandrics.shop.sales.domain.Order;
import io.github.semandrics.shop.sales.domain.Result;
import io.github.semandrics.testing.Scenario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Map;
import java.util.UUID;

@SpringBootTest
public class CheckoutFlowScenarioTest {

    @Autowired
    private SalesService salesService;

    @Test
    void shouldCompleteCheckoutSuccessfully_usingScenario() {

        var cartId = UUID.fromString("a664bd82-9b4f-48ed-a567-b334f8715698");
        var orderId = UUID.fromString("288d3df1-e958-4a38-aa7d-f52291365929");
        var orderResult = Result.ok(new Order(orderId, Map.of("product-1-sku", 1), new BigDecimal("100.00"), Order.Status.PAID));

        Scenario.whenCalling(SalesService::checkout)
                .with(cartId)
                .expectResult(orderResult)
                .execute(salesService);
    }
}
