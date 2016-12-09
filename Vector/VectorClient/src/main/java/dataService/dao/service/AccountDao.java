package dataService.dao.service;

import java.rmi.Remote;

import common.AccountType;
import common.ResultMessage;
import vo.AccountVo;

/**
 * @ author lienming
 * @ version 2016-11-27
 * @ description
 */
public interface AccountDao extends Remote {

    public AccountType login(String id, String password);

    public ResultMessage logout(String id)  ;

    public String register(String name,String password) ;

    public ResultMessage modifyPassword(String id,String newPassword) ;

    public AccountVo findAccount(String id);

	public String insertAccount(String name,String password,AccountType type) ;

    public ResultMessage updateAccount(AccountVo vo);

    public ResultMessage deleteAccount(String id);

}
