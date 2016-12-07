package presentation.controller.impl;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.service.AccountBlService;
import common.AccountType;
import presentation.controller.service.SignViewControllerService;

import java.rmi.RemoteException;

/**
 * @author Molloh
 * @version 2016/11/27
 * @description
 */
public class SignViewControllerImpl implements SignViewControllerService {
    private AccountBlService accountBlService = AccountBlServiceImpl.getInstance();

    @Override
    public AccountType signIn(String memberId, String password) throws RemoteException {
        return accountBlService.login(memberId, password);
    }
}
