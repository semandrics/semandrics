package io.github.decote.testing;

public final class Scenario {

    private Scenario() {}

    public static <T, P1, R> WhenBuilders.WhenBuilder1<T, P1, R> whenCalling(CoreEntryMethods.Method1<T, P1, R> method) {
        return new WhenBuilders.WhenBuilder1<>(method);
    }
}

