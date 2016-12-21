package po;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreditRecordPo implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int creditNow;
	private String reason ;
	private String time ;
	private int delta;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public CreditRecordPo(int creditNow,String reason,int delta){
		this.creditNow=creditNow;
		this.reason=reason;
		String time_str = sdf.format(new Date());
		this.time=time_str;
		this.delta=delta;
	}
	
	public CreditRecordPo(){
		this.creditNow=100;
		this.reason="initialize";
		
		String time_str = sdf.format(new Date());
		this.time=time_str;
		this.delta=0;
	}
	
	public String parseString(){
		return creditNow+";"+reason+";"+time+";"+delta;
	}
	
	public int getCredit(){
		return this.creditNow;
	}
	
	 
}
