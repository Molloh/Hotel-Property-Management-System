package dataService.dataHelper.service;

import java.util.List;

import common.ResultMessage;
import po.CreditRecordPo;

public interface CreditDataHelper {
	public List<CreditRecordPo> getCreditRecordData(String id);
	
	public ResultMessage updateCreditRecordData(List<CreditRecordPo> list,String id);
	
	public ResultMessage newCredit(String id);
}
