package businessLogic.service;


import common.AccountType;
import common.ResultMessage;
import vo.AccountVo;

/**
 * AccountBLService
 * @author lienming
 * @version 2016-11-27
 */


public interface AccountBlService {

	/* 1.interface to Member Client*/

	/**
	 * 登入：Id+密码
	 * @param id :String     如Member的为:"N00001"
	 * @param password : String
	 * @return 用户类型  : AccountType
	 */
	public AccountType login(String id, String password);

	/**
	 * 通过Id登出
	 * @param id : String
	 * @return ResultMessage
	 */
	public ResultMessage logout(String id);

	/**
	 * @param memberName
	 * @param password
	 * @return 返回值为ID（成功）/"FAIL"（失败）
	 */
	public String register(String name, String password) ;

	public ResultMessage modifyPassword(String id, String newPassword) ;

	/**
	 * 长度[4,12] 只能由大小写字符和数字组成
	 * @param input
	 * @return ResultMessage
	 */
	public ResultMessage checkInput(String input) ;

	public AccountVo findAccount(String id);



	/* 2.interface to Manager Client  */

	public String insertAccount(String name,String password,AccountType type) ;

	public ResultMessage updateAccount(AccountVo vo) ;

	public ResultMessage deleteAccount(String id) ;

	

}
