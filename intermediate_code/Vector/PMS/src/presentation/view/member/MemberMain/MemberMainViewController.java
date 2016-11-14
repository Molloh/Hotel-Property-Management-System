package presentation.view.member.MemberMain;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import presentation.view.login.LoginView;

/**
 * @ author Molloh
 * @ version 2016/11/5
 * @ description Controller for login view
 */
public class MemberMainViewController {
    @FXML
    private Button login_btn;
    @FXML
    private Button signup_btn;

    // Reference to the login view
    private LoginView loginView;

    /**
     * The constructor
     */
    public MemberMainViewController() {}

    /**
     * Initializes the controller class
     */
    private void initialize() {}

    /**
     * @ param LoginView
     */
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    private void handleButtonClickAction(ActionEvent event) {

    }

}
