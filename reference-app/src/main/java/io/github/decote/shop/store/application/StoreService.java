package io.github.decote.shop.store.application;

import org.springframework.stereotype.Service;

@Service
public class StoreService {
    private final WarehouseSystem warehouseSystem;

    public StoreService(WarehouseSystem warehouseSystem) {
        this.warehouseSystem = warehouseSystem;
    }
    
    // Additional store-related logic if needed
}
