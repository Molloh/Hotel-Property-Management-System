/*package myTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import common.Sex;
import presentation.controller.Impl.MemberMainViewControllerServiceImpl;
import presentation.controller.service.MemberMainViewControllerService;
import runner.ClientRunner;
import vo.MemberVo;


public class TestMember {
	private MemberMainViewControllerService test  ;
	private String id_1 ;
	private String id_2 ;
	private ClientRunner clientRunner ;
	
	@Before
	public void setUp() throws Exception {
		clientRunner = new ClientRunner();
		test = MemberMainViewControllerServiceImpl.getInstance() ;
		id_1 =  "N00001" ;
		id_2 =  "N00002" ;
	}

	@Test
	public void testGetCredit() {
		int credit = test.getCredit(id_2);
		Assert.assertEquals(100, credit);
	}

	@Test
	public void testChargeCredit() {
		int ori = test.getCredit(id_1);
		int charge = 1 ;
		int expected = ori + charge ;
		test.chargeCredit(id_1, charge);
		Assert.assertEquals(expected, test.getCredit(id_1));
		
	}

	@Test
	public void testGetInfo() {
		MemberVo actual_vo = test.getInfo(id_1) ;
		Assert.assertEquals("JiangZeMin", actual_vo.getName());
		Assert.assertEquals("99900009999",actual_vo.getPhone());
		Assert.assertEquals("BeiJing",actual_vo.getAddress());
		Assert.assertEquals(Sex.MALE,actual_vo.getSex());
	}

	@Test
	public void testModifyInfo() {
		String set = "99900008888";
		MemberVo actual_vo = test.getInfo(id_2) ;
		actual_vo.setPhone("99900008888");
		test.modifyInfo(actual_vo);
		actual_vo = test.getInfo(id_2);
		Assert.assertEquals("99900008888", actual_vo.getPhone());
	}


}*/
