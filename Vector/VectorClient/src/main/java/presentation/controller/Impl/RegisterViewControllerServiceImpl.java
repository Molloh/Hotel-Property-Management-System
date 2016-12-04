package presentation.controller.Impl;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.service.AccountBlService;
import common.ResultMessage;
import presentation.controller.service.RegisterViewControllerService;

/**
 * Updated by lienming on 2016-11-27.
 */
public class RegisterViewControllerServiceImpl implements RegisterViewControllerService{

    private AccountBlService accountBlService ;  //下层

    private static RegisterViewControllerServiceImpl registerImpl;   //本层

    public static RegisterViewControllerServiceImpl getInstance(){
        if(registerImpl == null)
            registerImpl = new RegisterViewControllerServiceImpl() ;
        return registerImpl ;
    }

    private RegisterViewControllerServiceImpl(){
        accountBlService = AccountBlServiceImpl.getInstance();
    }

    @Override
    public String register(String memberName, String password){
        return accountBlService.register(memberName,password);
    }
    
    public ResultMessage modify(String id,String newPassword){
    	return accountBlService.modify(id, newPassword);
    }
}
