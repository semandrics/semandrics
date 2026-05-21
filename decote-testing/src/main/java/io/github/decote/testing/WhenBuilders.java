package io.github.decote.testing;

public final class WhenBuilders {

    private WhenBuilders() {}

    public static class WhenBuilder1<T, P1, R> {

        private final CoreEntryMethods.Method1<T, P1, R> method;
        private P1 parameter1 = null;

        public WhenBuilder1(CoreEntryMethods.Method1<T, P1, R> method) {
            this.method = method;
        }

        public WhenBuilder1<T, P1, R> with(P1 parameter1) {
            this.parameter1 = parameter1;
            return this;
        }

        public ExpectBuilders.ExpectBuilder1<T, P1, R> expectResult(R expected) {
            return new ExpectBuilders.ExpectBuilder1<>(method, parameter1, expected);
        }
    }
}

