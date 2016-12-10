package presentation.view.marketer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
public class MarketerPromotionView implements Initializable {
    @FXML
    AnchorPane missionPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleProEdit() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MarketerProInfo_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddPro() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MarketerProAdd_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
