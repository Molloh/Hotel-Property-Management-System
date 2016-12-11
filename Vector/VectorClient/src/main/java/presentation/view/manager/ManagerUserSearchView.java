package presentation.view.manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ author Molloh
 * @ version 2016/11/6
 * @ description
 */
public class ManagerUserSearchView implements Initializable {
    @FXML
    private TextField userId_field;

    @FXML
    private AnchorPane missionPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    @FXML
    private void handleEditUser() {
        SingletonItem.getInstance().setSearchedId(userId_field.getText());
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.ManagerUserEdit_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

