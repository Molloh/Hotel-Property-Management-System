package myTest;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.service.AccountBlService;
import common.AccountType;
import common.ResultMessage;
import vo.AccountVo;

public class TestAccount {
	
	private AccountBlService test ;
	private TestClient clientRunner ;
	
	@Before
	public void setUp() throws Exception {
		clientRunner = new TestClient();
		/*注：必须先开Client再开Service插件*/
		test = AccountBlServiceImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		clientRunner = null;
		test=null;
	}

	@Test
	public void testRegister() {
		//test member
		Assert.assertEquals("N00007", test.register("HuJinTao", "20160890z",true));
		Assert.assertEquals("N00008", test.register("WenJiaBao", "q204900q",true));
		Assert.assertEquals("N00009", test.register("BoXiLai", "qwertasdfg",true));
		Assert.assertEquals("FAIL"  , test.register("BoXiLai", "qwertasdfg",true));
		//test enterprise
		Assert.assertEquals("E00005", test.register("XinLang", "0009998",false));
		Assert.assertEquals("E00006", test.register("Tencent", "268889z",false));
		Assert.assertEquals("E00007", test.register("Baidu", "2mmmmm0z",false));
		Assert.assertEquals("FAIL"  , test.register("Baidu", "2mmmmm0z",false));
		Assert.assertEquals("FAIL"  , test.register("Tencent", "268889z",false));
	}

	@Test
	public void testLogin() {
		//test member 
		Assert.assertEquals(AccountType.Fail,test.login("N00001", "00123456"));
		Assert.assertEquals(AccountType.Member,test.login("N00001", "1234567"));
		Assert.assertEquals(AccountType.Fail,test.login("N00001", "1234567")); 
		//test enterprise
		Assert.assertEquals(AccountType.Fail,test.login("E00001", "00123456"));
		Assert.assertEquals(AccountType.Member,test.login("E00001", "123456"));
		Assert.assertEquals(AccountType.Fail,test.login("E00001", "123456")); 
	}

	@Test
	public void testLogout() {
		//test member
		Assert.assertEquals(ResultMessage.SUCCEED,test.logout("N00001"));
		Assert.assertEquals(ResultMessage.FAIL,test.logout("N00001"));
		Assert.assertEquals(ResultMessage.FAIL,test.logout("N00002"));
		//test enterprise
		Assert.assertEquals(ResultMessage.SUCCEED,test.logout("E00001"));
		Assert.assertEquals(ResultMessage.FAIL,test.logout("E00001"));
	}


	@Test
	public void testFind() {
		Assert.assertEquals("MaoZeDong",test.findAccount("N00001").getMemberName());
	}
	
	
	@Test
	public void testModify() {
		//test member
		test.modifyPassword("N00003","99999999");
		Assert.assertEquals("99999999",test.findAccount("N00002").getPassword());
		//test enterprise
		test.modifyPassword("E00003","99999999");
		Assert.assertEquals("99999999",test.findAccount("E00003").getPassword());
	}


	@Test
	public void testInsert() {
		//test insert manager
		test.insertAccount("Admin", "Admin", AccountType.Manager);
		Assert.assertEquals("Admin",test.findAccount("A00002").getMemberName());
		//test insert marketer
		test.insertAccount("Jack", "Monica", AccountType.Marketer);
		Assert.assertEquals("Monica",test.findAccount("M00002").getPassword());
		//test insert hotel
		test.insertAccount("TrumpHotel", "american", AccountType.Hotel);
		Assert.assertEquals(0,test.findAccount("H00002").getLogState());
	}

	@Test
	public void testUpdate() {
		//test update hotel logState
		AccountVo vo = test.findAccount("H00001");
		vo.setLogState(1);
		test.updateAccount(vo);
		Assert.assertEquals(1, test.findAccount("H00001").getLogState());
		test.logout("H00001");
	}

	@Test
	public void testDelete() {
		//test delete member
		test.deleteAccount("N00005");
		Assert.assertEquals(null, test.findAccount("N00005"));
	}

}
