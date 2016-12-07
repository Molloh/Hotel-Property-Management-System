package presentation.view.member;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.controller.SingletonId;
import presentation.controller.impl.MemberInfoViewControllerImpl;
import presentation.controller.service.MemberInfoViewControllerService;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Molloh
 * @version 2016/11/25
 * @description
 */
public class MemberInfoView implements Initializable {
    @FXML
    private TextField name_field;
    @FXML
    private DatePicker date_field;

    @FXML
    private Button modify_btn;
    @FXML
    private Button confirm_btn;
    @FXML
    private Button cancel_btn;

    @FXML
    private Label credit_label;

    private MemberInfoViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MemberInfoViewControllerImpl.getInstance();
        controller.setMemberId(SingletonId.getInstance().getActivateId());

        credit_label.setText(controller.getCredit());
        //name_field.setText(controller.getMemberName());
        //date_field.setAccessibleText(controller.getBirthDay());
    }

    @FXML
    private void handleModifyMission() {
        name_field.setEditable(true);
        date_field.setEditable(true);

        modify_btn.setVisible(false);
        confirm_btn.setVisible(true);
        cancel_btn.setVisible(true);
    }
}
