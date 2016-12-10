package presentation.view.manager;

import common.AccountType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import presentation.controller.impl.manager.ManagerUserControllerImpl;
import presentation.controller.service.manager.ManagerUserControllerService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class ManagerUserAddView implements Initializable {
    @FXML
    private ComboBox<String> userType_combo;

    @FXML
    private PasswordField password_field;
    @FXML
    private PasswordField repeat_field;

    @FXML
    private Label alert_label;

    private ManagerUserControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = ManagerUserControllerImpl.getInstance();

        userType_combo.setValue(null);
        userType_combo.getItems().addAll(
                "普通会员","企业会员","酒店","营销人员"
        );
    }

    @FXML
    private void handleAddUser() {
        if(!password_field.getText().equals(repeat_field.getText())) {
            alert_label.setText("MisMatch!");
        }else {
            String userType = userType_combo.getAccessibleText();
            AccountType accountType;
            switch (userType) {
                case "普通会员":
                    accountType = AccountType.Member;
                    break;
                case "企业会员":
                    accountType = AccountType.Member;
                    break;
                case "酒店":
                    accountType = AccountType.Hotel;
                    break;
                case "营销人员":
                    accountType = AccountType.Marketer;
                    break;
                default:
                    accountType = AccountType.Fail;
                    break;
            }

            if(accountType != AccountType.Fail)
                controller.addUser(password_field.getText(), accountType);
            else
                alert_label.setText("MisMatch!");
        }
    }

    @FXML
    private void handleCancel() {
        password_field.setText(null);
        repeat_field.setText(null);
        userType_combo.setValue(null);
    }
}
