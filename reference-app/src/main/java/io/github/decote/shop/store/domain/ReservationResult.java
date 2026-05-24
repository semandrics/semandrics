package io.github.semandrics.shop.store.domain;

import io.github.semandrics.shop.sales.domain.Result;
import java.time.Instant;

public record ReservationResult(String reservationId, Instant expiresAt) {
    public static Result<ReservationResult> success(String id, Instant expiry) {
        return Result.ok(new ReservationResult(id, expiry));
    }

    public static Result<ReservationResult> failure(String reason) {
        return Result.err(reason);
    }
}
