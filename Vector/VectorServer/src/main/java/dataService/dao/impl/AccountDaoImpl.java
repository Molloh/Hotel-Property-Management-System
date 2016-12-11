package dataService.dao.impl;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import common.AccountType;
import common.ResultMessage;
import dataService.dao.service.AccountDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.AccountDataHelper;
import dataService.dataHelper.service.DataFactory;
import po.AccountPo;
import po.MemberPo;
import vo.AccountVo;
import vo.MemberVo;

/**
 * Updated by lienming on 2016-12-8.
 */
public class AccountDaoImpl implements AccountDao {

    /* map<Id,AccountPO> */
    private TreeMap<String,AccountPo> map_member;
    private TreeMap<String,AccountPo> map_enterprise;
    private TreeMap<String,AccountPo> map_hotel;
    private TreeMap<String,AccountPo> map_marketer;
    private TreeMap<String,AccountPo> map_manager;
    
    private AccountDataHelper accountDataHelper;

    private DataFactory dataFactory;

    private static AccountDaoImpl accountDataServiceImpl;

    public static AccountDaoImpl getInstance(){
        if(accountDataServiceImpl == null){
            accountDataServiceImpl = new AccountDaoImpl();
        }

        return accountDataServiceImpl;
    }

    private AccountDaoImpl(){
        if(dataFactory == null){
            dataFactory = new DataFactoryImpl();
            accountDataHelper = dataFactory.getAccountDataHelper();
            map_member 		 = accountDataHelper.getAccountData(AccountType.Member);
            map_enterprise   = accountDataHelper.getAccountData(AccountType.Enterprise);
            map_hotel  		 = accountDataHelper.getAccountData(AccountType.Hotel);
            map_marketer     = accountDataHelper.getAccountData(AccountType.Marketer);
            map_manager 	 = accountDataHelper.getAccountData(AccountType.Manager);
        }
    }


