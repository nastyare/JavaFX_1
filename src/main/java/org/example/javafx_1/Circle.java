package org.example.javafx_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Circle implements Figure {
    private double radius;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new InvalidDimensionException("The radius must be greater than zero.");
        }
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle with radius: " + radius;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y, double size) {
        gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        gc.fillOval(x, y, size, size);
    }
}
