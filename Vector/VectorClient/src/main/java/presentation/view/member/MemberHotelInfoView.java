package presentation.view.member;

import common.RoomType;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.member.MemberHotelInfoViewControllerImpl;
import presentation.controller.service.member.MemberHotelInfoViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/4
 * @description
 */
public class MemberHotelInfoView implements Initializable {
    @FXML
    private Button reserve_btn;
    @FXML
    private Button return_btn;

    @FXML
    private Label name_label;
    @FXML
    private Label star_label;
    @FXML
    private Label point_label;
    @FXML
    private Label price_label;

    @FXML
    private TextArea description_area;

    @FXML
    private ChoiceBox<RoomType> roomType_choice;

    @FXML
    private ListView<String> comment_list;

    @FXML
    private AnchorPane missionPane;

    private MemberHotelInfoViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MemberHotelInfoViewControllerImpl.getInstance();
        controller.setHotelId(SingletonItem.getInstance().getHotelId());

        name_label.setText(controller.getHotelName());
        star_label.setText(controller.getHotelStar());
        point_label.setText(controller.getHotelPoint());
        description_area.setText(controller.getHotelDiscription());

        roomType_choice.getItems().addAll(RoomType.SINGLE, RoomType.DOUBLE, RoomType.FAMILY);
        roomType_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    price_label.setText("￥" + controller.getHotelRoomPrice(roomType_choice.getItems().get((Integer)new_val)));
                }
        );

        comment_list.getItems().add(controller.getComment());
    }

    @FXML
    private void handleReverseOrReturn(ActionEvent event) throws IOException {
        if(event.getSource() == reserve_btn) {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberHotelOrder_View_Path)));
        }else if(event.getSource() == return_btn) {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberHotelList_View_Path)));
        }
    }
}
