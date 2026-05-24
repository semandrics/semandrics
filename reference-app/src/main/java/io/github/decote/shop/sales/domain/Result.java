package io.github.semandrics.shop.sales.domain;

public sealed interface Result<T> {
    record Ok<T>(T value) implements Result<T> {}
    record Err<T>(String error) implements Result<T> {}

    static <T> Result<T> ok(T value) {
        return new Ok<>(value);
    }

    static <T> Result<T> err(String error) {
        return new Err<>(error);
    }

    default boolean isOk() {
        return this instanceof Ok;
    }

    default boolean isErr() {
        return this instanceof Err;
    }

    default T get() {
        if (this instanceof Ok<T> ok) {
            return ok.value();
        }
        throw new RuntimeException("Called get on Err: " + ((Err<T>) this).error());
    }

    default String getError() {
        if (this instanceof Err<T> err) {
            return err.error();
        }
        throw new RuntimeException("Called getError on Ok");
    }
}
