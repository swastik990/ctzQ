package com.example.ctzq;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class loginPageController implements Initializable {

        @FXML
        private Button registerpage_button;

        @FXML
        private TextField username;

        @FXML
        private PasswordField password;

        @FXML
        private Button loginButton;

        @FXML
        private Button backto_login;

        @FXML
        private Button playQuiz;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (backto_login != null) {
            backto_login.setOnAction(event -> loadLoginPage());
        }
        if (playQuiz != null) {
            playQuiz.setOnAction(event -> loadQuizPage());
        }
        if (registerpage_button != null) {
            registerpage_button.setOnAction(event -> loadRegisterPage());
        }

        if (loginButton != null) {
            loginButton.setOnAction(event -> {
                String user_name = username.getText();
                String pass_word = password.getText();

                //calling method
                compareInDatabase(user_name, pass_word);
            });
        }
    }


        private void compareInDatabase(String user_name, String pass_word) {
            if (user_name.isEmpty() || pass_word.isEmpty()) {
                showErrorMessage("Credentials cannot be empty.");
                return;
            }

            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ctzqdb", "root", "Infiniti@111");
                 PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM user_info WHERE user_name = ?")) {

                preparedStatement.setString(1, user_name);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (!resultSet.isBeforeFirst()) {
                        showAlert("Username not found.");
                    } else {
                        while (resultSet.next()) {
                            String retrievedPassword = resultSet.getString("password");

                            // Securely compare passwords using a secure method like BCrypt
                            if (passwordsMatch(pass_word, retrievedPassword)) {
                                loadDashboardPage();
                            } else {
                                showAlert("Incorrect Password");
                            }
                        }
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
    public void loadDashboardPage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setTitle("Dashboard.");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void loadQuizPage() {
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


    public void loadLoginPage() {
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

        private boolean passwordsMatch(String enteredPassword, String storedPassword) {
            // Implement a secure password comparison method, for example using BCrypt
            // Example: return BCrypt.check(enteredPassword, storedPassword);
            // Note: You need to add the BCrypt library to your project dependencies.
            return enteredPassword.equals(storedPassword);
        }

        private void showAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }

        private void showErrorMessage(String message) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
    }

