package org.example.javafx_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class RestaurantOrderApp extends Application {

    private final Map<String, Double> menu = new HashMap<>();
    private final Map<String, Integer> order = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        menu.put("Pasta", 8.50);
        menu.put("Pizza", 12.00);
        menu.put("Salad", 6.00);
        menu.put("Soup", 5.50);

        GridPane gridPane = new GridPane();
        int row = 0;

        for (String dish : menu.keySet()) {
            CheckBox checkBox = new CheckBox(dish + " ($" + menu.get(dish) + ")");
            Spinner<Integer> quantitySpinner = new Spinner<>(1, 10, 1);
            quantitySpinner.setDisable(true);

            checkBox.setOnAction(event -> quantitySpinner.setDisable(!checkBox.isSelected()));

            gridPane.add(checkBox, 0, row);
            gridPane.add(quantitySpinner, 1, row);
            row++;

            checkBox.setOnAction(event -> {
                if (checkBox.isSelected()) {
                    order.put(dish, quantitySpinner.getValue());
                } else {
                    order.remove(dish);
                }
            });

            quantitySpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
                if (checkBox.isSelected()) {
                    order.put(dish, newValue);
                }
            });
        }

        Button orderButton = new Button("Оформить заказ");
        orderButton.setOnAction(event -> showReceipt());

        VBox root = new VBox(10, gridPane, orderButton);
        Scene scene = new Scene(root, 400, 300);

        primaryStage.setTitle("Restaurant Order");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showReceipt() {
        Stage receiptStage = new Stage();
        receiptStage.initModality(Modality.APPLICATION_MODAL);
        receiptStage.setTitle("Чек");

        VBox receiptBox = new VBox(10);
        double totalCost = 0;

        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            String dish = entry.getKey();
            int quantity = entry.getValue();
            double price = menu.get(dish);
            double cost = price * quantity;

            receiptBox.getChildren().add(new Label(dish + ": " + quantity + " порций x $" + price + " = $" + cost));
            totalCost += cost;
        }

        receiptBox.getChildren().add(new Label("Суммарная стоимость заказа: $" + String.format("%.2f", totalCost)));

        Button closeButton = new Button("Закрыть");
        closeButton.setOnAction(e -> receiptStage.close());
        receiptBox.getChildren().add(closeButton);

        Scene receiptScene = new Scene(receiptBox, 300, 200);
        receiptStage.setScene(receiptScene);
        receiptStage.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
