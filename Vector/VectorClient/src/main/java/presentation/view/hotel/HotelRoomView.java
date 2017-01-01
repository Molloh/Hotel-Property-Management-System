package presentation.view.hotel;

import common.ResultMessage;
import common.RoomType;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentation.common.SingletonItem;
import presentation.controller.impl.hotel.HotelRoomViewControllerImpl;
import presentation.controller.service.hotel.HotelRoomViewControllerService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description 酒店更新房间界面
 */
public class HotelRoomView implements Initializable {
    @FXML
    private ChoiceBox<String> mission_choice;
    @FXML
    private ComboBox<RoomType> roomType_combo;
    @FXML
    private TextField num_field;
    @FXML
    private Label price_label;
    @FXML
    private TextField price_field;

    private HotelRoomViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = HotelRoomViewControllerImpl.getInstance();
        controller.setHotelId(SingletonItem.getInstance().getActivateId());

        price_field.setVisible(false);
        price_label.setVisible(false);

        mission_choice.getItems().addAll("更新入住", "更新退房", "设置房间间数量");
        roomType_combo.getItems().addAll(RoomType.SINGLE, RoomType.DOUBLE, RoomType.FAMILY);
        mission_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    String command = mission_choice.getItems().get((Integer)new_val);
                    if(command.equals("设置房间数量")){
                        price_field.setVisible(true);
                        price_label.setVisible(true);
                    }
                }
        );
    }

    //处理更新房间信息事件
    @FXML
    private void handleUpdate() {
        if(price_label.isVisible() && price_field.isVisible()) {
            RoomType type = roomType_combo.getValue();
            ResultMessage msg;
            if(type != null) {
                msg = controller.initializeRoom(type, Integer.valueOf(num_field.getText()), Integer.valueOf(price_field.getText()));
                popUp(msg);
            }
        }else {
            String command = mission_choice.getValue();
            ResultMessage msg = ResultMessage.FAIL;
            if(command.equals("更新入住")) {
                msg = controller.checkinRoom(roomType_combo.getValue(), Integer.valueOf(num_field.getText()));
            }else if(command.equals("更新退房")) {
                msg = controller.checkoutRoom(roomType_combo.getValue(), Integer.valueOf(num_field.getText()));
            }
            popUp(msg);
        }
    }

    @FXML
    private void handleClear() {

    }

    //弹出窗口
    private void popUp(ResultMessage msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Tips");
        alert.setHeaderText("");
        if(msg == ResultMessage.SUCCEED) {
            alert.setContentText("更新成功！");
            alert.showAndWait();
        }else {
            alert.setContentText("更新失败！");
            alert.showAndWait();
        }
    }
}
