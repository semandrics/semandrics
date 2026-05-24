package io.github.semandrics.shop.store.application;

import io.github.semandrics.shop.sales.domain.Result;
import io.github.semandrics.shop.store.domain.ReservationResult;

public interface WarehouseSystem {
    Result<ReservationResult> reserve(String sku, int quantity);
    void release(String reservationId);
    void commit(String reservationId);
}
