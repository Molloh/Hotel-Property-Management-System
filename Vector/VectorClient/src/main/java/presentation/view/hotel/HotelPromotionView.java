package presentation.view.hotel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.Promotion;
import presentation.controller.impl.hotel.HotelPromotionViewControllerImpl;
import presentation.controller.service.hotel.HotelPromotionViewControllerService;
import vo.ActivityPromotionVo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class HotelPromotionView implements Initializable {
    @FXML
    private AnchorPane anchorPane;

    private ArrayList<ActivityPromotionVo> promotionList;
    private HotelPromotionViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = HotelPromotionViewControllerImpl.getInstance();
        promotionList = (ArrayList<ActivityPromotionVo>) controller.getCurrentActStrategy();



        for(ActivityPromotionVo promotionVo : promotionList) {
            SingletonItem.getInstance().setActivityPromotionVo(promotionVo);
        }
    }



    @FXML
    private void handleAddPro() {

    }
}