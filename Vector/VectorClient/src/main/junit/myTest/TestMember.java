package myTest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.MemberBlServiceImpl;
import businessLogic.service.MemberBlService;
import common.Sex;
import vo.MemberVo;


public class TestMember {
	private MemberBlService test  ;
	private TestClient clientRunner ;
	
	@Before
	public void setUp() throws Exception {
		clientRunner = new TestClient();
		test = MemberBlServiceImpl.getInstance() ;
	}

	@Test
	public void testGetCredit() {
		int credit = test.getCredit("N00002");
		Assert.assertEquals(100, credit);
	}

	@Test
	public void testChargeCredit() {
		int ori = test.getCredit("N00001");
		int charge = 1 ;
		test.chargeCredit("N00001", charge);
		Assert.assertEquals(ori + charge, test.getCredit("N00001"));
		
	}

	@Test
	public void testGetInfo() {
		MemberVo actual_vo = test.getInfo("N00001") ;
		Assert.assertEquals("JiangZeMin", actual_vo.getName());
		Assert.assertEquals("99900009999",actual_vo.getPhone());
		Assert.assertEquals("BeiJing",actual_vo.getAddress());
		Assert.assertEquals(Sex.MALE,actual_vo.getSex());
	}

	@Test
	public void testModifyInfo() {
		String set = "99900008888";
		MemberVo actual_vo = test.getInfo("N00002") ;
		actual_vo.setPhone("99900008888");
		test.modifyInfo(actual_vo);
		actual_vo = test.getInfo("N00002");
		Assert.assertEquals("99900008888", actual_vo.getPhone());
	}


}