    public AccountType login(String id, String password) {
    	AccountType type = getAccountType(id);
    	TreeMap<String,AccountPo> map = getMap(type);
        Iterator<Map.Entry<String, AccountPo>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, AccountPo> entry = iterator.next();

            if(entry.getKey().equals(id) )
            {
                AccountPo po = entry.getValue();
                if(po.getPassword().equals(password)) {
                    if (po.getLogState() == 1)
                        return AccountType.Fail;    //已登入，不能重复登入

                    po.setLogState(1);
                    map.put(po.getId(), po);
                    updateMap(map,type);       //更新Map中的po信息
                    accountDataHelper.updateAccountData(map, type);
                    return po.getAccountType(id);
                }
                else
                    return AccountType.Fail; //账号存在,密码错误
            }
        }
        return AccountType.Fail; //账号名不存在

    }

    public ResultMessage logout(String id) {
    	AccountType type = getAccountType(id);
    	TreeMap<String,AccountPo> map = getMap(type);
        if( map.containsKey(id) )
        {
        AccountPo po = map.get(id); 
        if( po.getLogState()==0 )
            return ResultMessage.FAIL;

        po.setLogState(0);
        map.put(po.getId(), po);
        updateMap(map,type);       //更新Map中的po信息
        accountDataHelper.updateAccountData(map, type);
        return ResultMessage.SUCCEED;
        }
        else return ResultMessage.FAIL;
    }

    public ResultMessage modifyPassword(String id,String newPassword) {
    	AccountType type = getAccountType(id);
    	TreeMap<String,AccountPo> map = getMap(type);
    	if( map.containsKey(id) )
        {
    		AccountPo po = map.get(id); 
    		if( po.getPassword().equals(newPassword))
    			return ResultMessage.FAIL;

    		po.setPassword(newPassword);
    		po.setLogState(0);
            map.put(po.getId(), po);
            updateMap(map,type);       //更新Map中的po信息
            return ResultMessage.SUCCEED;
         }
         else return ResultMessage.FAIL;
    }

    public AccountVo findAccount(String id){
    	AccountType type = getAccountType(id);
    	TreeMap<String,AccountPo> map = getMap(type);
    	if( map.containsKey(id) )
        {
    		AccountPo po = map.get(id); 
    		AccountVo vo = new AccountVo(po);
    		return vo;
        }
        else return null;
    }

    public String insertAccount(String name,String password,AccountType type) {
    	TreeMap<String,AccountPo> map = getMap(type);
    	Iterator<Map.Entry<String, AccountPo>> iterator = map.entrySet().iterator();
    	String newId = null;
        int Id_num = 0 ;
        while(iterator.hasNext()) {
            Map.Entry<String, AccountPo> entry = iterator.next();
            if(entry.getValue().getMemberName().equals(name))
                return "FAIL";  //用户名已存在，不能重复注册
            newId = entry.getKey();
            int temp = Integer.parseInt(newId.substring(1));
            if(temp>Id_num)
            	Id_num=temp;
        }
        Id_num ++ ;  //默认不会超出五位数
        newId = newId.charAt(0)+ String.format("%05d",Id_num);
        
        AccountPo newAccPo = new AccountPo(name,password,newId,0);
        map.put(newId, newAccPo);
        updateMap(map,type);
        
        accountDataHelper.updateAccountData(map, type);
        
        MemberPo newMemPo = null;
		try {
			newMemPo = new MemberPo(newId,name);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MemberVo newMemVo = new MemberVo(newMemPo);
		if(type==AccountType.Member)
			MemberDaoImpl.getInstance().insertInfo(newMemVo);
		else if(type==AccountType.Enterprise)
			MemberDaoImpl.getInstance().insertInfo(newMemVo);
		
        return newId;
    }

    /**对AccountDaoImpl类持有的map对象进行数据更新，再更新txtData里的数据*/
    public ResultMessage updateAccount(AccountVo vo){
        String id = vo.getId() ;
        AccountType type = vo.getAccountType();
        TreeMap<String,AccountPo> map = getMap(type);
        if(map.containsKey(id))
        {
        	AccountPo po = map.get(id) ;
        	po.setAccountType(vo.getAccountType());
        	po.setMemberName(vo.getMemberName());
        	po.setLogState(vo.getLogState());
        	po.setPassword(vo.getPassword());
            map.put(id, po);
            accountDataHelper.updateAccountData(map,type);
            return ResultMessage.SUCCEED;
        }
        else
            return ResultMessage.FAIL;
    }

    public ResultMessage deleteAccount(String id){
    	AccountType type = getAccountType(id);
    	TreeMap<String,AccountPo> map = getMap(type);
        if(map.containsKey(id))
        {
            map.remove(id);
            accountDataHelper.updateAccountData(map,type);
            
            MemberDaoImpl.getInstance().deleteInfo(id);
            return ResultMessage.SUCCEED;
        }
        else
            return ResultMessage.FAIL;
    }

    public TreeMap<String,AccountPo> getMap(AccountType type){
    	TreeMap<String,AccountPo> map;
    	switch(type){
    	 case Member  	    : map = this.map_member; 		break;
    	 case Enterprise	: map = this.map_enterprise;	break;
         case Marketer 		: map = this.map_marketer;		break;
         case Hotel   	    : map = this.map_hotel;			break;
         case Manager       : map = this.map_manager;		break;
         default            : map = null ;
    	}
    	return map;
    }
    
    public void updateMap(TreeMap<String,AccountPo> map,AccountType type){
    	switch(type){
    	case Member    : this.map_member    = map ;		break;
    	case Enterprise: this.map_enterprise= map ;		break;
        case Marketer  : this.map_marketer  = map ;		break;
        case Hotel     : this.map_hotel     = map ;		break;
        case Manager   : this.map_manager   = map ;		break;
        default:         this.map_member    = null ;
    	}
    }
    
    public AccountType getAccountType(String id){
    	char label = id.charAt(0);
    	AccountType type ;
    	switch (label){
        case 'A': type =  AccountType.Manager ; 	break;
        case 'M': type =  AccountType.Marketer;		break;
        case 'H': type =  AccountType.Hotel;		break;
        case 'N': type =  AccountType.Member;		break;
        case 'E': type =  AccountType.Enterprise;	break;
        default : type =  null;
    	}
    	return type;
    }
    
    
}
