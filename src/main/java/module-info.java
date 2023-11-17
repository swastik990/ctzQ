module com.example.ctzq {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.ctzq to javafx.fxml;
    exports com.example.ctzq;
}