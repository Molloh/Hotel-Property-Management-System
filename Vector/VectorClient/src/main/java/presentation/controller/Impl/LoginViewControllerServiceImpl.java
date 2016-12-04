package presentation.controller.Impl;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.service.AccountBlService;
import common.AccountType;
import common.ResultMessage;
import presentation.controller.service.LoginViewControllerService;
import vo.AccountVo;

/**
 * updated by Administrator on 2016-11-27.
 */
public class LoginViewControllerServiceImpl implements LoginViewControllerService {

    private AccountBlService accountBlService ;  //下层

    private static LoginViewControllerServiceImpl loginImpl;  //本层

    public static LoginViewControllerServiceImpl getInstance(){
        if(loginImpl==null)
            loginImpl = new LoginViewControllerServiceImpl() ;
        return loginImpl;
    }

    private LoginViewControllerServiceImpl(){
        accountBlService = AccountBlServiceImpl.getInstance();
    }

    public AccountType login(String id, String password) {
        return accountBlService.login(id,password);
    }

    public ResultMessage logout(String id){
        return accountBlService.logout(id);
    }

    public AccountVo find(String id){
    	return accountBlService.find(id);
    }
}
