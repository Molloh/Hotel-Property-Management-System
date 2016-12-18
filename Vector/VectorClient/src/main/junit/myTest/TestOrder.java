package myTest;

import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import businessLogic.impl.OrderBlServiceImpl;
import businessLogic.service.OrderBlService;

public class TestOrder {
	private OrderBlService test;
	private TestClient clientRunner;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	
	@Before
	public void setUp() throws Exception {
		clientRunner = new TestClient();
		test = OrderBlServiceImpl.getInstance() ;
	}
	
	@Test
	public void testGetOrderId() {
		Assert.assertEquals("2016121812345", test.getOrderId("2016121812345"));
	}

	@Test
	public void testGetOrderCondition() {
		Assert.assertEquals("WAITING", test.getOrderCondition("2016121812345"));
	}

	@Test
	public void testGetMemberId() {
		Assert.assertEquals("N00001", test.getMemberId("2016121812345"));
	}

	@Test
	public void testGetMemberName() {
		Assert.assertEquals("MaoZeDong", test.getMemberName("2016121812345"));
	}

	@Test
	public void testGetCreateTime() {
		Assert.assertEquals("2016-12-18 00:00:00", sdf.format(test.getCreateTime("2016121812345")));
	}

	@Test
	public void testGetCheckInTime() {
		Assert.assertEquals("1970-01-01 00:00:00", sdf.format(test.getCreateTime("2016121812345")));
	}

	@Test
	public void testGetCheckOutTime() {
		Assert.assertEquals("1970-01-01 00:00:00", sdf.format(test.getCreateTime("2016121812345")));
	}

	@Test
	public void testGetHotelName() {
		Assert.assertEquals("布丁酒店", test.getHotelName("2016121812345"));
	}

	@Test
	public void testGetHotelId() {
		Assert.assertEquals("H00001", test.getHotelId("2016121812345"));
	}

	@Test
	public void testGetRoomType() {
		Assert.assertEquals("DOUBLE", test.getRoomType("2016121812345"));
	}

	@Test
	public void testGetNumOfRoom() {
		Assert.assertEquals(2, test.getNumOfRoom("2016121812345"));
	}

	@Test
	public void testGetNumOfGuest() {
		Assert.assertEquals(2, test.getNumOfGuest("2016121812345"));
	}

	@Test
	public void testGetChildExist() {
		Assert.assertEquals(false, test.getChildExist("2016121812345"));
	}

	@Test
	public void testGetOriginalPrice() {
		Assert.assertEquals(130, test.getOriginalPrice("2016121812345"));
	}

	@Test
	public void testGetDiscount() {
		Assert.assertEquals(0.5, test.getDiscount("2016121812345"), 0.1);
	}

	@Test
	public void testGetDiscountedPrice() {
		Assert.assertEquals(260, test.getDiscountedPrice("2016121812345"));
	}

	@Test
	public void testSetToAbnormal() {
		Assert.assertEquals("ABNORMAL", test.getOrderCondition("2016121812345"));
	}

	@Test
	public void testCancel() {
		Assert.assertEquals("CANCEL", test.getOrderCondition("2016121812345"));
	}

	@Test
	public void testCheckIn() {
		Assert.assertEquals("EXCUTING", test.getOrderCondition("2016121812345"));
	}

	@Test
	public void testAbnormalCheckIn() {
		Assert.assertEquals("EXCUTING", test.getOrderCondition("2016121812345"));
	}

	@Test
	public void testCheckOut() {
		Assert.assertEquals("EXCUTED", test.getOrderCondition("2016121812345"));
	}

	@Test
	public void testRevoke() {
		Assert.assertEquals("REVOKED", test.getOrderCondition("2016121812345"));
	}
	
	@Test
	public void testSetToFinished() {
		Assert.assertEquals("FINISHED", test.getOrderCondition("2016121812345"));
	}

}
