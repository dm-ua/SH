package ua.dm.controller;

import ua.dm.Main;
import ua.dm.db.UserDB;
import ua.dm.entity.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Макс on 20.01.2018.
 */
public class LogInController {
    private Main main;
    private UserDB userDB;
    private String logName;
    private String logPassword;

    @FXML
    private Button ok;

    @FXML
    private Button registration;

    @FXML
    private Button cancel;

    @FXML
    private TextField userField;

    @FXML
    private PasswordField passwordField;

    public void setMain(Main main) {
        this.main = main;
    }

    public LogInController() throws SQLException {
        userDB = new UserDB();
    }

    public Main getMain(){return main;}

    public boolean showRegistrationScene(User user) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(RegistrationController.class.getResource("/scene/Registration.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage registerStage = new Stage();
            registerStage.setTitle("Registration");
            registerStage.initModality(Modality.WINDOW_MODAL);
            registerStage.initOwner(null);
            Scene scene = new Scene(page);
            registerStage.setScene(scene);
            RegistrationController controller = loader.getController();
            controller.setRegisterStage(registerStage);
            controller.setUser(user);
            registerStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return false;
        }
    }

    @FXML
    public void handleLogIn() throws ClassNotFoundException, SQLException {

        if (userDB.logIn(userField.getText(), passwordField.getText())){
            userDB.newDay(userField.getText(), passwordField.getText());
                main.authenticationOK();
            }
    }

    @FXML
    public void handleCancel() {
        System.exit(0);
    }

    @FXML
    private void handleRegistration() {
        User tempUser = new User();
        System.out.println(tempUser.toString());
        boolean okClicked = showRegistrationScene(tempUser);
        System.out.println(tempUser.toString());
        if (okClicked) {
            userDB.insertIntoDB(tempUser);

        }
    }
    @FXML
    public void initialize() {

    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getLogPassword() {
        return logPassword;
    }

    public void setLogPassword(String logPassword) {
        this.logPassword = logPassword;
    }



}
