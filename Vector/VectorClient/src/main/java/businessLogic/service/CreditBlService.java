package businessLogic.service;

import common.ResultMessage;
import vo.OrderVo;

public interface CreditBlService {

	public ResultMessage addCreditByOrder(String id,int amount,OrderVo vo);
	
	
	
}
