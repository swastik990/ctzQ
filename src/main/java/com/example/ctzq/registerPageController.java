package com.example.ctzq;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.sql.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class registerPageController implements Initializable {

    @FXML
    private TextField user_name;

    @FXML
    private DatePicker dateOfBirth;

    @FXML
    private ChoiceBox gender;

    @FXML
    private ChoiceBox nationality;

    @FXML
    private TextField pass_word;

    @FXML
    private Button sign_up;

    @FXML
    private Button backto_login;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        backto_login.setOnAction(event -> loadLoginPage());

        sign_up.setOnAction(event -> {
            //Retrieving values to store in database
            String userName = user_name.getText();
            LocalDate dob = dateOfBirth.getValue();
            String selectedGender = (String) gender.getValue();
            String selectedNationality = (String) nationality.getValue();
            String password = pass_word.getText();

            //calling method
            storeInDatabaseandtxt(userName, dob, selectedGender, selectedNationality, password);
        });

    }




    //method to store values
    private void storeInDatabaseandtxt(String userName, LocalDate dob, String gender, String nationality, String password) {
        if (userName.isEmpty() || dob == null || gender == null || gender.isEmpty() || nationality == null || nationality.isEmpty() || password.isEmpty()) {
            // Display an error message for empty fields
            showErrorMessage("Credentials cannot be empty.");
            return;
        }
        //to store the components in database
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ctzqdb", "root", "Infiniti@111");
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_info (user_name, dob, gender, nationality, password) VALUES (?, ?, ?, ?, ?)")) {

            preparedStatement.setString(1, userName);
            preparedStatement.setDate(2, Date.valueOf(dob));
            preparedStatement.setString(3, gender);
            preparedStatement.setString(4, nationality);
            preparedStatement.setString(5, password);

            //to execute
            preparedStatement.executeUpdate();

            //to store the components in a txt files
            try (FileWriter writer = new FileWriter("Candidate.txt", true)) {
                String userInfo = String.format("Username: %s, DOB: %s, Gender: %s, Nationality: %s, Password: %s%n",
                        userName, dob, gender, nationality, password);
                writer.write(userInfo);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //After registering the credentials
            showSuccessMessage("User registration successful, Now Login!");

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }
    private void showSuccessMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success!");
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

    public static void resister(ActionEvent event, String user_name, String password, String dob, String gender, String nationality){
        Connection connection = null;
        PreparedStatement insert = null;
        PreparedStatement checkUser = null;
        ResultSet resultSet = null;

        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ctzqdb","root","Infiniti@111");
            checkUser = connection.prepareStatement("SELECT * FROM user_info WHERE user_name = ?");
            checkUser.setString(1,"user_name");
            resultSet = checkUser.executeQuery();

            if(resultSet.isBeforeFirst()){
                System.out.println("Username Already Exists!");

            }
            else{
                insert = connection.prepareStatement("INSERT INTO user_info (user_name, password, dob, gender, nationality( ?, ?, ?, ?, ?)");
                insert.setString(1,user_name);
                insert.setString(2,password);
                insert.setString(3,dob);
                insert.setString(4,gender);
                insert.setString(5,nationality);
                insert.execute();

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if(resultSet != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(checkUser != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(insert != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try{
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}