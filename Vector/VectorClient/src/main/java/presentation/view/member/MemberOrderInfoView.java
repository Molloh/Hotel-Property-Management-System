package presentation.view.member;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import presentation.common.ViewFxmlPath;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/4
 * @description
 */
public class MemberOrderInfoView implements Initializable {
    @FXML
    private Label orderId;
    @FXML
    private Label hotel;
    @FXML
    private Label room;
    @FXML
    private Label children;

    @FXML
    private AnchorPane missionPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleReturn() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberOrder_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
