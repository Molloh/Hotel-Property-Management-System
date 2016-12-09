package dataService.dao.service;
/*123*/
import java.rmi.Remote;
import java.rmi.RemoteException;

import common.AccountType;
import common.ResultMessage;
import po.AccountPo;
import vo.AccountVo;

/**
 * @ author lienming
 * @ version 2016-11-27
 * @ description
 */
public interface AccountDao extends Remote {

    public AccountType login(String id, String password)  throws RemoteException;

    public ResultMessage logout(String id) throws RemoteException;

    public ResultMessage modifyPassword(String id, String newPassword) throws RemoteException;

    public AccountVo findAccount(String id) throws RemoteException;

    public String insertAccount(String name,String password,AccountType type)
    		throws RemoteException;

    public ResultMessage updateAccount(AccountVo vo) throws RemoteException;

    public ResultMessage deleteAccount(String id) throws RemoteException;

}
