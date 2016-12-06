package presentation.view.member;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import presentation.controller.SingletonId;
import presentation.controller.impl.MemberMainViewControllerImpl;
import presentation.controller.service.MemberMainViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/11/25
 * @description
 */
public class MemberMainView implements Initializable {


    private String memberId;
    private String fxmlPath;
    private MemberMainViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        memberId = SingletonId.getInstance().getActivateId();
        controller = new MemberMainViewControllerImpl(memberId);

    }

    @FXML
    private void handleMissionSwitch(ActionEvent event) throws IOException{


    }
}
