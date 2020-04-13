package com.martin;

import com.martin.factory.Factory;
import com.martin.model.Rectangle;
import com.martin.model.Shape;
import com.martin.model.Square;
import com.martin.model.Triangle;
import com.martin.registry.Builder;
import com.martin.registry.Registry;

import java.util.function.Consumer;

public class PlayWithRegistryBuilder {

    public static void main(String[] args) {

        Consumer<Builder<Shape>> consumer1 = builder -> builder.register("rectangle", Rectangle::new);
        Consumer<Builder<Shape>> consumer2 = builder -> builder.register("triangle", Triangle::new);

        Consumer<Builder<Shape>> initializer = consumer1.andThen(consumer2);

        Registry registry = Registry.createRegistry(initializer, s -> {throw new IllegalArgumentException("Unknown shape: " + s);});

        Factory<Rectangle> buildRectangleFactory = (Factory<Rectangle>) registry.buildShapeFactory("rectangle");

        // Create rectangle from factory
        Rectangle rectangle = buildRectangleFactory.newInstance();
        System.out.println("Rectangle = " + rectangle);

        Factory<Triangle> buildTriangleFactory = (Factory<Triangle>) registry.buildShapeFactory("triangle");

        // Create triangle from factory
        Triangle triangle = buildTriangleFactory.newInstance();
        System.out.println("Triangle = " + triangle);

        // Test Exception Unknown shape: square
        Factory<Square> buildSquareFactory = (Factory<Square>) registry.buildShapeFactory("square");
        Square square = buildSquareFactory.newInstance();
    }
}
