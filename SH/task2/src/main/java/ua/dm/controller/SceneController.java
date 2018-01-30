package ua.dm.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;
import ua.dm.Main;
import ua.dm.db.UserDB;

import java.sql.*;


/**
 * Created by Макс on 20.01.2018.
 */
public class SceneController extends Window {
    private Main main;
    private SceneController sceneController;
    private UserDB userDB = new UserDB();
    private int level;
    private int experience;
    public static String userName;
    public static String password;

    @FXML
    Label daysToEnd;

    @FXML
    Button cancel;

    @FXML
    Button button;

    @FXML
    Button seeDays;

    @FXML
    public void handleExit() {
        System.exit(0);
    }

    @FXML
    public void handleClick() {
        System.out.println(getUserName() + "\\\\\\\\\\"+getPassword());
        userDB.UpdateExp(getUserName(),getPassword());
    }

    @FXML
    public void giveDays(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(sceneController);
        alert.setTitle("Days");
        alert.setHeaderText("Left " + userDB.day(getUserName(), getPassword()) + " paid days!\n ^_^\nHave a nice day!");
        alert.showAndWait();
    }

    public void str(String user, String pass){
        setUserName(user);
        setPassword(pass);
    }

    public SceneController() throws SQLException {
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public SceneController getSceneController() {
        return sceneController;
    }

    public void setSceneController(SceneController sceneController) {
        this.sceneController = sceneController;
    }

    @FXML
    public void initialize() {

    }
}
