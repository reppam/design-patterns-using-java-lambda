package com.martin;

import com.martin.chaining.function.Consumer;

public class PlayWithConsumers {

    public static void main(String[] args) {

        Consumer<String> c1 = s -> System.out.println("c1 = " + s);
        Consumer<String> c2 = s -> System.out.println("c2 = " + s);

        // Chaining consumers
         Consumer<String> c3 = c1.andThen(c2);
        /*Consumer<String> c3 = s -> {
                c1.accept(s);
                c2.accept(s);
        };*/

        c3.accept("Hello");
    }
}
