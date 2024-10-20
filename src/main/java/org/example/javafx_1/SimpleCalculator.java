package org.example.javafx_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class SimpleCalculator extends Application {

    private TextField firstNumberField;
    private TextField secondNumberField;
    private Label resultLabel;

    @Override
    public void start(Stage primaryStage) {
        firstNumberField = new TextField();
        secondNumberField = new TextField();

        Button addButton = new Button("+");
        Button subtractButton = new Button("-");
        Button multiplyButton = new Button("*");
        Button divideButton = new Button("/");

        resultLabel = new Label("Result:");

        addButton.setOnAction(event -> performOperation("+"));
        subtractButton.setOnAction(event -> performOperation("-"));
        multiplyButton.setOnAction(event -> performOperation("*"));
        divideButton.setOnAction(event -> performOperation("/"));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(new Label("First Number:"), 0, 0);
        gridPane.add(firstNumberField, 1, 0);
        gridPane.add(new Label("Second Number:"), 0, 1);
        gridPane.add(secondNumberField, 1, 1);
        gridPane.add(addButton, 0, 2);
        gridPane.add(subtractButton, 1, 2);
        gridPane.add(multiplyButton, 2, 2);
        gridPane.add(divideButton, 3, 2);
        gridPane.add(resultLabel, 0, 3, 4, 1);

        Scene scene = new Scene(gridPane, 400, 200);
        primaryStage.setTitle("Simple Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void performOperation(String operator) {
        try {
            double num1 = Double.parseDouble(firstNumberField.getText());
            double num2 = Double.parseDouble(secondNumberField.getText());
            double result = 0;


            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        showError("Error: Division by zero!");
                        return;
                    }
                    result = num1 / num2;
                    break;
            }

            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException e) {
            showError("Error: Invalid number format!");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
