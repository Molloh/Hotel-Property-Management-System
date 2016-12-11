package presentation.view.hotel;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import presentation.common.SingletonItem;
import presentation.controller.impl.hotel.HotelRoomViewControllerImpl;
import presentation.controller.service.hotel.HotelRoomViewControllerService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/3
 * @description
 */
public class HotelRoomView implements Initializable {
    @FXML
    private ChoiceBox<String> mission_choice;
    @FXML
    private TextField num_field;
    @FXML
    private Label price_label;
    @FXML
    private TextField price_field;

    private HotelRoomViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new HotelRoomViewControllerImpl(SingletonItem.getInstance().getActivateId());

        mission_choice.getItems().addAll("更新入住", "更新退房", "设置单人间数量", "设置双人间数量", "设置大床房数量");
        mission_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    String command = mission_choice.getItems().get((Integer)new_val);
                    if(command.equals("设置单人间数量") || command.equals("设置双人间数量") || command.equals("设置大床房数量")){
                        price_field.setVisible(true);
                        price_label.setVisible(true);
                    }
                }
        );
    }

    @FXML
    private void handleUpdate() {
        if(price_label.isVisible() && price_field.isVisible()) {

        }else {

        }
    }

    @FXML
    private void handleClear() {

    }

}
