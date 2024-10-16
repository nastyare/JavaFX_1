package org.example.javafx_1;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class WordSwitcher extends Application {

    private boolean isLeftToRight = true;

    @Override
    public void start(Stage primaryStage) {
        TextField textField1 = new TextField();
        TextField textField2 = new TextField();

        Button switchButton = new Button("→");

        switchButton.setOnAction(event -> {
            if (isLeftToRight) {
                textField2.setText(textField1.getText());
                textField1.clear();
                switchButton.setText("←");
            } else {
                textField1.setText(textField2.getText());
                textField2.clear();
                switchButton.setText("→");
            }
            isLeftToRight = !isLeftToRight;
        });

        HBox root = new HBox(10, textField1, switchButton, textField2);

        Scene scene = new Scene(root, 400, 100);
        primaryStage.setTitle("Word Switcher");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
