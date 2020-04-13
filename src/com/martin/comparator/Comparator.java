package com.martin.comparator;

import com.martin.chaining.function.Function;
import com.martin.model.Person;

import java.util.Objects;

@FunctionalInterface
public interface Comparator<T> {

    int compare(T t1, T t2);

    default Comparator<T> reversed(){
        return (t1, t2) -> this.compare(t2, t1);
    }

    static <T, U extends Comparable<U>> Comparator<T> comparing(Function<T, U> keyExtractor) {
        Objects.requireNonNull(keyExtractor);
        return  (p1, p2) -> {
            U s1 = keyExtractor.apply(p1);
            U s2 = keyExtractor.apply(p2);
            return s1.compareTo(s2);
        };
    }

    default Comparator<T> thenComparing(Comparator<T> other) {
        Objects.requireNonNull(other);
        return (T t1, T t2) -> {
            int compare = this.compare(t1, t2);
            if (compare == 0) {
                return other.compare(t1, t2);
            } else {
                return compare;
            }
        };
    }

    default <U extends Comparable<U>> Comparator<T> thenComparing(Function<T, U> keyExctractor) {
        Objects.requireNonNull(keyExctractor);
        Comparator<T> other = comparing(keyExctractor);
        return this.thenComparing(other);
    }
}
