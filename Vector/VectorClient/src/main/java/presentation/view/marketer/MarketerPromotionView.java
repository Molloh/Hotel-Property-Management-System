package presentation.view.marketer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.hotel.HotelPromotionViewControllerImpl;
import presentation.controller.service.hotel.HotelPromotionViewControllerService;
import presentation.view.hotel.HotelPromotionView;
import vo.ActivityPromotionVo;

import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MarketerPromotionView implements Initializable {
    @FXML
    AnchorPane missionPane;

    @FXML
    private ListView<ActivityPromotionVo> promotion_list;

    private HotelPromotionViewControllerService controller;

    private ArrayList<ActivityPromotionVo> promotion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //controller = new HotelPromotionViewControllerImpl(SingletonItem.getInstance().getHotelId());
        promotion = (ArrayList<ActivityPromotionVo>) controller.getCurrentActStrategy();

        ObservableList<ActivityPromotionVo> data = FXCollections.observableArrayList();
        for (ActivityPromotionVo item : promotion) {
            data.add(item);
        }
        promotion_list.setItems(data);
        promotion_list.setCellFactory((ListView<ActivityPromotionVo> lv) -> new PromotionCell());

    }

    @FXML
    private void handleAddPro() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.HotelProAdd_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class PromotionCell extends ListCell<ActivityPromotionVo> {
        @Override
        protected void updateItem(ActivityPromotionVo item, boolean empty) {
            super.updateItem(item, empty);
            Label area = new Label();
            area.setText(item.getPromotionName() + " " + item.getStartDate() + "-" + item.getEndDate() + "\n" + item.getPromotionType() + " " + item.getDiscount());
            javafx.scene.control.Button btn = new javafx.scene.control.Button("编辑");
            btn.setOnAction(event -> {
                SingletonItem.getInstance().setActivityPromotionVo(item);
                missionPane.getChildren().clear();
                try {
                    missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.HotelProInfo_View_Path)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
