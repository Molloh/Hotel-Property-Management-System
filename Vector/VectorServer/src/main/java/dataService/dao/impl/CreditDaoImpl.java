package dataService.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import common.ResultMessage;
import dataService.dao.service.CreditDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.CreditDataHelper;
import dataService.dataHelper.service.DataFactory;
import po.CreditRecordPo;
import vo.CreditRecordVo;
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time_str = sdf.format(new Date());
		if(vo==null) //充值信用，与订单无关!
			po = new CreditRecordPo(ori_credit+amount,
					"Charge credit",time_str,amount);
		else
			po = new CreditRecordPo(ori_credit+amount,
				vo.getOrderId()+"&&"+vo.getCondition(),time_str,amount);
		
		List<CreditRecordPo> list = creditDataHelper.getCreditRecordData(id);
		list.add(po);
		return creditDataHelper.updateCreditRecordData(list, id);
	}
	
	public ResultMessage newCredit(String id){
		return creditDataHelper.newCredit(id);
	}
	
	public List<CreditRecordVo> getCreditRecordList(String id){
		List<CreditRecordPo> list_po = creditDataHelper.getCreditRecordData(id);
		List<CreditRecordVo> list_vo = new ArrayList<CreditRecordVo>();
		Iterator<CreditRecordPo> iterator = list_po.iterator();
		while(iterator.hasNext()){
			list_vo.add( new CreditRecordVo(iterator.next()));
		}		
		return list_vo;
	}
}
