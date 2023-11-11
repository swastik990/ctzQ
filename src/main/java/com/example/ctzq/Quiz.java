package com.example.ctzq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class Quiz {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label text;



    @FXML
    private Label text1;


    private int currentPage = 1;
    private int totalPages = 20;


    @FXML
    private Button backButton (ActionEvent event) {
        navigatePage(-1);
        return null;
    }

    @FXML
    private Button nextButton (ActionEvent event){
        navigatePage(1);
        return null;
    }

    private void navigatePage(int step) {
        currentPage = Math.min(Math.max(currentPage + step, 1), totalPages);

        // Update the progress bar
        double progress = (double) (currentPage - 1) / (totalPages - 1);
        progressBar.setProgress(progress);

        text.setText(String.valueOf(currentPage));
        text1.setText(String.valueOf(currentPage));

    }

    @FXML
    private Button option1;

    @FXML
    private Button option2;

    @FXML
    private Button option3;

    @FXML
    private Button option4;




}
