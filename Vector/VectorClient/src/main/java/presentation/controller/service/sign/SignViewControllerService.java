package presentation.controller.service.sign;

import common.AccountType;

/**
 * @author Molloh
 * @version 2016/11/5
 * @description
 */
public interface SignViewControllerService {

    public AccountType signIn(String memberName, String password);

    public String signUp(String memberName, String password);

    public void signOut();

}
