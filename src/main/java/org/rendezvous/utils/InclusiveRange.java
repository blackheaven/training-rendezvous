package org.rendezvous.utils;

import java.util.Objects;

public class InclusiveRange<T extends Comparable<T>> {
    private final T lower;
    private final T upper;

    private InclusiveRange(T lower, T upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public static <T extends Comparable<T>> InclusiveRange<T> of(T lower, T upper) {
        assert lower.compareTo(upper) <= 0;
        return new InclusiveRange<>(lower, upper);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InclusiveRange<?> that)) return false;
        return Objects.equals(lower, that.lower) && Objects.equals(upper, that.upper);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lower, upper);
    }

    public boolean includes(T item) {
        return this.lower.compareTo(item) <= 0 && this.upper.compareTo(item) >= 0;
    }
}
