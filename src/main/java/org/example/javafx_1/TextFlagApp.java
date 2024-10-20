package org.example.javafx_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TextFlagApp extends Application {

    private RadioButton redButton1, greenButton1, blueButton1, redButton2, greenButton2, blueButton2, redButton3, greenButton3, blueButton3;

    @Override
    public void start(Stage primaryStage) {
        Label label1 = new Label("Выберите цвет для первой полосы:");
        ToggleGroup group1 = new ToggleGroup();
        redButton1 = new RadioButton("Красный");
        redButton1.setToggleGroup(group1);
        greenButton1 = new RadioButton("Зелёный");
        greenButton1.setToggleGroup(group1);
        blueButton1 = new RadioButton("Синий");
        blueButton1.setToggleGroup(group1);

        Label label2 = new Label("Выберите цвет для второй полосы:");
        ToggleGroup group2 = new ToggleGroup();
        redButton2 = new RadioButton("Красный");
        redButton2.setToggleGroup(group2);
        greenButton2 = new RadioButton("Зелёный");
        greenButton2.setToggleGroup(group2);
        blueButton2 = new RadioButton("Синий");
        blueButton2.setToggleGroup(group2);

        Label label3 = new Label("Выберите цвет для третьей полосы:");
        ToggleGroup group3 = new ToggleGroup();
        redButton3 = new RadioButton("Красный");
        redButton3.setToggleGroup(group3);
        greenButton3 = new RadioButton("Зелёный");
        greenButton3.setToggleGroup(group3);
        blueButton3 = new RadioButton("Синий");
        blueButton3.setToggleGroup(group3);


        Button drawButton = new Button("Нарисовать");
        drawButton.setOnAction(event -> displayFlagColors());


        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);


        gridPane.add(label1, 0, 0);
        gridPane.add(redButton1, 0, 1);
        gridPane.add(greenButton1, 1, 1);
        gridPane.add(blueButton1, 2, 1);


        gridPane.add(label2, 0, 2);
        gridPane.add(redButton2, 0, 3);
        gridPane.add(greenButton2, 1, 3);
        gridPane.add(blueButton2, 2, 3);


        gridPane.add(label3, 0, 4);
        gridPane.add(redButton3, 0, 5);
        gridPane.add(greenButton3, 1, 5);
        gridPane.add(blueButton3, 2, 5);


        gridPane.add(drawButton, 0, 6, 3, 1);


        Scene scene = new Scene(gridPane, 400, 300);
        primaryStage.setTitle("Текстовый флаг");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private void displayFlagColors() {
        String color1 = getSelectedColor(redButton1, greenButton1, blueButton1);
        String color2 = getSelectedColor(redButton2, greenButton2, blueButton2);
        String color3 = getSelectedColor(redButton3, greenButton3, blueButton3);

        if (color1 != null && color2 != null && color3 != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Результат");
            alert.setHeaderText(null);
            alert.setContentText("Флаг: " + color1 + ", " + color2 + ", " + color3);
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText("Пожалуйста, выберите цвета для всех полос!");
            alert.showAndWait();
        }
    }

    private String getSelectedColor(RadioButton red, RadioButton green, RadioButton blue) {
        if (red.isSelected()) {
            return "Красный";
        } else if (green.isSelected()) {
            return "Зелёный";
        } else if (blue.isSelected()) {
            return "Синий";
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
