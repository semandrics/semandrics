package io.github.decote.std.time;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public interface TimeService {

    Instant getCurrentInstant();

    LocalDateTime getCurrentLocalDateTime();

    ZonedDateTime getCurrentZonedDateTime(TimeZone timeZone);

    TimeZone getSystemTimeZone();

    Instant convertInstantToTimeZone(Instant instant, TimeZone timeZone);

    Instant getCurrentInstantInTimeZone(TimeZone timeZone);
}
