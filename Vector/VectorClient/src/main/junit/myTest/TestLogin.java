package myTest;


import common.AccountType;
import common.ResultMessage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import presentation.controller.Impl.LoginViewControllerServiceImpl;
import presentation.controller.service.LoginViewControllerService;
import runner.ClientRunner;


public class TestLogin {
	private LoginViewControllerService test ;
	private ClientRunner clientRunner ;
	
	private String id ;
	private String password ; 
	
	@Before
	public void setUp() throws Exception {
		id = "N00001" ;
		password = "123456";
		
		clientRunner = new ClientRunner();
		/*注：必须先开Client再开Service插件*/
		test = LoginViewControllerServiceImpl.getInstance();
		
	}

	@Test
	public void testLogin() {
		
		AccountType actual  = test.login(id, password);
		AccountType expected= AccountType.Member;
		Assert.assertEquals(expected,actual);

	}

	@Test
	public void testLogout() {
		Assert.assertEquals(ResultMessage.SUCCEED, test.logout(id) );
	}
	
}
