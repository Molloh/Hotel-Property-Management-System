package presentation.view.marketer;

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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MarketerRootView implements Initializable {
    @FXML
    private Button credit_btn;
    @FXML
    private Button promotion_btn;
    @FXML
    private Button abOrder_btn;
    @FXML
    private Button signOut_btn;
    @FXML
    private Button exit_btn;

    @FXML
    private Label name_label;
    @FXML
    private Label id_label;

    @FXML
    private BorderPane missionPane;

    private String fxmlPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMissionPane(ViewFxmlPath.MarketerOrder_View_Path);
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
        }else if(event.getSource() == credit_btn) {
            fxmlPath = ViewFxmlPath.MarketerCredit_View_Path;
        }else if(event.getSource() == promotion_btn) {
            fxmlPath = ViewFxmlPath.MarketerPromotion_View_Path;
        }else if(event.getSource() == abOrder_btn) {
            fxmlPath = ViewFxmlPath.MarketerOrder_View_Path;
        }

        setMissionPane(fxmlPath);
    }

    @FXML
    private void handleExit() {
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
