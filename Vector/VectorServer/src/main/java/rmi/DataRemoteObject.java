package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.AccountType;
import common.ResultMessage;
import dataService.dao.impl.AccountDaoImpl;
import dataService.dao.impl.MemberDaoImpl;
import dataService.dao.service.AccountDao;
import dataService.dao.service.MemberDao;
import po.AccountPo;
import vo.AccountVo;
import vo.MemberVo;

/**
 * Created by Administrator on 2016-11-20.
 */
public class DataRemoteObject extends UnicastRemoteObject implements AccountDao,MemberDao{

	 
	private static final long serialVersionUID = 1L;

    private AccountDao accountDao;
    private MemberDao memberDao ;
    
    protected DataRemoteObject() throws RemoteException{
        accountDao = AccountDaoImpl.getInstance();
        memberDao = MemberDaoImpl.getInstance();
    }


    /* AccountDao 接口方法 */
    public AccountType login(String id, String password)  throws RemoteException {
        return accountDao.login(id,password);
    }

    public ResultMessage logout(String id) throws RemoteException{
        return accountDao.logout(id);
    }

    public String register(String memberName, String password) throws RemoteException{
        return accountDao.register(memberName,password);
    }

    public ResultMessage modify(String id, String newPassword) throws RemoteException{
        return accountDao.modify(id,newPassword);
    }

    public AccountVo find(String id) throws RemoteException{
        return accountDao.find(id);
    }

    public ResultMessage insert(AccountPo po) throws RemoteException{
        return accountDao.insert(po);
    }

    public ResultMessage update(AccountPo po) throws RemoteException{
        return accountDao.update(po);
    }

    public ResultMessage delete(String id) throws RemoteException{
        return accountDao.delete(id);
    }

    /* MemberDao接口方法  */
    
    public int getCredit(String id)throws RemoteException {
    	return memberDao.getCredit(id);
    }

    public ResultMessage chargeCredit(String id, int amount)throws RemoteException {
    	return memberDao.chargeCredit(id, amount);
    }

    public MemberVo getInfo(String id)throws RemoteException {
    	return memberDao.getInfo(id);
    }

    public ResultMessage modifyInfo(MemberVo vo)throws RemoteException {
    	return memberDao.modifyInfo(vo);
    }
    
}