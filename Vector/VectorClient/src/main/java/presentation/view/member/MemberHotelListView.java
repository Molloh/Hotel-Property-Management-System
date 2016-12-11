package presentation.view.member;

import common.RoomType;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.Hotel;
import presentation.controller.impl.member.MemberHotelListViewControllerImpl;
import presentation.controller.service.member.MemberHotelListViewControllerService;
import vo.HotelVo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/11/25
 * @description
 */
public class MemberHotelListView implements Initializable {
    @FXML
    private ChoiceBox<String> province_choice;
    @FXML
    private ChoiceBox<String> city_choice;
    @FXML
    private ChoiceBox<String> cbd_choice;
    @FXML
    private ChoiceBox<Integer> star_choice;
    @FXML
    private ChoiceBox<RoomType> roomtype_choice;

    @FXML
    private TextField keyword_field;
    @FXML
    private TextField lowPrice_field;
    @FXML
    private TextField highPrice_field;
    @FXML
    private TextField lowPoint_field;
    @FXML
    private TextField highPoint_field;

    @FXML
    private TableView<Hotel> hotel_list;
    @FXML
    private TableColumn<Hotel, String> hotelName_column;
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

    private MemberHotelListViewControllerService controller;

    private ArrayList<HotelVo> hotelList;

    private ArrayList<HotelVo> hotelSubList;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        controller = MemberHotelListViewControllerImpl.getInstance();

        province_choice.setValue("省");
        city_choice.setValue("市");
        cbd_choice.setValue("商圈");

        initChoice();

    }

    private void initChoice() {
        star_choice.getItems().addAll(1, 2, 3, 4, 5);
        star_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    if(hotelList != null) {
                        hotelSubList = (ArrayList<HotelVo>) controller
                                .findByStars(star_choice.getItems().get(new_val.intValue()), hotelList);
                        initTable(hotelSubList);
                    }else {
                    	System.out.println("a");
                    }
                }
        );

        roomtype_choice.getItems().addAll(RoomType.SINGLE, RoomType.DOUBLE, RoomType.FAMILY);
        roomtype_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    if(hotelList != null) {
                        hotelList = (ArrayList<HotelVo>) controller
                                .findByRoomType(roomtype_choice.getItems().get((Integer)new_val), hotelList);
                    }
                }
        );

        ArrayList<String> province = (ArrayList<String>) controller.getProvinceList();
        for(String item : province) {
            province_choice.getItems().add(item);
        }
        province_choice.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> ov, Number old_val, Number new_val) -> {
                    ArrayList<String> city = (ArrayList<String>) controller
                            .getCityList(province.get(new_val.intValue()));
                    city_choice.getItems().clear();
                    cbd_choice.getItems().clear();
                    for(String item : city) {
                        city_choice.getItems().add(item);
                    }
                    if(!city_choice.getItems().isEmpty()) {
                        city_choice.getSelectionModel().selectedIndexProperty().addListener(
                                (ObservableValue<? extends Number> oV, Number oldVal, Number newVal) -> {
                                    ArrayList<String> cbd = (ArrayList<String>) controller
                                            .getCbdList(province_choice.getValue(), city.get(newVal.intValue()));
                                    cbd_choice.getItems().clear();
                                    for (String item : cbd) {
                                        cbd_choice.getItems().add(item);
                                    }
                                    cbd_choice.getSelectionModel().selectedIndexProperty().addListener(
                                            (ObservableValue<? extends Number> one, Number oldOne, Number newOne) -> {
                                                hotelList = (ArrayList<HotelVo>) controller.findByAddress(province_choice.getValue(), city_choice.getValue(), cbd.get(newOne.intValue()));
                                                initTable(hotelList);
                                            }
                                    );
                                }
                        );
                    }
                }
        );
    }

    @FXML
    private void selectHotelByPriceRange() {
        int low = Integer.parseInt(lowPrice_field.getText());
        int high = Integer.parseInt(highPrice_field.getText());
        RoomType type = roomtype_choice.getValue();
        if(hotelList != null && type != null ) {
            if(low > high) {

            }else {
                hotelList = (ArrayList<HotelVo>) controller.findByOriginalPrice(type, low, high, hotelList);
                initTable(hotelList);
            }
        }
    }

    @FXML
    private void selectHotelByPoint() {
        double low = Double.parseDouble(lowPoint_field.getText());
        double high = Double.parseDouble(highPoint_field.getText());
        if(hotelList != null) {
            hotelList = (ArrayList<HotelVo>) controller.findByPoint(low, high, hotelList);
            initTable(hotelList);
        }
    }

    @FXML
    private void selectHotelByKeyWord() {
        hotelList = (ArrayList<HotelVo>) controller.findByKeyword(keyword_field.getText());
        initTable(hotelList);
    }

    private void initTable(ArrayList<HotelVo> hotelList) {
        if(hotelList != null) {
            List<Hotel> propertyList = new ArrayList<>();
            for (HotelVo hotelVo : hotelList) {
                propertyList.add(
                        new Hotel(hotelVo.getHotelName() + "#" + hotelVo.getId(),
                        String.valueOf(hotelVo.getStars()),
                        String.valueOf(hotelVo.getNumOfpoint()),
                        hotelVo.getHotelPosition(),
                        String.valueOf(hotelVo.getOriginPrice(roomtype_choice.getValue()))));
            }
            ObservableList<Hotel> data = FXCollections.observableArrayList();
            for (Hotel hotel : propertyList) {
                data.add(hotel);
            }
            hotel_list.setItems(data);

            hotelName_column.setCellValueFactory(cellData -> cellData.getValue().hotelNameProperty());

            hotelStar_column.setCellValueFactory(cellData -> cellData.getValue().hotelStarProperty());
            hotelPoint_column.setCellValueFactory(cellData -> cellData.getValue().hotelPointProperty());
            hotelPrice_column.setCellValueFactory(cellData -> cellData.getValue().hotelPriceProperty());
            hotelAddress_column.setCellValueFactory(cellData -> cellData.getValue().hotelAddressProperty());

            hotelName_column.setCellFactory(param -> new HotelBtnCell());
        }

    }

    class HotelBtnCell extends TableCell<Hotel, String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Button hotel_btn;
            if(item != null) {
                String name = item.split("#")[0];
                String id = item.split("#")[1];
                hotel_btn = new Button(name);
                SingletonItem.getInstance().setHotelId(id);
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
