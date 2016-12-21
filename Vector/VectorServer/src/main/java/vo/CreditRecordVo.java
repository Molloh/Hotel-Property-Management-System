package vo;

import java.text.SimpleDateFormat;

import po.CreditRecordPo;

public class CreditRecordVo {
	private int creditNow;
	private String reason ;
	private String time ;
	private int delta;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public CreditRecordVo(CreditRecordPo po){
		this.creditNow = po.getCredit();
	}
}
