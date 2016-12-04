package businessLogic.impl;

import businessLogic.service.MemberBlService;
import common.InfoType;
import common.ResultMessage;
import dataService.dao.service.MemberDao;
import rmi.RemoteHelper;
import vo.MemberVo;

/**
 * Updated by lienming on 2016-11-27.
 */
public class MemberBlServiceImpl implements MemberBlService{

    private MemberDao memberDao ;
    
    private static MemberBlServiceImpl memberBlServiceImpl;
    
    public static MemberBlServiceImpl getInstance(){
    	if(memberBlServiceImpl==null)
    		memberBlServiceImpl=new MemberBlServiceImpl();
    	return memberBlServiceImpl;
    }
    
    public MemberBlServiceImpl(){
        memberDao = RemoteHelper.getInstance().getMemberDao();
    }

    public int getCredit(String id)  {
        return memberDao.getCredit(id);
    }

    public ResultMessage chargeCredit(String id, int amount){
        return memberDao.chargeCredit(id,amount);
    }

    public MemberVo getInfo(String id){
        return memberDao.getInfo(id);
    }

    /*
     * (non-Javadoc)
     * @see businessLogic.service.MemberBlService#checkInfo(java.lang.String, common.InfoType)
     * 数据格式要求：
     * InfoType   
     *  NAME     :   长度为4~12
     *  PHONE    :   长度为11位的数字
     *  ADDRESS  :   暂时无要求
     *  SEX      :   FEMALE / MALE 
     */
    public ResultMessage checkInfo(String info, InfoType infoType){
    	switch(infoType){
    	case NAME :	   
    		if(info.length()>=4 && info.length()<=12)
    			return ResultMessage.VALID;
    		else 
    			return ResultMessage.INVALID;
    	case PHONE: 	
    		if(info.length()==11)
    		{
    			for(int i=0;i<11;i++)
    			{
    				if(info.charAt(i)<48 || info.charAt(i)>57)
    					return ResultMessage.INVALID;
    			}
    			return ResultMessage.VALID;
    		}
    		else 
    			return ResultMessage.INVALID;
    	case ADDRESS:	return ResultMessage.VALID;
    	case SEX:
    		if(info.equals("MALE") || info.equals("FEMALE"))
    			return ResultMessage.VALID;
    		else return ResultMessage.INVALID;
    	}
    	
        return ResultMessage.SUCCEED;
    }

    public ResultMessage modifyInfo(MemberVo vo){
    	if(checkInfo(vo.getName(),InfoType.NAME) == ResultMessage.VALID
    			&& checkInfo(vo.getPhone(),InfoType.PHONE)== ResultMessage.VALID)
    		return memberDao.modifyInfo(vo);
    	else return ResultMessage.FAIL;
    }

}
