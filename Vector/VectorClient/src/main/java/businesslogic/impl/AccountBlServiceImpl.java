package businessLogic.impl;

import businessLogic.service.AccountBlService;
import common.AccountType;
import common.ResultMessage;
import dataService.dao.service.AccountDao;
import rmi.RemoteHelper;
import vo.AccountVo;

/**
 * Updated by lienming on 2016-11-27.
 */
public class AccountBlServiceImpl implements AccountBlService{

    private AccountDao accountDao;

    private static AccountBlServiceImpl accountBlServiceImpl;

    public static AccountBlServiceImpl getInstance(){
        if( accountBlServiceImpl == null)
            accountBlServiceImpl = new AccountBlServiceImpl() ;
        return accountBlServiceImpl;
    }

    private AccountBlServiceImpl(){
        accountDao = RemoteHelper.getInstance().getAccountDao();
    }

    public AccountType login(String id, String password){
        //先检查输入
        ResultMessage idValid = checkInput(id);
        ResultMessage passwordValid   = checkInput(password) ;
        if(idValid == ResultMessage.VALID
                && passwordValid == ResultMessage.VALID )
            return accountDao.login(id,password);
        else
            return AccountType.Fail; //输入非法
    }

    public ResultMessage logout(String id) {
        return accountDao.logout(id);
    }

    public String register(String name,String password,boolean isMember) {
    	AccountType type;
    	if(isMember) 
    		type = AccountType.Member;
    	else type = AccountType.Enterprise;
    	
    	return insertAccount(name,password,type);
    }

    public ResultMessage modifyPassword(String id,String newPassword){
    	if(newPassword.length()>=4 && newPassword.length()<=12)
    		return accountDao.modifyPassword(id,newPassword);
    	return ResultMessage.FAIL;
    }

    /**
     * 长度限制为[4,12] ；
     * 字符限制为：大小写字母、数字 ；
	 * 是否敏感大小写   (未完成)；
	 * 注意：返回ResultMessage.VALID/INVALID
     */
    public ResultMessage checkInput(String input){
        if(input.length()<=3 || input.length()>12)
            return ResultMessage.INVALID;
        else
        {
        	for(int i=input.length()-1;i>=0;i--)
        	{
        		char ch = input.charAt(i); 
        		if( (48<=ch && ch<=57) || (65<=ch&& ch<=90) || 
        				(97<=ch&&ch<=122) )
        			continue;
        		else 
        			return ResultMessage.INVALID;
        	}
            return ResultMessage.VALID;
        }
    }

    public AccountVo findAccount(String id) {
        return accountDao.findAccount(id);
    }

    public String insertAccount(String name,String password,AccountType type) {
    	 //先检查输入
        ResultMessage nameValid = checkInput(name);
        ResultMessage passwordValid   = checkInput(password) ;
        if(nameValid == ResultMessage.VALID
                && passwordValid == ResultMessage.VALID )
        {
        	 return accountDao.insertAccount(name,password,type);
        }
        return null;
    }

    public ResultMessage updateAccount(AccountVo vo) {
        return accountDao.updateAccount(vo);
    }

    public ResultMessage deleteAccount(String id) {
        return accountDao.deleteAccount(id);
    }

    public AccountType getAccountTypeById(String id){
    	char label = id.charAt(0);
    	switch(label){
    	case 'N': 	
    	case 'E':   return AccountType.Member;
    	case 'A':   return AccountType.Manager;
    	case 'M':   return AccountType.Marketer;
    	case 'H':   return AccountType.Hotel;
    	default:    return AccountType.Fail;
    	}
    }
}
