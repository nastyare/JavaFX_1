package org.example.javafx_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Rectangle implements Figure {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new InvalidDimensionException("The dimensions must be greater than zero.");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }

    @Override
    public String toString() {
        return "Rectangle with width: " + width + " and height: " + height;
    }

    @Override
    public void draw(GraphicsContext gc, double x, double y, double size) {
        gc.setFill(Color.color(Math.random(), Math.random(), Math.random()));
        gc.fillRect(x, y, size, size * (height / width));
    }
}
