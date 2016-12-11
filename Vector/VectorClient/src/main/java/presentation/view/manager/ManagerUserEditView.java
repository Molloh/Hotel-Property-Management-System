package presentation.view.manager;

import common.AccountType;
import common.Sex;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import presentation.common.ViewFxmlPath;
import presentation.controller.impl.manager.ManagerUserControllerImpl;
import presentation.controller.service.manager.ManagerUserControllerService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class ManagerUserEditView implements Initializable {
    @FXML
    private AnchorPane missionPane;
    @FXML
    private TextField name_field;
    @FXML
    private Label type_label;
    @FXML
    private Label ID_label;

    //Member
    @FXML
    private GridPane member_grid;

    //普通会员
    @FXML
    private DatePicker birthday_field;

    //企业会员
    @FXML
    private TextField enterprise_field;

    @FXML
    private TextField address_field;
    @FXML
    private TextField phone_field;
    @FXML
    private RadioButton male_radio;
    @FXML
    private RadioButton female_radio;

    @FXML
    private Label VIP_label;
    @FXML
    private Label credit_label;
    @FXML
    private Label member_label;

    //Hotel
    @FXML
    private GridPane hotel_grid;
    @FXML
    private TextArea discription_area;
    @FXML
    private TextField hotelPoint_field;
    @FXML
    private TextField hotelAddress_field;
    @FXML
    private TextField hotelPhone_field;

    @FXML
    private ComboBox<String> star_combo;

    private ManagerUserControllerService controller;

    private Sex sex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller = ManagerUserControllerImpl.getInstance();

        type_label.setText(String.valueOf(controller.getUserType()));
        name_field.setText(controller.getUserName());
        ID_label.setText(controller.getUserName());

        AccountType accountType = controller.getUserType();
        if(accountType == AccountType.Member) {
            member_grid.setVisible(true);
            hotel_grid.setVisible(false);
            member_label.setText("生日：");
            birthday_field.setVisible(true);
            enterprise_field.setVisible(false);
            birthday_field.setValue(controller.getBirthDay());
            initialMember();
        }else if(accountType == AccountType.Enterprise) {
            member_grid.setVisible(true);
            hotel_grid.setVisible(false);
            member_label.setText("企业：");
            enterprise_field.setVisible(true);
            birthday_field.setVisible(true);
            enterprise_field.setText(controller.getEnterPrise());
            initialMember();
        }else if(accountType == AccountType.Marketer) {
            member_grid.setVisible(false);
            hotel_grid.setVisible(false);
        }else if(accountType == AccountType.Hotel) {
            hotel_grid.setVisible(true);
            member_grid.setVisible(false);
            initialHotel();
        }

    }

    private void initialMember() {
        address_field.setText(controller.getAddress());
        phone_field.setText(controller.getPhone());
        credit_label.setText(controller.getCredit());
        VIP_label.setText(controller.getVIPLevel());

        ToggleGroup sex_group = new ToggleGroup();
        male_radio.setToggleGroup(sex_group);
        female_radio.setToggleGroup(sex_group);

        sex_group.selectedToggleProperty().addListener(
                (ObservableValue<?extends Toggle> ov, Toggle old_Toggle, Toggle new_Toggle) -> {
                    if(sex_group.getSelectedToggle() == male_radio) {
                        sex = Sex.MALE;
                    }else if(sex_group.getSelectedToggle() == female_radio) {
                        sex = Sex.FEMALE;
                    }
                }
        );
    }

    private void initialHotel() {
        hotelAddress_field.setText(controller.getHotelAddress());
        hotelPhone_field.setText(controller.getHotelPhone());
        star_combo.setValue(controller.getHotelStar());
        discription_area.setText(controller.getHotelDiscription());
    }

    @FXML
    private void handleCancel() {
        try {
            missionPane.getChildren().clear();
            missionPane.getChildren().add(FXMLLoader.load(getClass().getResource(ViewFxmlPath.ManagerUserSearch_View_Path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleEditUser() {
        AccountType accountType = controller.getUserType();
        if(accountType == AccountType.Member) {

        }else if(accountType == AccountType.Enterprise) {

        }else if(accountType == AccountType.Marketer) {

        }else if(accountType == AccountType.Hotel) {

        }
    }
}
