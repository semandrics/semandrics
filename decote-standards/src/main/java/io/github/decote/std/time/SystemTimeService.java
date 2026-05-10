package io.github.decote.std.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class SystemTimeService implements TimeService {

    @Override
    public Instant getCurrentInstant() {
        return Instant.now();
    }

    @Override
    public LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    @Override
    public ZonedDateTime getCurrentZonedDateTime(TimeZone timeZone) {
        return ZonedDateTime.now(timeZone.toZoneId());
    }

    @Override
    public TimeZone getSystemTimeZone() {
        return TimeZone.getDefault();
    }

    @Override
    public Instant convertInstantToTimeZone(Instant instant, TimeZone timeZone) {
        return instant.atZone(timeZone.toZoneId()).toInstant();
    }

    @Override
    public Instant getCurrentInstantInTimeZone(TimeZone timeZone) {
        return ZonedDateTime.now(timeZone.toZoneId()).toInstant();
    }
}
