package presentation.controller.impl;

import common.AccountType;
import presentation.controller.service.SignInViewControllerService;

import java.rmi.RemoteException;

/**
 * @author Molloh
 * @version 2016/11/27
 * @description
 */
public class SignInViewControllerImpl implements SignInViewControllerService {


    @Override
    public AccountType signIn(String memberName, String password) throws RemoteException {
        return AccountType.Member;
    }
}
