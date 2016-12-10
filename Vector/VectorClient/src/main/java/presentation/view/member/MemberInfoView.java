package presentation.view.member;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import presentation.common.SingletonId;
import presentation.controller.impl.member.MemberInfoViewControllerImpl;
import presentation.controller.service.member.MemberInfoViewControllerService;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * @author Molloh
 * @version 2016/11/25
 * @description
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

    //性别
    @FXML
    private RadioButton male_radio;
    @FXML
    private RadioButton female_radio;

    @FXML
    private Label credit_label;
    @FXML
    private Label type_label;

    private MemberInfoViewControllerService controller;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String memberId = SingletonId.getInstance().getActivateId();
        controller = MemberInfoViewControllerImpl.getInstance();
        controller.setMemberId(SingletonId.getInstance().getActivateId());

        if(memberId.charAt(0) == 'N') {
            type_label.setText("生日：");
            birthday_field.setVisible(true);
            enterprise_field.setVisible(false);
            birthday_field.setAccessibleText(controller.getBirthDay());
        }else {
            type_label.setText("企业：");
            enterprise_field.setVisible(true);
            birthday_field.setVisible(true);
            enterprise_field.setText(controller.getEnterPrise());
        }

        name_field.setText(controller.getMemberName());
        credit_label.setText(controller.getCredit());
        address_field.setText(controller.getAddress());
        phone_field.setText(controller.getPhone());
    }

    @FXML
    private void handleModify() {
        if(enterprise_field.isVisible())
            controller.setEnterPrise(enterprise_field.getText());
        if(birthday_field.isVisible())
            controller.setBirthDay(birthday_field.getAccessibleText());

        controller.setAddress(address_field.getText());
        controller.setPhone(phone_field.getText());
        controller.setMemberName(name_field.getText());
        //还差性别
    }

    @FXML
    private void handleCancel() {
        if(enterprise_field.isVisible())
            enterprise_field.setText(controller.getEnterPrise());
        if(birthday_field.isVisible())
            birthday_field.setAccessibleText(controller.getBirthDay());

        name_field.setText(controller.getMemberName());
        address_field.setText(controller.getAddress());
        phone_field.setText(controller.getPhone());
    }
}
