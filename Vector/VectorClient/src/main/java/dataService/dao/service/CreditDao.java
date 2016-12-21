package dataService.dao.service;

import common.ResultMessage;
import vo.OrderVo;

public interface CreditDao {
	
	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo);
}
