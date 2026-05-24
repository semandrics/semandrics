package io.github.semandrics.testing;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.fail;

public final class ExpectBuilders {

    public static class ExpectBuilder1<T, P1, R> {

        private final CoreEntryMethods.Method1<T, P1, R> method;
        private P1 parameter1;
        private final R expected;

        ExpectBuilder1(CoreEntryMethods.Method1<T, P1, R> method, P1 parameter1, R expected) {
            this.method = method;
            this.parameter1 = parameter1;
            this.expected = expected;
        }

        public void execute(T serviceInstance) {
            Objects.requireNonNull(serviceInstance, "serviceInstance must not be null");

            fail("Verification of expected return value not yet implemented.");
        }
    }
}
