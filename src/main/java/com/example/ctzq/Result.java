package com.example.ctzq;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
public class Result {

    @FXML
    private Label comments;

    @FXML
    private ProgressIndicator correct_progress;

    @FXML
    private Label correct_progress1;

    @FXML
    private Label remark;

    @FXML
    private Button to_dashboard;

    @FXML
    private Button to_group;

    @FXML
    private Label best;

    @FXML
    private Label worst;

    private int correctValue;


    public void setCorrectValue(int correct) {
        correctValue = correct;
    }

    public void initialize() {

        double percentage = ((double) correctValue / 20) * 100;
        correct_progress1.setText(String.format("%.0f%%", percentage));
        best.setText(" " + correctValue + " ");

        int wrongValue = 20 - correctValue;
        worst.setText(" " + wrongValue + " ");

        correct_progress.setProgress(percentage / 100);

        if (correctValue < 10) {
            remark.setText("FAIL");
            comments.setText("Better Luck next time Hommie.");
        } else {
            remark.setText("PASS");
            comments.setText("Congratulation on passing The Official CTZQ.YOU ROCK");
        }


        to_dashboard.setOnAction(event -> loadDashboard());
        to_group.setOnAction(event -> loadGroup());
    }


    public void loadDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) to_dashboard.getScene().getWindow();
            stage.setTitle("Dashboard!");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void loadGroup() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("group.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) to_group.getScene().getWindow();
            stage.setTitle("Group Members!");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
