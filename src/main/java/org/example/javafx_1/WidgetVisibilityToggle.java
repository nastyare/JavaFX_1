package org.example.javafx_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WidgetVisibilityToggle extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("This is a label");
        TextField textField = new TextField("This is a text field");
        Button button = new Button("This is a button");

        CheckBox labelCheckBox = new CheckBox("Show Label");
        CheckBox textFieldCheckBox = new CheckBox("Show TextField");
        CheckBox buttonCheckBox = new CheckBox("Show Button");

        labelCheckBox.setSelected(true);
        textFieldCheckBox.setSelected(true);
        buttonCheckBox.setSelected(true);


        labelCheckBox.setOnAction(event -> label.setVisible(labelCheckBox.isSelected()));
        textFieldCheckBox.setOnAction(event -> textField.setVisible(textFieldCheckBox.isSelected()));
        buttonCheckBox.setOnAction(event -> button.setVisible(buttonCheckBox.isSelected()));


        VBox widgetBox = new VBox(10, label, textField, button);
        VBox checkBoxBox = new VBox(10, labelCheckBox, textFieldCheckBox, buttonCheckBox);


        HBox root = new HBox(20, widgetBox, checkBoxBox);


        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Widget Visibility Toggle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
