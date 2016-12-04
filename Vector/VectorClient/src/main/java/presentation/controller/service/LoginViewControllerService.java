package presentation.controller.service;

import common.AccountType;
import common.ResultMessage;
import vo.AccountVo;

/**
 * Created by Molloh on 2016/11/5.
 */
public interface LoginViewControllerService {
    /**
     * 用户账号登录
     * @return 账号登录是否成功，成功返回用户类型AccountType
     * @author lienming
     * @version 2016-11-27
     */
    public AccountType login(String id, String password) ;

    public ResultMessage logout(String id) ;

    public AccountVo find(String id);
}
