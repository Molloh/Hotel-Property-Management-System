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
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.member.MemberRootViewControllerImpl;
import presentation.controller.impl.sign.SignViewControllerImpl;
import presentation.controller.service.member.MemberRootViewControllerService;
import presentation.controller.service.sign.SignViewControllerService;

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
    	controller = MemberRootViewControllerImpl.getInstance();
    	
        String memberId = SingletonItem.getInstance().getActivateId();
        controller.setMemberId(memberId);

        memberName_label.setText(controller.getMemberName());
        memberId_label.setText(memberId);

        setMissionPane(ViewFxmlPath.MemberHotelList_View_Path);
    }

    @FXML
    private void handleMissionSwitch(ActionEvent event) throws IOException {
        if(event.getSource() == signOut_btn) {
            SignViewControllerImpl.getInstance().signOut();

            fxmlPath = ViewFxmlPath.SignIn_View_Path;
            Stage stage = (Stage)signOut_btn.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        }else if(event.getSource() == modifyInfo_btn) {
            fxmlPath = ViewFxmlPath.MemberInfo_View_Path;
        }else if(event.getSource() == myOrder_btn) {
            fxmlPath = ViewFxmlPath.MemberOrder_View_Path;
        }else if(event.getSource() == home_btn) {
            fxmlPath = ViewFxmlPath.MemberHotelList_View_Path;
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