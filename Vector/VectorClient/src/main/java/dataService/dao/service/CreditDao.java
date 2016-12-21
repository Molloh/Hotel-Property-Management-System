package dataService.dao.service;

import java.util.List;

import common.ResultMessage;
import vo.CreditRecordVo;
import vo.OrderVo;

public interface CreditDao {
	
	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo);
	
	public List<CreditRecordVo> getCreditRecordList(String id);
	
	
}
