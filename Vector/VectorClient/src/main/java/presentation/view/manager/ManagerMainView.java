package presentation.view.manager;/**
 * @ author Molloh
 * @ version 2016/11/6
 * @ description
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import presentation.controller.ViewFxmlPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerMainView implements Initializable {
    @FXML
    private Button signOut_btn;

    private String fxmlPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleMissionSwitch(ActionEvent event) throws IOException{
        if(event.getSource() == signOut_btn) {
            fxmlPath = ViewFxmlPath.SignIn_View_Path;
        }

        Stage stage = (Stage)signOut_btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}

