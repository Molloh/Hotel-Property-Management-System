package presentation.view.manager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.sign.SignViewControllerImpl;
import presentation.controller.service.sign.SignViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/3
 * @description
 */
public class ManagerRootView implements Initializable {
    @FXML
    private Button searchUser_btn;
    @FXML
    private Button addUser_btn;
    @FXML
    private Button signOut_btn;

    @FXML
    private Label name_label;
    @FXML
    private Label id_label;

    @FXML
    private BorderPane missionPane;

    private String fxmlPath;
    private SignViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = SignViewControllerImpl.getInstance();
        setMissionPane(ViewFxmlPath.ManagerUserSearch_View_Path);
    }

    @FXML
    private void handleMissionSwitch(ActionEvent event) throws IOException {
        if(event.getSource() == signOut_btn) {
            fxmlPath = ViewFxmlPath.SignIn_View_Path;
            Stage stage = (Stage)signOut_btn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            controller.signOut();
        }else if(event.getSource() == searchUser_btn) {
            fxmlPath = ViewFxmlPath.ManagerUserSearch_View_Path;
        }else if(event.getSource() == addUser_btn) {
            fxmlPath = ViewFxmlPath.ManagerUserAdd_View_Path;
        }

        setMissionPane(fxmlPath);
    }

    @FXML
    private void handleExit() {
        controller.signOut();
        System.exit(0);
    }

    private void setMissionPane(String fxmlPath) {
        try {
            missionPane.setCenter(FXMLLoader.load(getClass().getResource(fxmlPath)));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
