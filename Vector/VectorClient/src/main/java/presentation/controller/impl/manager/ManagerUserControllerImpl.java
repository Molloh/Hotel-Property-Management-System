package presentation.controller.impl.manager;

import businessLogic.service.AccountBlService;
import common.AccountType;
import common.Sex;
import presentation.controller.service.manager.ManagerUserControllerService;

import java.time.LocalDate;

/**
 * @author Molloh
 * @version 2016/12/9
 * @description
 */
public class ManagerUserControllerImpl implements ManagerUserControllerService {
    private static final ManagerUserControllerService INSTANCE = new ManagerUserControllerImpl();

    private AccountBlService account;

    private ManagerUserControllerImpl(){}

    public static ManagerUserControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void addUser(String password, AccountType type) {

    }

    @Override
    public void searchUser(String userId) {

    }

    @Override
    public AccountType getUserType() {
        return null;
    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public void setUserName(String userName) {

    }

    @Override
    public LocalDate getBirthDay() {
        return null;
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getCredit() {
        return null;
    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public Sex getSex() {
        return null;
    }

    @Override
    public String getEnterPrise() {
        return null;
    }

    @Override
    public String getVIPLevel() {
        return null;
    }

    @Override
    public void setSex(Sex sex) {

    }

    @Override
    public void setPhone(String phoneNum) {

    }

    @Override
    public void setMemberName(String memberName) {

    }

    @Override
    public void setBirthDay(LocalDate birthDay) {

    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public void setEnterPrise(String enterPrise) {

    }

    @Override
    public void setVIPLevel(String vipLevel) {

    }

    @Override
    public String getHotelAddress() {
        return null;
    }

    @Override
    public String getHotelDiscription() {
        return null;
    }

    @Override
    public String getHotelPoint() {
        return null;
    }

    @Override
    public String getHotelStar() {
        return null;
    }

    @Override
    public String getHotelPhone() {
        return null;
    }

    @Override
    public void setHotelAddress(String hotelAddress) {

    }

    @Override
    public void setHotelDiscription(String hotelDiscription) {

    }

    @Override
    public void setHotelPhone(String hotelPhone) {

    }

    @Override
    public void setHotelStar(String hotelStar) {

    }

    @Override
    public void setHotelPoint(String hotelPoint) {

    }
}
