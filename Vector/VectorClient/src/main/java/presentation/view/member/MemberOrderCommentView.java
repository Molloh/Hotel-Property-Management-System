package presentation.view.member;

import businessLogic.impl.HotelBlServiceImpl;
import businessLogic.service.HotelBlService;
import common.ResultMessage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;


import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MemberOrderCommentView implements Initializable {
    @FXML
    private TextArea commentArea;

    private Stage dialogStage;
    private HotelBlService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = HotelBlServiceImpl.getInstance();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleComment() {
        ResultMessage msg = controller.comment(commentArea.getText());
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText("");
        if(msg == ResultMessage.SUCCEED) {
            alert.setContentText("评价成功！");
            alert.showAndWait();
        }else {
            alert.setContentText("评价失败！");
            alert.showAndWait();
        }
        dialogStage.close();
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}

