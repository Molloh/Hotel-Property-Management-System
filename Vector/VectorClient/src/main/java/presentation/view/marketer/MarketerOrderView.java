package presentation.view.marketer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import presentation.common.ViewFxmlPath;
import presentation.controller.Order;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @ author Molloh
 * @ version 2016/11/5
 * @ description Controller for login view
 */
public class MarketerOrderView implements Initializable {
    @FXML
    private TableView<Order> order_list;
    @FXML
    private TableColumn<Order, String> orderId_column;

    @FXML
    private AnchorPane missionPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initTable();
    }

    private void initTable() {
        ObservableList<Order> data = FXCollections.observableArrayList(
                new Order("2016121200001", "0","0", "", "", "")
        );
        order_list.setItems(data);

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
                order_btn.setOnAction(event -> {
                    try {
                        missionPane.getChildren().clear();
                        missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.MarketerOrderRevoke_View_Path)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
