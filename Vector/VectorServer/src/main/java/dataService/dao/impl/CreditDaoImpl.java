package dataService.dao.impl;

import java.util.List;

import common.ResultMessage;
import dataService.dao.service.CreditDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.CreditDataHelper;
import dataService.dataHelper.service.DataFactory;
import po.CreditRecordPo;
import vo.OrderVo;

public class CreditDaoImpl implements CreditDao {
	private static CreditDaoImpl creditDataServiceImpl;
	private CreditDataHelper creditDataHelper;
	private DataFactory dataFactory;
	
	public static CreditDaoImpl getInstance(){
		if(creditDataServiceImpl==null)
			creditDataServiceImpl = new CreditDaoImpl();
		
		return creditDataServiceImpl;
	}
	
	private CreditDaoImpl(){
		if(dataFactory==null){
			dataFactory = new DataFactoryImpl();
			creditDataHelper= dataFactory.getCreditDataHelper();
		}
			
	}
	
	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo){
		int ori_credit = MemberDaoImpl.getInstance().getCredit(id);
		CreditRecordPo po;
		
		if(vo==null) //充值信用，与订单无关!
			po = new CreditRecordPo(ori_credit+amount,
					"Charge credit",amount);
		else
			po = new CreditRecordPo(ori_credit+amount,
				vo.getOrderId()+"&&"+vo.getCondition(),amount);
		
		List<CreditRecordPo> list = creditDataHelper.getCreditRecordData(id);
		list.add(po);
		return creditDataHelper.updateCreditRecordData(list, id);
	}
	
	public ResultMessage newCredit(String id){
		return creditDataHelper.newCredit(id);
	}
	
	
}
