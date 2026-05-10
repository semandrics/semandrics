package io.github.decote.std.random;

import java.util.UUID;

public interface RandomService {

    UUID generateUuid();

    long generateRandomLong();

    int generateRandomInt(int bound);

    double generateRandomDouble();

    boolean generateRandomBoolean();
}
