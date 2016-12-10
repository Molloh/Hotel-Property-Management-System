package presentation.view.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonId;
import presentation.common.ViewFxmlPath;
import presentation.controller.Hotel;
import presentation.controller.impl.member.MemberMainViewControllerImpl;
import presentation.controller.service.member.MemberMainViewControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/11/25
 * @description
 */
public class MemberHotelListView implements Initializable {
    @FXML
    private ComboBox<String> province_combo;
    @FXML
    private ComboBox<String> city_combo;
    @FXML
    private ComboBox<String> cbd_combo;

    @FXML
    private TableView<Hotel> hotel_list;
    @FXML
    private TableColumn<Hotel, String> hotelbtn_column;
    @FXML
    private TableColumn<Hotel, String> hotelStar_column;
    @FXML
    private TableColumn<Hotel, String> hotelPoint_column;
    @FXML
    private TableColumn<Hotel, String> hotelAddress_column;
    @FXML
    private TableColumn<Hotel, String> hotelPrice_column;

    @FXML
    private AnchorPane missionPane;

    private String memberId;
    private String fxmlPath;
    private MemberMainViewControllerService controller;

    public MemberHotelListView() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources){
        memberId = SingletonId.getInstance().getActivateId();
        controller = new MemberMainViewControllerImpl(memberId);

        initTable();

    }

    private void initTable() {
        hotelStar_column.setCellValueFactory(cellData -> cellData.getValue().hotelStarProperty());
        hotelbtn_column.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());
        hotelPoint_column.setCellValueFactory(cellData -> cellData.getValue().hotelPointProperty());
        hotelPrice_column.setCellValueFactory(cellData -> cellData.getValue().hotelPriceProperty());
        hotelAddress_column.setCellValueFactory(cellData -> cellData.getValue().hotelAddressProperty());

        hotelbtn_column.setCellFactory(param -> new HotelBtnCell());

        ObservableList<Hotel> data = FXCollections.observableArrayList(
                new Hotel("格林豪泰", "*****", "5.0", "南大仙林", "300起")
        );

        hotel_list.setItems(data);
    }

    class HotelBtnCell extends TableCell<Hotel, String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Button hotel_btn;
            if(item != null) {
                hotel_btn = new Button(item);
                setGraphic(hotel_btn);
                hotel_btn.setOnAction(event -> {
                    try {
                        missionPane.getChildren().clear();
                        missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberHotelInfo_View_Path)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }else {
                setGraphic(null);
            }
        }
    }



}
