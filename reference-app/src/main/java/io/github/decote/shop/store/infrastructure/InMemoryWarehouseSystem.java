package io.github.semandrics.shop.store.infrastructure;

import io.github.semandrics.shop.sales.domain.Result;
import io.github.semandrics.shop.store.application.WarehouseSystem;
import io.github.semandrics.shop.store.domain.ReservationResult;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryWarehouseSystem implements WarehouseSystem {
    private final Map<String, Integer> stock = new ConcurrentHashMap<>();
    private final Map<String, Reservation> reservations = new ConcurrentHashMap<>();

    public InMemoryWarehouseSystem() {
        // Pre-fill some stock for testing
        stock.put("PROD-1", 100);
        stock.put("PROD-2", 50);
    }

    @Override
    public Result<ReservationResult> reserve(String sku, int quantity) {
        var currentStock = stock.getOrDefault(sku, 0);
        if (currentStock < quantity) {
            return Result.err("Not enough stock for " + sku);
        }

        stock.put(sku, currentStock - quantity);
        var reservationId = UUID.randomUUID().toString();
        reservations.put(reservationId, new Reservation(sku, quantity));
        
        return ReservationResult.success(reservationId, Instant.now().plus(15, ChronoUnit.MINUTES));
    }

    @Override
    public void release(String reservationId) {
        var res = reservations.remove(reservationId);
        if (res != null) {
            stock.compute(res.sku(), (k, v) -> (v == null ? 0 : v) + res.quantity());
        }
    }

    @Override
    public void commit(String reservationId) {
        reservations.remove(reservationId);
        // In this simple model, stock was already deducted at reservation.
        // Committing just means the reservation is no longer "pending".
    }

    private record Reservation(String sku, int quantity) {}
}
