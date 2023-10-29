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


public class Controller implements Initializable {

    @FXML
    private Button registerpage_button;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        registerpage_button.setOnAction(event -> loadRegisterPage());

    }
    public void loadRegisterPage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) registerpage_button.getScene().getWindow();
            stage.setTitle("Register!");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}