package businessLogic.service;

import java.util.Date;
import java.util.List;

import common.OrderCondition;
import common.ResultMessage;
import common.RoomType;
import vo.OrderVo;

/**
 * @ author Aobang
 * @ version 2016/11/27
 * @ description
 */
public interface OrderBlService {
	
	public String getOrderId(String orderId);
	
	public OrderCondition getOrderCondition(String orderId);
	
	public String getMemberId(String orderId);
	
	public String getMemberName(String orderId);
	
	public Date getCreateTime(String orderId);
	
	public Date getCheckInTime(String orderId);
	
	public Date getCheckOutTime(String orderId);
	
	public String getHotelName(String orderId);
	
	public String getHotelId(String orderId);
	
	public RoomType getRoomType(String orderId);
	
	public int getNumOfRoom(String orderId);
	
	public int getNumOfGuest(String orderId);
	
	public boolean getChildExist(String orderId);
	
	public int getOriginalPrice(String orderId);
	
	public double getDiscount(String orderId);
	
	public double getDiscountedPrice(String orderId);
	
	public List<OrderVo> getAllOrdersByHotel(String hotelId);
	
	public List<OrderVo> getAllOrdersByMember(String memberId);
	
	public List<OrderVo> getOrdersInConditionByHotel(String hotelId, OrderCondition condition);
	
	public List<OrderVo> getOrdersInConditionByMember(String memberId, OrderCondition condition);
	
	public List<OrderVo> getNotExecutedOrderByWebSite();
	
	public List<OrderVo> getAbnormalByWebSite();
	
	public ResultMessage submit(String memberId, String planCheckInTime, String hoteId, int numOfDays,
			RoomType roomType, int numOfRoom, int numOfGuest, boolean childExist);

	public ResultMessage setToAbnormal();
	
	public ResultMessage cancel(String orderId);
	
	public ResultMessage checkIn(String orderId);

	public ResultMessage abnormalCheckIn(String orderId);
	
	public ResultMessage checkOut(String orderId);
	
	public ResultMessage revoke(String orderId, double allOrHalf);
	
	public ResultMessage delete(String orderId);
	
	public ResultMessage setToFinished(String orderId); //提供给Hotel评价使用
}
