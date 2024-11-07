package org.example.javafx_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.example.javafx_1.geometry2d.Circle;
import org.example.javafx_1.geometry2d.Figure;
import org.example.javafx_1.geometry2d.Rectangle;

public class DrawableFigure {
    private Figure figure;
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;

    public DrawableFigure(Figure figure, double x, double y, double size, Color color) {
        this(figure, x, y, size, size, color);
    }

    public DrawableFigure(Figure figure, double x, double y, double width, double height, Color color) {
        this.figure = figure;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        if (figure instanceof Circle) {
            gc.fillOval(x, y, width, height);
        } else if (figure instanceof Rectangle) {
            gc.fillRect(x, y, width, height);
        }
    }

    public boolean contains(double px, double py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
