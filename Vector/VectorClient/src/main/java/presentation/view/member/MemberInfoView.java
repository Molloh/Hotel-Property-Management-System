package presentation.view.member;

import common.AccountType;
import common.Sex;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentation.common.SingletonItem;
import presentation.controller.impl.member.MemberInfoViewControllerImpl;
import presentation.controller.service.member.MemberInfoViewControllerService;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description Member信息修改界面
 */
public class MemberInfoView implements Initializable {
    @FXML
    private TextField name_field;

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
    private Label type_label;

    private MemberInfoViewControllerService controller;

    private Sex sex;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String memberId = SingletonItem.getInstance().getActivateId();
        controller = MemberInfoViewControllerImpl.getInstance();
        controller.setMemberId(memberId);

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
        
        //不可修改
        VIP_label.setText(controller.getVIPLevel());
        credit_label.setText(controller.getCredit());

        //普通会员显示生日，企业会员显示企业名
        AccountType accountType = controller.getAccountType();
        if(accountType == AccountType.Member) {
            type_label.setText("生日：");
            birthday_field.setVisible(true);
            enterprise_field.setVisible(false);
            birthday_field.setValue(controller.getBirthDay());
        }else {
            type_label.setText("企业：");
            enterprise_field.setVisible(true);
            birthday_field.setVisible(true);
            enterprise_field.setText(controller.getEnterPrise());
        }

        //可修改
        name_field.setText(controller.getMemberName());
        address_field.setText(controller.getAddress());
        phone_field.setText(controller.getPhone());
        if(controller.getSex() == Sex.MALE) {
        	male_radio.setSelected(true);
        }else if(controller.getSex() == Sex.FEMALE) {
        	female_radio.setSelected(true);
        }
    }

    @FXML
    private void handleModify() {
        if(enterprise_field.isVisible())
            controller.setEnterPrise(enterprise_field.getText());
        if(birthday_field.isVisible())
            controller.setBirthDay(birthday_field.getValue());

        controller.setAddress(address_field.getText());
        controller.setPhone(phone_field.getText());
        controller.setMemberName(name_field.getText());
        controller.setSex(this.sex);
    }

    @FXML
    private void handleCancel() {
        if(enterprise_field.isVisible())
            enterprise_field.setText(controller.getEnterPrise());
        if(birthday_field.isVisible())
            birthday_field.setValue(controller.getBirthDay());

        name_field.setText(controller.getMemberName());
        address_field.setText(controller.getAddress());
        phone_field.setText(controller.getPhone());

        sex = controller.getSex();
        if(sex == Sex.MALE) {
            male_radio.setSelected(true);
            female_radio.setSelected(false);
            male_radio.requestFocus();
        }else if(sex == Sex.FEMALE) {
            female_radio.setSelected(true);
            male_radio.setSelected(false);
            female_radio.requestFocus();
        }
    }
    
    @FXML
    private void handleModifyPassword() {
    	
    }
}
