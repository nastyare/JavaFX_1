package org.example.javafx_1;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.example.javafx_1.geometry2d.Circle;
import org.example.javafx_1.geometry2d.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShapeDrawerController {
    @FXML
    private Canvas canvas;
    @FXML
    private javafx.scene.control.Button circleButton;
    @FXML
    private javafx.scene.control.Button rectangleButton;

    private final List<DrawableFigure> figures = new ArrayList<>();
    private DrawableFigure selectedFigure = null;
    private double offsetX;
    private double offsetY;

    @FXML
    private void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseDragged(this::onMouseDragged);
        canvas.setOnMouseReleased(e -> selectedFigure = null);
    }

    @FXML
    private void handleCircleButton() {
        addRandomCircle();
    }

    @FXML
    private void handleRectangleButton() {
        addRandomRectangle();
    }

    private void addRandomCircle() {
        double radius = 10 + Math.random() * 50;
        double x = Math.random() * (canvas.getWidth() - radius * 2);
        double y = Math.random() * (canvas.getHeight() - radius * 2);
        Circle circle = new Circle(radius);
        DrawableFigure drawableCircle = new DrawableFigure(circle, x, y, radius * 2, Color.color(Math.random(), Math.random(), Math.random()));
        figures.add(drawableCircle);
        redraw();
    }

    private void addRandomRectangle() {
        double width = 20 + Math.random() * 100;
        double height = 20 + Math.random() * 100;
        double x = Math.random() * (canvas.getWidth() - width);
        double y = Math.random() * (canvas.getHeight() - height);
        Rectangle rectangle = new Rectangle(width, height);
        DrawableFigure drawableRectangle = new DrawableFigure(rectangle, x, y, width, height, Color.color(Math.random(), Math.random(), Math.random()));
        figures.add(drawableRectangle);
        redraw();
    }

    private void onMousePressed(MouseEvent event) {
        Optional<DrawableFigure> figureOpt = getFigureAt(event.getX(), event.getY());
        if (figureOpt.isPresent()) {
            DrawableFigure figure = figureOpt.get();
            if (event.getButton() == MouseButton.PRIMARY) {
                selectedFigure = figure;
                offsetX = event.getX() - figure.getX();
                offsetY = event.getY() - figure.getY();
                figures.remove(figure);
                figures.add(figure); // bring to front
                redraw();
            } else if (event.getButton() == MouseButton.SECONDARY) {
                figure.setColor(Color.color(Math.random(), Math.random(), Math.random()));
                redraw();
            }
        }
    }

    private void onMouseDragged(MouseEvent event) {
        if (selectedFigure != null) {
            selectedFigure.setX(event.getX() - offsetX);
            selectedFigure.setY(event.getY() - offsetY);
            redraw();
        }
    }

    private Optional<DrawableFigure> getFigureAt(double x, double y) {
        for (int i = figures.size() - 1; i >= 0; i--) {
            DrawableFigure figure = figures.get(i);
            if (figure.contains(x, y)) {
                return Optional.of(figure);
            }
        }
        return Optional.empty();
    }

    private void redraw() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (DrawableFigure figure : figures) {
            figure.draw(gc);
        }
    }
}
