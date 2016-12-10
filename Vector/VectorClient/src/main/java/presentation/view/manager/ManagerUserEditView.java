package presentation.view.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import presentation.common.ViewFxmlPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class ManagerUserEditView implements Initializable {
    @FXML
    private TextField userName;
    @FXML
    private Label userId;

    @FXML
    private AnchorPane missionPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleCancel() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.ManagerUserSearch_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
