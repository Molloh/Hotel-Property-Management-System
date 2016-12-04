package dataService.dao.impl;
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

/**
 * Created by Administrator on 2016-11-27.
 */
public class AccountDaoImpl implements AccountDao {

    /* map<Id,AccountPO> */
    private TreeMap<String,AccountPo> map;

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
        if(map == null){
            dataFactory = new DataFactoryImpl();
            accountDataHelper = dataFactory.getAccountDataHelper();
            map = accountDataHelper.getAccountData();
        }
    }


    public AccountType login(String id, String password) {
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
                    update(po);       //默认更新po信息成功
                    return po.getAccountType(id);
                }
                else
                    return AccountType.Fail; //账号存在,密码错误
            }
        }
        return AccountType.Fail; //账号名不存在

    }

    public ResultMessage logout(String id) {
        if( map.containsKey(id) )
        {
        AccountPo po = map.get(id); 
        if( po.getLogState()==0 )
            return ResultMessage.FAIL;

        po.setLogState(0);
        update(po);
        return ResultMessage.SUCCEED;
        }
        else return ResultMessage.FAIL;
    }

    public String register(String memberName,String password) {
        Iterator<Map.Entry<String, AccountPo>> iterator = map.entrySet().iterator();
        String newId = "" ;
        /*找到accountPo库最末尾的Id，以方便创建一个新的Id，最后写入
                          过程中遍历之前的每一个memberName
         */
        int Id_num = 0 ;
        while(iterator.hasNext()) {
            Map.Entry<String, AccountPo> entry = iterator.next();
            if(entry.getValue().getMemberName().equals(memberName))
                return "FAIL";  //用户名已存在，不能重复注册
            newId = entry.getKey();
            int temp = Integer.parseInt(newId.substring(1));
            if(temp>Id_num)
            	Id_num=temp;
        }
       
        
        Id_num ++ ;  //默认不会超出五位数
        newId = newId.charAt(0) + String.format("%05d",Id_num);
        AccountPo newAccPo = new AccountPo(memberName,password,newId,0);
        ResultMessage w=insert(newAccPo) ;
        
        MemberPo newMemPo = new MemberPo(newId,memberName,null,null,null,100);
        MemberDaoImpl.getInstance().insert(newMemPo);
        if(w==ResultMessage.SUCCEED)
        	return newId;
        else 
        	return "??";
    }

    public ResultMessage modify(String id,String newPassword) {
    	 if( map.containsKey(id) )
         {
         AccountPo po = map.get(id); 
         if( po.getPassword().equals(newPassword))
             return ResultMessage.FAIL;

         po.setPassword(newPassword);
         update(po);
         return ResultMessage.SUCCEED;
         }
         else return ResultMessage.FAIL;
    }

    public AccountVo find(String id){
    	if( map.containsKey(id) )
        {
    		AccountPo po = map.get(id); 
    		AccountVo vo = new AccountVo(po);
    		return vo;
        }
        else return null;
    }

    public ResultMessage insert(AccountPo po) {
        if(!map.containsKey(po.getId())) {
            map.put(po.getId(), po); 
            accountDataHelper.updateAccountData(map);
            return ResultMessage.SUCCEED;
        }
        else
            return ResultMessage.FAIL; //已存在
    }

    /**对AccountDaoImpl类持有的map对象进行数据更新，再更新txtData里的数据
    *  完全可由 map.put(int Id,AccountPo po)代替
    */
    public ResultMessage update(AccountPo po){
        String id = po.getId() ;
        if(map.containsKey(id))
        {
            map.put(id, po);
            accountDataHelper.updateAccountData(map);
            return ResultMessage.SUCCEED;
        }
        else
            return ResultMessage.FAIL;
    }


    public ResultMessage delete(String id){
        if(map.containsKey(id))
        {
            map.remove(id);
            accountDataHelper.updateAccountData(map);
            
            MemberDaoImpl.getInstance().delete(id);
            return ResultMessage.SUCCEED;
        }
        else
            return ResultMessage.FAIL;
    }

}
