package presentation.view.sign;

import common.AccountType;
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
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.sign.SignViewControllerImpl;
import presentation.controller.service.sign.SignViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/11/5
 * @description Controller for login view
 */
public class SignInView implements Initializable{
    @FXML
    private Button signIn_btn;
    @FXML
    private Button signUp_btn;

    @FXML
    private TextField account_field;
    @FXML
    private PasswordField password_field;

    @FXML
    private Label alert_label;

    private String userId;
    private String fxmlPath;
    private SignViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = SignViewControllerImpl.getInstance();
        String userId = SingletonItem.getInstance().getActivateId();
        if(userId != null)
            account_field.setText(userId);
    }

    @FXML
    private void handleSignIn() throws IOException {
        AccountType TYPE = controller.signIn(account_field.getText(), password_field.getText());
        if(TYPE != AccountType.Fail) {
            userId = account_field.getText();
            SingletonItem.getInstance().setActivateId(userId);
        }
        switch (TYPE) {
            case Fail:
                alert_label.setText("WRONG!");
                break;
            case Member:
                fxmlPath = ViewFxmlPath.MemberRoot_View_Path;
                break;
            case Hotel:
                fxmlPath = ViewFxmlPath.HotelRoot_View_Path;
                break;
            case Manager:
                fxmlPath = ViewFxmlPath.ManagerRoot_View_Path;
                break;
            case Marketer:
                fxmlPath = ViewFxmlPath.MarketerRoot_View_Path;
                break;
            default:
                break;
        }

        if(fxmlPath != null) {
            Stage stage = (Stage)signIn_btn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void handleSignUp() throws IOException {
        Stage stage = (Stage)signUp_btn.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource(ViewFxmlPath.SignUp_View_Path));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
