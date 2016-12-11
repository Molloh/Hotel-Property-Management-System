package presentation.view.sign;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.sign.SignViewControllerImpl;
import presentation.controller.service.sign.SignViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ author Molloh
 * @ version 2016/11/5
 * @ description Controller for login view
 */
public class SignUpView implements Initializable {
    @FXML
    private Button signUp_btn;

    @FXML
    private RadioButton normal_radio;
    @FXML
    private RadioButton enterprise_radio;

    @FXML
    private TextField name_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private PasswordField repeat_field;

    @FXML
    private Label alert_label;

    private SignViewControllerService controller;

    private boolean isNormalMember;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = SignViewControllerImpl.getInstance();

        ToggleGroup member_group = new ToggleGroup();
        normal_radio.setToggleGroup(member_group);
        enterprise_radio.setToggleGroup(member_group);

        member_group.selectedToggleProperty().addListener(
                (ObservableValue<?extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
                    if(member_group.getSelectedToggle() == normal_radio) {
                        isNormalMember = true;
                    }else if(member_group.getSelectedToggle() == enterprise_radio){
                        isNormalMember = false;
                    }
                }
        );
    }

    @FXML
    private void handleSignUp() throws IOException {
        if(!repeat_field.getText().equals(password_field.getText())) {
            alert_label.setText("WRONG!");
        }else {
            String memberId = controller.signUp(name_field.getText(), password_field.getText(), isNormalMember);
            if(!memberId.equals("FAIL")) {
                SingletonItem.getInstance().setActivateId(memberId);
                switchToSignIn();
            }else {
                alert_label.setText("FAIL");
            }
        }
    }

    @FXML
    private void handleCancel() throws IOException {
        switchToSignIn();
    }

    private void switchToSignIn() throws IOException {
        Stage stage = (Stage)signUp_btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(ViewFxmlPath.SignIn_View_Path));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
