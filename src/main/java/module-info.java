module org.example.javafx_1 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.javafx_1 to javafx.fxml;
    exports org.example.javafx_1;
}