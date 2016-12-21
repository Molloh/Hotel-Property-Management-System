package dataService.dao.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import common.ResultMessage;
import vo.CreditRecordVo;
import vo.OrderVo;

public interface CreditDao extends Remote{
	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo)
			throws RemoteException;
	
	public List<CreditRecordVo> getCreditRecordList(String id)throws RemoteException;
}
