package presentation.view.member;

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
import presentation.controller.SingletonId;
import presentation.controller.ViewFxmlPath;
import presentation.controller.impl.MemberMainViewControllerImpl;
import presentation.controller.impl.MemberRootViewControllerImpl;
import presentation.controller.service.MemberMainViewControllerService;
import presentation.controller.service.MemberRootViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/3
 * @description Member Interface的界面根节点
 */
public class MemberRootView implements Initializable {
    @FXML
    private Label memberName_label;
    @FXML
    private Label memberId_label;

    @FXML
    private Button modifyInfo_btn;
    @FXML
    private Button myOrder_btn;
    @FXML
    private Button signOut_btn;
    @FXML
    private Button home_btn;

    @FXML
    private BorderPane missionPane;

    private String fxmlPath;

    private MemberRootViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        String memberId = SingletonId.getInstance().getActivateId();
        controller = MemberRootViewControllerImpl.getInstance();
        controller.setMemberId(memberId);

        memberName_label.setText(controller.getMemberName());
        memberId_label.setText(memberId);

        setMissionPane(ViewFxmlPath.MemberMain_View_Path);
    }

    @FXML
    private void handleMissionSwitch(ActionEvent event) throws IOException {
        if(event.getSource() == signOut_btn) {
            //sign out, and switch stage
            fxmlPath = ViewFxmlPath.SignIn_View_Path;
            Stage stage = (Stage)signOut_btn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            controller.signOut();


        }else if(event.getSource() == modifyInfo_btn) {
            fxmlPath = ViewFxmlPath.MemberInfo_View_Path;
        }else if(event.getSource() == myOrder_btn) {
            fxmlPath = ViewFxmlPath.MemberOrder_View_Path;
        }else if(event.getSource() == home_btn) {
            fxmlPath = ViewFxmlPath.MemberMain_View_Path;
        }

        if(fxmlPath != null)
            setMissionPane(fxmlPath);
    }

    //load mission pane
    private void setMissionPane(String fxmlPath) {
        try {
            missionPane.setCenter(FXMLLoader.load(getClass().getResource(fxmlPath)));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}