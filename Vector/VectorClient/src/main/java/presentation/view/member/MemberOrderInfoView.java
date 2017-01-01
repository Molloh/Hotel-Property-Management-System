package presentation.view.member;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.member.MemberOrderInfoViewControllerImpl;
import presentation.controller.service.member.MemberOrderInfoViewControllerService;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/4
 * @description
 */
public class MemberOrderInfoView implements Initializable {
    @FXML
    private Label orderId_label;
    @FXML
    private Label hotel_label;
    @FXML
    private Label type_label;
    @FXML
    private Label yesOrNo_label;
    @FXML
    private Label start_label;
    @FXML
    private Label end_label;
    @FXML
    private Label last_label;
    @FXML
    private Label people_label;

    @FXML
    private AnchorPane missionPane;

    private MemberOrderInfoViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MemberOrderInfoViewControllerImpl.getInstance();
        controller.setOrderId(SingletonItem.getInstance().getOrderId());

        orderId_label.setText(SingletonItem.getInstance().getOrderId());
        hotel_label.setText(controller.getHotelName());
        type_label.setText(controller.getRoomType().name());
        yesOrNo_label.setText(String.valueOf(controller.getChildExist()));
        start_label.setText(controller.getCheckInTime());
        end_label.setText(controller.getCheckOutTime());
        last_label.setText("");
        people_label.setText(String.valueOf(controller.getNumOfGuest()));
    }

    @FXML
    private void handleReturn() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberOrder_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleComment() {
        try {
            Stage primaryStage = (Stage)orderId_label.getScene().getWindow();

            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("view/PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            MemberOrderCommentView controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
