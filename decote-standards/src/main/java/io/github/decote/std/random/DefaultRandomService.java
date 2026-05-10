package io.github.decote.std.random;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class DefaultRandomService implements RandomService {

    @Override
    public UUID generateUuid() {
        return UUID.randomUUID();
    }

    @Override
    public long generateRandomLong() {
        return ThreadLocalRandom.current().nextLong();
    }

    @Override
    public int generateRandomInt(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    @Override
    public double generateRandomDouble() {
        return ThreadLocalRandom.current().nextDouble();
    }

    @Override
    public boolean generateRandomBoolean() {
        return ThreadLocalRandom.current().nextBoolean();
    }
}
