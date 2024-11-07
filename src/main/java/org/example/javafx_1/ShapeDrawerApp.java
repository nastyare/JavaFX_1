package org.example.javafx_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ShapeDrawerApp extends Application {
    private static final double CANVAS_WIDTH = 800;
    private static final double CANVAS_HEIGHT = 600;

    private final Canvas canvas = new Canvas(CANVAS_WIDTH, CANVAS_HEIGHT);
    private final List<DrawableFigure> figures = new ArrayList<>();
    private DrawableFigure selectedFigure = null;
    private double offsetX;
    private double offsetY;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Shape Drawer");

        GraphicsContext gc = canvas.getGraphicsContext2D();
        Button circleButton = new Button("Draw Circle");
        Button rectangleButton = new Button("Draw Rectangle");

        circleButton.setOnAction(e -> addRandomCircle(gc));
        rectangleButton.setOnAction(e -> addRandomRectangle(gc));

        canvas.setOnMousePressed(this::onMousePressed);
        canvas.setOnMouseDragged(this::onMouseDragged);
        canvas.setOnMouseReleased(e -> selectedFigure = null);

        HBox buttonBox = new HBox(10, circleButton, rectangleButton);
        VBox root = new VBox(10, canvas, buttonBox);

        primaryStage.setScene(new Scene(root, CANVAS_WIDTH, CANVAS_HEIGHT + 50));
        primaryStage.show();
    }

    private void addRandomCircle(GraphicsContext gc) {
        double radius = 10 + Math.random() * 50;
        double x = Math.random() * (CANVAS_WIDTH - radius * 2);
        double y = Math.random() * (CANVAS_HEIGHT - radius * 2);
        Circle circle = new Circle(radius);
        DrawableFigure drawableCircle = new DrawableFigure(circle, x, y, radius * 2, Color.color(Math.random(), Math.random(), Math.random()));
        figures.add(drawableCircle);
        redraw(gc);
    }

    private void addRandomRectangle(GraphicsContext gc) {
        double width = 20 + Math.random() * 100;
        double height = 20 + Math.random() * 100;
        double x = Math.random() * (CANVAS_WIDTH - width);
        double y = Math.random() * (CANVAS_HEIGHT - height);
        Rectangle rectangle = new Rectangle(width, height);
        DrawableFigure drawableRectangle = new DrawableFigure(rectangle, x, y, width, height, Color.color(Math.random(), Math.random(), Math.random()));
        figures.add(drawableRectangle);
        redraw(gc);
    }

    private void onMousePressed(MouseEvent event) {
        Optional<DrawableFigure> figureOpt = getFigureAt(event.getX(), event.getY());
        if (figureOpt.isPresent()) {
            DrawableFigure figure = figureOpt.get();
            if (event.getButton() == MouseButton.PRIMARY) {
                selectedFigure = figure;
                offsetX = event.getX() - figure.getX();
                offsetY = event.getY() - figure.getY();
                // Bring to front
                figures.remove(figure);
                figures.add(figure);
                redraw(canvas.getGraphicsContext2D());
            } else if (event.getButton() == MouseButton.SECONDARY) {
                figure.setColor(Color.color(Math.random(), Math.random(), Math.random()));
                redraw(canvas.getGraphicsContext2D());
            }
        }
    }

    private void onMouseDragged(MouseEvent event) {
        if (selectedFigure != null) {
            selectedFigure.setX(event.getX() - offsetX);
            selectedFigure.setY(event.getY() - offsetY);
            redraw(canvas.getGraphicsContext2D());
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

    private void redraw(GraphicsContext gc) {
        gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
        for (DrawableFigure figure : figures) {
            figure.draw(gc);
        }
    }
}
