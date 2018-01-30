package ua.dm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.dm.entity.User;

import java.time.LocalDate;

/**
 * Created by Макс on 20.01.2018.
 */
public class RegistrationController {
    private Stage registerStage;
    private User user;
    private boolean okClicked = false;
    private LocalDate registrDay = LocalDate.now();


    @FXML
    private TextField userName;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField verificationField;

    @FXML
    private Button cancel;

    @FXML
    private Button register;

    public void setRegisterStage(Stage registerStage) {
        this.registerStage = registerStage;
    }

    public void setUser(User user) {
        this.user = user;
        userName.setText(user.getUserName());
        passwordField.setText(user.getPassword());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    private boolean isInputValid() {
        String errorMessage = "";
        if (userName.getText() == null || userName.getText().length() <
                3 || userName.getText().matches("[-a-zA-Z0-9._]")) {
            errorMessage += "Enter user name! Minimum length 3! Can only consist of: '[a-z][A-Z][0-9]-._'\n";
        }
        if (passwordField.getText() == null || passwordField.getText().length()
                == 0) {
            errorMessage += "Enter password!\n";
        }
        if (verificationField.getText() == null || verificationField.getText().length()
                == 0) {
            errorMessage += "Enter password!\n";
        }
        if (passwordField.getText().compareTo(verificationField.getText()) != 0) {
            errorMessage += "Password is't correct!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(registerStage);
            alert.setTitle("Exeption");
            alert.setHeaderText("Check the information");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void handleRegister(){
        if (isInputValid()) {
            user.setUserName(userName.getText());
            user.setPassword(passwordField.getText());
            user.setLevel(0);
            user.setExperience(0);
            user.setDate(getRegistrDay().toString());
            user.setType("paid");
            user.setDays(5);
            okClicked = true;
            registerStage.close();
        }
    }

    @FXML
    public void handleCancel() {
        registerStage.close();
    }

    @FXML
    public void initialize() {

    }

    public LocalDate getRegistrDay() {
        return registrDay;
    }

    public void setRegistrDay(LocalDate registrDay) {
        this.registrDay = registrDay;
    }
}