package presentation.view.member;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.Order;
import presentation.controller.impl.member.MemberOrderViewControllerImpl;
import presentation.controller.service.member.MemberOrderViewControllerService;
import vo.OrderVo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MemberOrderView implements Initializable {
    @FXML
    private TableView<Order> order_list;
    @FXML
    private TableColumn<Order, String> orderId_column;
    @FXML
    private TableColumn<Order, String> orderState_column;
    @FXML
    private TableColumn<Order, String> orderPrice_column;
    @FXML
    private TableColumn<Order, String> orderHotel_column;
    @FXML
    private TableColumn<Order, String> orderTime_column;
    @FXML
    private TableColumn<Order, String> orderExeTime_column;

    @FXML
    private ChoiceBox<String> orderType_choice;

    @FXML
    private AnchorPane missionPane;

    private MemberOrderViewControllerService controller;

    private ArrayList<OrderVo> orderList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = MemberOrderViewControllerImpl.getInstance();
        controller.setMemberId(SingletonItem.getInstance().getActivateId());

        orderType_choice.getItems().addAll("全部订单", "未执行订单", "已执行订单", "已入住订单", "待评价订单", "异常订单");
        orderList = (ArrayList<OrderVo>) controller.getAllOrders();
        initTable();
    }

    private void initTable() {
        ObservableList<Order> data = FXCollections.observableArrayList();
        for(OrderVo vo : orderList) {
            data.add(new Order(vo.getOrderId(),
                    vo.getCondition().toString(),
                    vo.getHotel(),
                    String.valueOf(vo.getDiscountedPrice()),
                    vo.getCreateTime().toInstant().toString(),
                    vo.getCheckInTime().toInstant().toString()));
        }
        order_list.setItems(data);

        orderPrice_column.setCellValueFactory(cellData -> cellData.getValue().orderPriceProperty());
        orderHotel_column.setCellValueFactory(cellData -> cellData.getValue().orderHotelProperty());
        orderTime_column.setCellValueFactory(cellData -> cellData.getValue().orderTimeProperty());
        orderExeTime_column.setCellValueFactory(cellData -> cellData.getValue().orderExeTimeProperty());
        orderState_column.setCellValueFactory(cellData -> cellData.getValue().orderStateProperty());
        orderId_column.setCellValueFactory(cellData -> cellData.getValue().orderIdProperty());
        orderId_column.setCellFactory(param -> new OrderBtnCell());
    }

    class OrderBtnCell extends TableCell<Order, String> {
        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            Button order_btn;
            if(item != null) {
                order_btn = new Button(item);
                setGraphic(order_btn);
                order_btn.setOnAction((ActionEvent event) -> {
                    try {
                        SingletonItem.getInstance().setOrderId(item);
                        missionPane.getChildren().clear();
                        missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MemberOrderInfo_View_Path)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
