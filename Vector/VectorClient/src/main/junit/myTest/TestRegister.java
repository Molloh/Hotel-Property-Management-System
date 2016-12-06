/*package myTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import presentation.controller.Impl.LoginViewControllerServiceImpl;
import presentation.controller.Impl.RegisterViewControllerServiceImpl;
import presentation.controller.service.LoginViewControllerService;
import presentation.controller.service.RegisterViewControllerService;
import runner.ClientRunner;

public class TestRegister {
	private RegisterViewControllerService test ; 
	private LoginViewControllerService inter ;
	private ClientRunner clientRunner ;
	
	private String name ; 
	private String password ;
	
	@Before
	public void setUp() throws Exception {
		clientRunner = new ClientRunner();
		test = RegisterViewControllerServiceImpl.getInstance();
		inter= LoginViewControllerServiceImpl.getInstance();
		name = "LiKeQiang";
		password= "66666666";
	}

	@Test
	public void testRegister() {
		String actual= test.register(name, password);
		Assert.assertEquals("N00003", actual);
	}

	@Test
	public void testModify() {
		String newPassword = "99999999";
		test.modify("N00002", newPassword);
		Assert.assertEquals(newPassword,inter.find("N00002").getPassword());
	}

}*/
