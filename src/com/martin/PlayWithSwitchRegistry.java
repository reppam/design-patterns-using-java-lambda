package com.martin;

import com.martin.factory.Factory;
import com.martin.model.Rectangle;
import com.martin.registry.SwitchRegistry;

public class PlayWithSwitchRegistry {

    public static void main(String[] args) {

        SwitchRegistry registry = new SwitchRegistry();

        Factory<Rectangle> rectangleFactory = (Factory<Rectangle>) registry.buildShapeFactory("rectangle");
        System.out.println("Rectangle: " + rectangleFactory.newInstance());


    }
}
