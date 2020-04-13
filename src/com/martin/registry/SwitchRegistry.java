package com.martin.registry;

import com.martin.factory.Factory;
import com.martin.model.Rectangle;
import com.martin.model.Shape;
import com.martin.model.Square;
import com.martin.model.Triangle;

public class SwitchRegistry {

    public Factory<? extends Shape> buildShapeFactory(String shape) {

        switch (shape) {
            case "square" : return () -> new Square();
            case "triangle" : return () -> new Triangle();
            case "rectangle" :return () -> new Rectangle();
            default:
                throw new IllegalArgumentException("Unknown shape: " + shape);
        }
    }
}
