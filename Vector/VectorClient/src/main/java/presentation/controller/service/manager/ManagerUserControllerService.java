package presentation.controller.service.manager;

import common.AccountType;

/**
 * @author Molloh
 * @version 2016/12/9
 * @description
 */
public interface ManagerUserControllerService {

    public void addUser(String password, AccountType type);

    public void searchUser(String userId);

    public AccountType getUserType();

    public String getMemberName();

    public String getHotelName();

    public String getMarketerName();

    public String getMemberAddress();

    public String getMemberPhone();

    public String getMemberBirthday();

    public String getMemberEnterPrise();

    public String getMemberCredit();

    public String getHotelAddress();

}
