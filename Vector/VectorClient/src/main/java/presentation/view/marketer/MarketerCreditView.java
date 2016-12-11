package presentation.view.marketer;

import common.ResultMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.controller.impl.marketer.MarketerCreditViewControllerImpl;
import presentation.controller.service.marketer.MarketerCreditViewControllerService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MarketerCreditView implements Initializable {
    @FXML
    private TextField memberId_field;
    @FXML
    private TextField credit_field;
    @FXML
    private TextField charge_field;

    ResultMessage message;

    private MarketerCreditViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new MarketerCreditViewControllerImpl();
    }

    @FXML
    private void handleCharge() {
        message = controller.chargeCredit(memberId_field.getText(), Integer.parseInt(credit_field.getText()));
    }

    @FXML
    private void handleCancelCharge() {
        memberId_field.setText(null);
        charge_field.setText(null);
        credit_field.setText(null);
    }

}
