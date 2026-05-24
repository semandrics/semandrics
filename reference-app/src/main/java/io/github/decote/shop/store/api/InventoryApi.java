package io.github.semandrics.shop.store.api;

import io.github.semandrics.shop.store.application.WarehouseSystem;
import io.github.semandrics.shop.sales.domain.Result;
import io.github.semandrics.shop.store.domain.ReservationResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
public class InventoryApi {
    private final WarehouseSystem warehouseSystem;

    public InventoryApi(WarehouseSystem warehouseSystem) {
        this.warehouseSystem = warehouseSystem;
    }

    @PostMapping("/reserve")
    public Result<ReservationResult> reserve(@RequestBody InventoryRequest request) {
        return warehouseSystem.reserve(request.sku(), request.quantity());
    }

    public record InventoryRequest(String sku, int quantity) {}
}
