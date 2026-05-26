package io.github.semandrics.shop.store.application;

import io.github.semandrics.shop.sales.domain.Result;
import io.github.semandrics.shop.store.domain.ReservationResult;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ReservationService {
    private final WarehouseSystem warehouseSystem;

    public ReservationService(WarehouseSystem warehouseSystem) {
        this.warehouseSystem = warehouseSystem;
    }

    public Result<ReservationResult> reserveStock(Map<String, Integer> items) {
        // For simplicity, we reserve each item. In a real system, we might want an atomic multi-item reservation.
        // For this reference app, we'll just reserve the first item or handle it simply.
        if (items.isEmpty()) {
            return Result.err("No items to reserve");
        }
        
        // Simplified: just reserve the first item for now to demonstrate the flow
        Map.Entry<String, Integer> first = items.entrySet().iterator().next();
        return warehouseSystem.reserve(first.getKey(), first.getValue());
    }

    public void releaseReservation(String reservationId) {
        warehouseSystem.release(reservationId);
    }

    public void commitReservation(String reservationId) {
        warehouseSystem.commit(reservationId);
    }
}
