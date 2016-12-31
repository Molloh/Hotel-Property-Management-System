package presentation.view.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.hotel.HotelOrderExeViewControllerImpl;
import presentation.controller.impl.member.MemberOrderInfoViewControllerImpl;
import presentation.controller.service.hotel.HotelOrderExeViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class HotelOrderExeView implements Initializable {
    @FXML
    private AnchorPane missionPane;

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
    private Button revoke_btn;
    @FXML
    private Button exe_btn;

    private HotelOrderExeViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = HotelOrderExeViewControllerImpl.getInstance();
        controller.setOrderId(SingletonItem.getInstance().getOrderId());

        revoke_btn.setVisible(true);
        exe_btn.setVisible(true);

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
    private void handleRevoke() {

    }

    @FXML
    private void handleExe() {

    }

    @FXML
    private void handleDelay() {

    }

    @FXML
    private void handleCancel() throws IOException {
        missionPane.getChildren().clear();
        missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.HotelOrder_View_Path)));
    }
}
