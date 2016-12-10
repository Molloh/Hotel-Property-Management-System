package presentation.view.sign;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import presentation.common.SingletonId;
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
    private Button cancel_btn;

    @FXML
    private TextField name_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private PasswordField repeat_field;

    @FXML
    private Label alert_label;

    private SignViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = SignViewControllerImpl.getInstance();
    }

    @FXML
    private void handleSignUp() throws IOException {
        if(!repeat_field.getText().equals(password_field.getText())) {
            alert_label.setText("WRONG!");
        }else {
            String memberId = controller.signUp(name_field.getText(), password_field.getText());
            if(!memberId.equals("FAIL")) {
                SingletonId.getInstance().setActivateId(memberId);
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
