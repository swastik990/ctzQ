package com.example.ctzq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class Quiz {

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Label text;

    @FXML
    private Label questions;

    @FXML
    public Button option1, option2, option3, option4;


    static int correct = 0;
    static int wrong = 0;


    @FXML
    private Label text1;

    @FXML
    private Button backto_dashboard;
    @FXML
    private Button load_submit;


    private int currentPage = 1;
    private int totalPages = 20;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        backto_dashboard.setOnAction(event -> backButton(event));
        load_submit.setOnAction(event -> nextButton(event));


        loadQuestions();

    }

    @FXML
    public void opt1clicked(ActionEvent event) {
        handleOptionClick(option1);

        checkAnswer(option1.getText().toString());

        if (checkAnswer(option1.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
    }

    @FXML
    public void opt2clicked(ActionEvent event) {
        handleOptionClick(option2);
        checkAnswer(option2.getText().toString());

        if (checkAnswer(option2.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
    }

    @FXML
    public void opt3clicked(ActionEvent event) {
        handleOptionClick(option3);
        checkAnswer(option3.getText().toString());

        if (checkAnswer(option3.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
    }

    @FXML
    public void opt4clicked(ActionEvent event) {
        handleOptionClick(option4);
        checkAnswer(option4.getText().toString());

        if (checkAnswer(option4.getText().toString())) {
            correct = correct + 1;
        } else {
            wrong = wrong + 1;
        }
    }


    private boolean isSelected = false;
    private void handleOptionClick(Button button) {
        if (!isSelected) {
            // Select the option
            button.setStyle("-fx-background-color: #8ECDDD; -fx-background-radius: 20;");
            isSelected = true;
        } else {
            // Deselect the option
            button.setStyle("-fx-background-color: #ffcc70; -fx-background-radius: 20;"); // Reset style to default
            isSelected = false;
        }
    }

    private void deselectAllButtons() {
        // Reset the style for all buttons
        option1.setStyle("-fx-background-color: #ffcc70; -fx-background-radius: 20;");
        option2.setStyle("-fx-background-color: #ffcc70; -fx-background-radius: 20;");
        option3.setStyle("-fx-background-color: #ffcc70; -fx-background-radius: 20;");
        option4.setStyle("-fx-background-color: #ffcc70; -fx-background-radius: 20;");
        // Add more buttons if needed
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
        } else {

            navigatePage(-1);
            loadQuestions();
        }
        return null;
    }


    @FXML
    private void nextButton(ActionEvent event) {
        if (currentPage == 20) {

            try {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("submit.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) load_submit.getScene().getWindow();
                stage.setTitle("Submitted!");
                stage.setScene(new Scene(root, 600, 400));
                stage.show();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
       else {
            navigatePage(1);
            loadQuestions();
            deselectAllButtons();
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

        if (currentPage == 1) { //Question 1
            questions.setText("Which famous Thai festival is known for its release of floating lanterns into the sky?");
            option1.setText("Songkran");
            option2.setText("Loy Krathong");
            option3.setText("Chinese New Year");
            option4.setText("Diwali");
        } if (currentPage == 2) { //Question 2
            questions.setText("What is the name of the famous street food market in Kuala Lumpur?");
            option1.setText("Jalan Alor");
            option2.setText("Petaling Street");
            option3.setText("Jonker Street");
            option4.setText("Gurney Drive");
        } if (currentPage == 3) { //Question 3
            questions.setText("Which Thai festival is celebrated annually with water fights and is known as the Thai New Year?");
            option1.setText("Songkran");
            option2.setText("Loy Krathong");
            option3.setText("Chinese New Year");
            option4.setText("Diwali");
        } if (currentPage == 4) { //Question 4
            questions.setText("What is the name of the famous shopping street in Singapore?");
            option1.setText("Orchard Road");
            option2.setText("Bugis Street");
            option3.setText("Haji Lane");
            option4.setText("Clarke Quay");
        } if (currentPage == 5) { //Question 5
            questions.setText("Which city in Thailand is famous for its ancient temples, including the Wat Arun and Wat Phra Kaew?");
            option1.setText("Bangkok");
            option2.setText("Chiang Mai");
            option3.setText("Phuket");
            option4.setText("Pattaya");
        } if (currentPage == 6) { //Question 6
            questions.setText("Which traditional Malaysian dish is made of rice cooked in coconut milk and served with various side dishes?");
            option1.setText("Nasi Lemak");
            option2.setText("Roti Canai");
            option3.setText("Char Kway Teow");
            option4.setText("Laksa");
        } if (currentPage == 7) { //Question 7
            questions.setText("Which festival celebrates the Chinese Lunar New Year in Singapore?");
            option1.setText("Deepavali");
            option2.setText("Hari Raya Puasa");
            option3.setText("Vesak Day");
            option4.setText("Chinese New Year");
        } if (currentPage == 8) { //Question 8
            questions.setText("Who is the current Prime Minister of Malaysia (as of 2021)?");
            option1.setText("Mahathir Mohamad");
            option2.setText("Najib Razak");
            option3.setText("Muhyiddin Yassin");
            option4.setText("Anwar Ibrahim");
        } if (currentPage == 9) { //Question 9
            questions.setText("Which country is known for its traditional dance form called Thai classical dance?");
            option1.setText("Thailand");
            option2.setText("Malaysia");
            option3.setText("Indonesia");
            option4.setText("Singapore");
        } if (currentPage == 10) { //Question 10
            questions.setText("Which river is considered the lifeline of Thailand and flows through the capital city?");
            option1.setText("Mekong River");
            option2.setText("Chao Phraya River");
            option3.setText("Irrawaddy River");
            option4.setText("Yangtze River");
        } if (currentPage == 11) { //Question 11
            questions.setText("What is the official language of Thailand?");
            option1.setText("Thai");
            option2.setText("English");
            option3.setText("Mandarin");
            option4.setText("Malay");
        } if (currentPage == 12) { //Question 12
            questions.setText("Thailand is known for its rich cultural heritage, with influences from which major religion?");
            option1.setText("Buddhism");
            option2.setText("Hinduism");
            option3.setText("Islam");
            option4.setText("Christianity");
        } if (currentPage == 13) { //Question 13
            questions.setText("Who is considered the founding father of modern Singapore?");
            option1.setText("Lee Kuan Yew");
            option2.setText("Goh Chok Tong");
            option3.setText("Tony Tan");
            option4.setText("Halimah Yacob");
        } if (currentPage == 14) { //Question 14
            questions.setText("Which famous twin towers are located in Kuala Lumpur?");
            option1.setText("Petronas Twin Towers");
            option2.setText("Burj Khalifa");
            option3.setText("Eiffel Tower");
            option4.setText("Sydney Opera House");
        } if (currentPage == 15) { //Question 15
            questions.setText("Which ethnic group forms the majority in Malaysia?");
            option1.setText("Malay");
            option2.setText("Chinese");
            option3.setText("Indian");
            option4.setText("Indigenous Bumiputera");
        } if (currentPage == 16) { //Question 16
            questions.setText("What is the name of the iconic symbol of Singapore, a half-lion half-fish creature?");
            option1.setText("Merlion");
            option2.setText("Dragon");
            option3.setText("Griffin");
            option4.setText("Chimera");
        } if (currentPage == 17) { //Question 17
            questions.setText("When did Singapore gain independence?");
            option1.setText("1959");
            option2.setText("1965");
            option3.setText("1970");
            option4.setText("1980");
        } if (currentPage == 18) { //Question 18
            questions.setText("What is the official religion of Malaysia?");
            option1.setText("Islam");
            option2.setText("Buddhism");
            option3.setText("Hinduism");
            option4.setText("Christianity");
        } if (currentPage == 19) { //Question 19
            questions.setText("Which island is known for its beautiful beaches and clear waters in Malaysia?");
            option1.setText("Langkawi");
            option2.setText("Tioman");
            option3.setText("Pangkor");
            option4.setText("Redang");
        } if (currentPage == 20) { //Question 20
            questions.setText("Which country is known for its unique cultural festivals like Thaipusam and Hungry Ghost Festival?");
            option1.setText("Singapore");
            option2.setText("Thailand");
            option3.setText("Malaysia");
            option4.setText("Indonesia");
        }
    }


    boolean checkAnswer(String answer) {

        if (currentPage == 1) {
            // Question 1
            if (answer.equals("Loy Krathong")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 2) {
            // Question 2
            if (answer.equals("Jalan Alor")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 3) {
            // Question 3
            if (answer.equals("Songkran")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 4) {
            // Question 4
            if (answer.equals("Orchard Road")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 5) {
            // Question 5
            if (answer.equals("Bangkok")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 6) {
            // Question 6
            if (answer.equals("Nasi Lemak")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 7) {
            // Question 7
            if (answer.equals("Chinese New Year")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 8) {
            // Question 8
            if (answer.equals("Muhyiddin Yassin")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 9) {
            // Question 9
            if (answer.equals("Thailand")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 10) {
            // Question 10
            if (answer.equals("Chao Phraya River")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 11) {
            // Question 11
            if (answer.equals("Thai")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 12) {
            // Question 12
            if (answer.equals("Buddhism")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 13) {
            // Question 13
            if (answer.equals("Lee Kuan Yew")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 14) {
            // Question 14
            if (answer.equals("Petronas Twin Towers")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 15) {
            // Question 15
            if (answer.equals("Malay")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 16) {
            // Question 16
            if (answer.equals("Merlion")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 17) {
            // Question 17
            if (answer.equals("1965")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 18) {
            // Question 18
            if (answer.equals("Islam")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 19) {
            // Question 19
            if (answer.equals("Langkawi")) {
                return true;
            } else {
                return false;
            }
        }
        if (currentPage == 20) {
            // Question 20
            if (answer.equals("Singapore")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }







}
