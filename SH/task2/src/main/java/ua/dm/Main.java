package ua.dm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ua.dm.controller.LogInController;
import ua.dm.controller.SceneController;
import ua.dm.db.UserDB;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Макс on 20.01.2018.
 */
public class Main extends Application {
    private Stage stage;
    private Scene finallScene;
    LogInController loginController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.stage = primaryStage;
        primaryStage.setTitle("Task 2");
        initRootPane();
    }
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserDB.CreateDB();
        launch(args);
    }


    public void authenticationOK() {
        FXMLLoader menuBarLoader = new FXMLLoader();
        menuBarLoader.setLocation(Main.class.getResource("/scene/Scene.fxml"));
        try {
            AnchorPane rootPane = (AnchorPane) menuBarLoader.load();
            SceneController menubarController;
            menubarController= menuBarLoader.getController();
            menubarController.setMain(this);
            finallScene = new Scene(rootPane);
            stage.setScene(finallScene);
            stage.show();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void initRootPane() throws Exception {
        FXMLLoader loginLoader = new FXMLLoader();
        loginLoader.setLocation(Main.class.getResource("/scene/LogIn.fxml"));
        AnchorPane loginPane = (AnchorPane) loginLoader.load();
        loginController = loginLoader.<LogInController>getController();
        loginController.setMain(this);
        Scene loginScene = new Scene(loginPane);
        stage.setScene(loginScene);
        loginController.handleLogIn();
        stage.show();
    }
}
