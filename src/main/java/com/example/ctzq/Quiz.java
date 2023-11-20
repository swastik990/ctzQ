package com.example.ctzq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javafx.animation.PauseTransition;
import javafx.util.Duration;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.fxml.Initializable;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Quiz implements Initializable{

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label text;

    @FXML
    private Label questions;

    @FXML
    public Button option1, option2, option3, option4;

    private boolean isOptionSelected = false;

    static int correct =1;
    static int wrong =1 ;





    @FXML
    private Label text1;

    @FXML
    private Button backto_dashboard;
    @FXML
    private Button load_submit;


    static int currentPage = 1;
    private int totalPages = 20;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        backto_dashboard.setOnAction(event -> backButton(event));
        load_submit.setOnAction(event -> nextButton(event));

        // Add event handlers for each option
        option1.setOnAction(event -> handleOptionSelected(option1));
        option2.setOnAction(event -> handleOptionSelected(option2));
        option3.setOnAction(event -> handleOptionSelected(option3));
        option4.setOnAction(event -> handleOptionSelected(option4));

        loadQuestions();

    }

    public String selectedUserAnswer;

    int[] score;

    private void handleOptionSelected(Button selectedOption) {
        // Enable the nextButton when an option is selected
        isOptionSelected = true;
        selectedUserAnswer = selectedOption.getText();
        load_submit.setDisable(false);




        option1.setDisable(option1 != selectedOption);
        option2.setDisable(option2 != selectedOption);
        option3.setDisable(option3 != selectedOption);
        option4.setDisable(option4 != selectedOption);



    }



    @FXML
    public Button backButton (ActionEvent event) {

        if (currentPage == 1) {

            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) backto_dashboard.getScene().getWindow();
                stage.setTitle("Dashboard!");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }else {
            backto_dashboard.setDisable(true);
        }
        return null;
    }


    public int[] processAnswer(int currentPage, int correct, int wrong) {
        score = new int[]{correct, wrong};

        if (isOptionSelected) {
            String userAnswer = selectedUserAnswer;
            boolean isCorrect = checkAnswer(userAnswer, currentPage);


            if (isCorrect) {
                score[0]++;
            } else {
                score[1]++;
            }
        }

//        System.out.println(score[0]);

        return score;
    }


    @FXML
    private void nextButton(ActionEvent event) {
        if (currentPage == 20) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("result.fxml"));
                Parent root = loader.load();

                // Access the controller of the Result.fxml file
                Result result = loader.getController();

                // Pass the correct and wrong values to the controller
                int[] updatedScore = processAnswer(currentPage, correct, wrong);
                System.out.println("next button: " + score[0]);
                result.setCorrectValue(score[0]);
                result.initialize();


                Stage stage = (Stage) load_submit.getScene().getWindow();
                stage.setTitle("Result!");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        } else {
            // Process the answer and update the correct and wrong scores
            int[] updatedScore = processAnswer(currentPage, correct, wrong);
            correct = updatedScore[0];
            wrong = updatedScore[1];

            // Reset flags for the next question
            isOptionSelected = false;
            load_submit.setDisable(true);
            backto_dashboard.setDisable(true);

            // Navigate to the next page and load the next set of questions
            navigatePage(1);
            loadQuestions();

            // Enable all options for the next question
            option1.setDisable(false);
            option2.setDisable(false);
            option3.setDisable(false);
            option4.setDisable(false);
        }
    }











    private void navigatePage(int step) {
        currentPage = Math.min(Math.max(currentPage + step, 1), totalPages);

        // Update the progress bar
        double progress = (double) (currentPage - 1) / (totalPages - 1);
        progressBar.setProgress(progress);

        text.setText(String.valueOf(currentPage));
        text1.setText(String.valueOf(currentPage));

    }



    private void loadQuestions() {
        // Define the file path for your questions file
        String filePath = "C:\\Users\\user\\Desktop\\5th Semester IIMS College\\Advanced Programming\\javacode\\ctzQ\\src\\main\\java\\com\\example\\ctzq\\questions.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            // Skip lines until you reach the current question
            for (int i = 1; i < currentPage; i++) {
                br.readLine();
                br.readLine();  // Skip the question
                br.readLine();  // Skip option1
                br.readLine();  // Skip option2
                br.readLine();  // Skip option3
                br.readLine();  // Skip option4
            }

            // Read the question and options for the current page
            questions.setText(br.readLine().trim());
            option1.setText(br.readLine().trim());
            option2.setText(br.readLine().trim());
            option3.setText(br.readLine().trim());
            option4.setText(br.readLine().trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean checkAnswer(String answer, int questionNumber) {
        return switch (questionNumber) {
            case 1 -> answer.equals("Loy Krathong");
            case 2 -> answer.equals("Jalan Alor");
            case 3 -> answer.equals("Songkran");
            case 4 -> answer.equals("Orchard Road");
            case 5 -> answer.equals("Bangkok");
            case 6 -> answer.equals("Nasi Lemak");
            case 7 -> answer.equals("Chinese New Year");
            case 8 -> answer.equals("Muhyiddin Yassin");
            case 9 -> answer.equals("Thailand");
            case 10 -> answer.equals("Chao Phraya River");
            case 11 -> answer.equals("Thai");
            case 12 -> answer.equals("Buddhism");
            case 13 -> answer.equals("Lee Kuan Yew");
            case 14 -> answer.equals("Petronas Twin Towers");
            case 15 -> answer.equals("Malay");
            case 16 -> answer.equals("Merlion");
            case 17 -> answer.equals("1965");
            case 18 -> answer.equals("Islam");
            case 19 -> answer.equals("Langkawi");
            case 20 -> answer.equals("Singapore");
            default -> false;
        };
    }








}
