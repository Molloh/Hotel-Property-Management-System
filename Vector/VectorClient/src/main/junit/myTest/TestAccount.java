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
	public void testLogin() {
		Assert.assertEquals(AccountType.Fail,test.login("N00001", "00123456"));
		Assert.assertEquals(AccountType.Member,test.login("N00001", "123456"));
		Assert.assertEquals(AccountType.Fail,test.login("N00001", "123456")); 
	}

	@Test
	public void testLogout() {
		Assert.assertEquals(ResultMessage.SUCCEED,test.logout("N00001"));
		Assert.assertEquals(ResultMessage.FAIL,test.logout("N00001"));
	}

	@Test
	public void testRegister() {
		Assert.assertEquals("FAIL", test.register("LiKeQiang", "66666666"));
	}

	@Test
	public void testModify() {
		test.modify("N00002","99999999");
		Assert.assertEquals("99999999",test.find("N00002").getPassword());
	}

	@Test
	public void testFind() {
	}

	@Test
	public void testInsert() {
	}

	@Test
	public void testUpdate() {
	}

	@Test
	public void testDelete() {
	}

}
