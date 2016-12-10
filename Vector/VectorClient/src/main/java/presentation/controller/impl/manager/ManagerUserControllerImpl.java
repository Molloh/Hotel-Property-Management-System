package presentation.controller.impl.manager;

import businessLogic.service.AccountBlService;
import common.AccountType;
import presentation.controller.service.manager.ManagerUserControllerService;

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
    public String getMemberName() {
        return null;
    }

    @Override
    public String getHotelName() {
        return null;
    }

    @Override
    public String getMarketerName() {
        return null;
    }

    @Override
    public String getMemberAddress() {
        return null;
    }

    @Override
    public String getMemberPhone() {
        return null;
    }

    @Override
    public String getMemberBirthday() {
        return null;
    }

    @Override
    public String getMemberEnterPrise() {
        return null;
    }

    @Override
    public String getMemberCredit() {
        return null;
    }

    @Override
    public String getHotelAddress() {
        return null;
    }
}
