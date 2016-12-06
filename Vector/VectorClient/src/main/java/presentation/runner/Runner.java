package presentation.runner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.controller.ViewFxmlPath;

/**
 * @author Molloh
 * @version 2016/11/27
 * @description
 */
public class Runner extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource(ViewFxmlPath.SignIn_View_Path));
        //SignInView signInView = new SignInView("sss");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


