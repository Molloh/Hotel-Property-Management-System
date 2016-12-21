package dataService.dao.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.ResultMessage;
import vo.OrderVo;

public interface CreditDao extends Remote{
	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo)
			throws RemoteException;
}
