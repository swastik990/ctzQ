package com.example.ctzq;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


import java.io.IOException;

public class Group {

    @FXML
    private Button back_dashboard;



    public void initialize() {
        back_dashboard.setOnAction(event -> loadDashboard());
    }
    public void loadDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) back_dashboard.getScene().getWindow();
            stage.setTitle("Dashboard!");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
