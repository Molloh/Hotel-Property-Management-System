package presentation.controller.impl.sign;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.service.AccountBlService;
import common.AccountType;
import presentation.common.SingletonId;
import presentation.controller.service.sign.SignViewControllerService;

import java.rmi.RemoteException;

/**
 * @author Molloh
 * @version 2016/11/27
 * @description
 */
public class SignViewControllerImpl implements SignViewControllerService {
    private AccountBlService account;

    private static final SignViewControllerService INSTANCE = new SignViewControllerImpl();

    private SignViewControllerImpl(){/*
        account = AccountBlServiceImpl.getInstance();*/
    }

    public static SignViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public AccountType signIn(String memberId, String password) {
        return AccountType.Marketer;
        //return account.login(memberId, password);
    }

    @Override
    public String signUp(String memberName, String password) {
        return "MOLLOH00001";
        //return account.register(memberName, password);
    }

    @Override
    public void signOut() {/*
        account.logout(SingletonId.getInstance().getActivateId());
        SingletonId.getInstance().setActivateId(null);*/
    }

}
