package io.github.decote.testing;

public final class CoreEntryMethods {

    private CoreEntryMethods() {
    }

    //
    // WITH RETURN VALUE
    //
    //

    @FunctionalInterface
    public interface Method0<T, R> {
        R apply(T instance);
    }

    @FunctionalInterface
    public interface Method1<T, P1, R> {
        R apply(T instance, P1 p1);
    }

    @FunctionalInterface
    public interface Method2<T, P1, P2, R> {
        R apply(T instance, P1 p1, P2 p2);
    }

    @FunctionalInterface
    public interface Method3<T, P1, P2, P3, R> {
        R apply(T instance, P1 p1, P2 p2, P3 p3);
    }

    @FunctionalInterface
    public interface Method4<T, P1, P2, P3, P4, R> {
        R apply(T instance, P1 p1, P2 p2, P3 p3, P4 p4);
    }

    @FunctionalInterface
    public interface Method5<T, P1, P2, P3, P4, P5, R> {
        R apply(T instance, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }

    @FunctionalInterface
    public interface Method6<T, P1, P2, P3, P4, P5, P6, R> {
        R apply(T instance, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6);
    }

    @FunctionalInterface
    public interface Method7<T, P1, P2, P3, P4, P5, P6, P7, R> {
        R apply(T instance, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7);
    }

    //
    // VOID (NO RETURN VALUE)
    //

    @FunctionalInterface
    public interface VoidMethod0<T> {
        void apply(T instance);
    }

    @FunctionalInterface
    public interface VoidMethod1<T, P1> {
        void apply(T instance, P1 p1);
    }

    @FunctionalInterface
    public interface VoidMethod2<T, P1, P2> {
        void apply(T instance, P1 p1, P2 p2);
    }

    @FunctionalInterface
    public interface VoidMethod3<T, P1, P2, P3> {
        void apply(T instance, P1 p1, P2 p2, P3 p3);
    }

    @FunctionalInterface
    public interface VoidMethod4<T, P1, P2, P3, P4> {
        void apply(T instance, P1 p1, P2 p2, P3 p3, P4 p4);
    }

    @FunctionalInterface
    public interface VoidMethod5<T, P1, P2, P3, P4, P5> {
        void apply(T instance, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5);
    }

    @FunctionalInterface
    public interface VoidMethod6<T, P1, P2, P3, P4, P5, P6> {
        void apply(T instance, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6);
    }

    @FunctionalInterface
    public interface VoidMethod7<T, P1, P2, P3, P4, P5, P6, P7> {
        void apply(T instance, P1 p1, P2 p2, P3 p3, P4 p4, P5 p5, P6 p6, P7 p7);
    }
}
