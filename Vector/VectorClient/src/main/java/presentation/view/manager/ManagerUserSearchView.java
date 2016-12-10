package presentation.view.manager;/**
 * @ author Molloh
 * @ version 2016/11/6
 * @ description
 */

import common.AccountType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import presentation.common.ViewFxmlPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerUserSearchView implements Initializable {
    @FXML
    private TextField userId_field;

    @FXML
    private Button search_btn;

    @FXML
    private AnchorPane missionPane;

    private String fxmlPath;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleEditUser() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.ManagerUserEdit_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

