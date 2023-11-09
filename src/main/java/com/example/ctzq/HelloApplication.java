package com.example.ctzq;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

import java.io.IOException;


public class HelloApplication extends Application {

    @Override
    public void start(Stage PrimaryStage) throws Exception {

            Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
            PrimaryStage.setTitle("Login!");
            PrimaryStage.setScene(new Scene(root, 600, 400));
            PrimaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}