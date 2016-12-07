package presentation.controller.service;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.service.AccountBlService;
import common.AccountType;

import java.rmi.RemoteException;

/**
 * Created by Molloh on 2016/11/5.
 */
public interface SignViewControllerService {

    public AccountType signIn(String memberName, String password) throws RemoteException;

}
