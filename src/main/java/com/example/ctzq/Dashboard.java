package com.example.ctzq;


import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Dashboard implements Initializable{

    @FXML
    private Button backto_login;

    @FXML
    private Button playQuiz;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        backto_login.setOnAction(event -> loadLoginPage());
        playQuiz.setOnAction(event -> loadQuizPage());

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

    } public void loadQuizPage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Quiz.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) playQuiz.getScene().getWindow();
            stage.setTitle("Quiz!");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
