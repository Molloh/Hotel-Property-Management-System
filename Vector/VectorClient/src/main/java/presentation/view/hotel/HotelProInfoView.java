package presentation.view.hotel;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import presentation.common.SingletonItem;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.hotel.HotelProInfoViewControllerImpl;
import presentation.controller.service.hotel.HotelProInfoViewControllerService;
import vo.ActivityPromotionVo;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class HotelProInfoView implements Initializable {
    @FXML
    private AnchorPane missionPane;

    @FXML
    private Label proType_label;
    @FXML
    private TextField name_field;
    @FXML
    private TextField discount_field;
    @FXML
    private DatePicker start_date;
    @FXML
    private DatePicker end_date;

    private HotelProInfoViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = new HotelProInfoViewControllerImpl();

        proType_label.setText(controller.getPromotionType());
        name_field.setText(controller.getPromotionName());
        discount_field.setText(controller.getDiscount());
        start_date.setValue(controller.getStartDate());
        end_date.setValue(controller.getEndDate());
    }

    @FXML
    private void handleModifyPromotion() {
        controller.setPromotionName(name_field.getText());
        controller.setDiscount(discount_field.getText());
        controller.setStartDate(start_date.getValue());
        controller.setEndDate(end_date.getValue());
    }

    @FXML
    private void handleCancel() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.HotelPromotion_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleDelete() {
        controller.delete();
    }

}
