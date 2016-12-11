package presentation.controller.service.manager;

import common.AccountType;
import common.Sex;

import java.time.LocalDate;

/**
 * @author Molloh
 * @version 2016/12/9
 * @description
 */
public interface ManagerUserControllerService {

    void addUser(String password, AccountType type);

    void searchUser(String userId);

    AccountType getUserType();

    String getUserName();

    void setUserName(String userName);

    //member

    LocalDate getBirthDay();

    String getAddress();

    String getCredit();

    String getPhone();

    Sex getSex();

    String getEnterPrise();

    String getVIPLevel();

    void setSex(Sex sex);

    void setPhone(String phoneNum);

    void setMemberName(String memberName);

    void setBirthDay(LocalDate birthDay);

    void setAddress(String address);

    void setEnterPrise(String enterPrise);

    void setVIPLevel(String vipLevel);

    //hotel

    String getHotelAddress();

    String getHotelDiscription();

    String getHotelPoint();

    String getHotelStar();

    String getHotelPhone();

    void setHotelAddress(String hotelAddress);

    void setHotelDiscription(String hotelDiscription);

    void setHotelPhone(String hotelPhone);

    void setHotelStar(String hotelStar);

    void setHotelPoint(String hotelPoint);

}
