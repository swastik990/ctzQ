package com.example.ctzq;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerTwo implements Initializable {
    @FXML
    private Button backto_login;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        backto_login.setOnAction(event -> loadLoginPage());

    }
    public void loadLoginPage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) backto_login.getScene().getWindow();
            stage.setTitle("Login!");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}