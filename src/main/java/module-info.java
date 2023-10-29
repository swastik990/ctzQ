module com.example.ctzq {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens com.example.ctzq to javafx.fxml;
    exports com.example.ctzq;
}