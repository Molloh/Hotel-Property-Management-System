package presentation.view.member;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import presentation.common.ViewFxmlPath;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/4
 * @description
 */
public class MemberHotelInfoView implements Initializable {
    @FXML
    private Button reserve_btn;
    @FXML
    private Button return_btn;

    @FXML
    private AnchorPane missionPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleReverseOrReturn(ActionEvent event) throws IOException {
        if(event.getSource() == reserve_btn) {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberHotelOrder_View_Path)));
        }else if(event.getSource() == return_btn) {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberHotelList_View_Path)));
        }
    }
}
