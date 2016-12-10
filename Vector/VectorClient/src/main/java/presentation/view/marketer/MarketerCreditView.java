package presentation.view.marketer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MarketerCreditView implements Initializable {
    @FXML
    private TextField memberId;
    @FXML
    private TextField credit;

    @FXML
    private Label cost;

    @FXML
    private Button confirm_btn;
    @FXML
    private Button cancel_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    private void handleCreditCharge() {

    }

    @FXML
    private void handleCancel() {

    }

}
