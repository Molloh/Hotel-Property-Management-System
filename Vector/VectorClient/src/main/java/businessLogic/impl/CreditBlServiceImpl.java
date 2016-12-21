package businessLogic.impl;

import java.util.List;

import businessLogic.service.CreditBlService;
import common.ResultMessage;
import dataService.dao.service.CreditDao;
import rmi.RemoteHelper;
import vo.CreditRecordVo;
import vo.OrderVo;

public class CreditBlServiceImpl implements CreditBlService {
	private CreditDao creditDao;
	
	private static CreditBlServiceImpl creditBlServiceImpl;
	
	public static CreditBlServiceImpl getInstance(){
		if(creditBlServiceImpl==null)
			creditBlServiceImpl = new CreditBlServiceImpl();
		return creditBlServiceImpl;
	}
	
	private CreditBlServiceImpl(){
		creditDao = RemoteHelper.getInstance().getCreditDao();
	}
	
	/*由于订单状态改变导致的信用值变化，请调用此方法，信用值变化可为负数*/
	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo){
		if(vo==null)
			return ResultMessage.FAIL;
		return creditDao.addCreditByOrder(id, amount, vo);
	}
	
	public List<CreditRecordVo> getCreditRecordList(String id){
		return creditDao.getCreditRecordList(id);
	}
}
