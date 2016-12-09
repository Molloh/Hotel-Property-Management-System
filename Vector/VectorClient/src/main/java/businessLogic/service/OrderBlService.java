package businessLogic.service;

import java.rmi.RemoteException;
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
	
	public String getOrderId(String orderId) throws RemoteException;
	
	public OrderCondition getOrderCondition(String orderId) throws RemoteException;
	
	public String getMemberId(String orderId) throws RemoteException;
	
	public String getMemberName(String orderId) throws RemoteException;
	
	public Date getCreateTime(String orderId) throws RemoteException;
	
	public Date getCheckInTime(String orderId) throws RemoteException;
	
	public Date getCheckOutTime(String orderId) throws RemoteException;
	
	public String getHotelName(String orderId) throws RemoteException;
	
	public String getHotelId(String orderId) throws RemoteException;
	
	public RoomType getRoomType(String orderId) throws RemoteException;
	
	public int getNumOfRoom(String orderId) throws RemoteException;
	
	public int getNumOfGuest(String orderId) throws RemoteException;
	
	public boolean getChildExist(String orderId) throws RemoteException;
	
	public int getOriginalPrice(String orderId) throws RemoteException;
	
	public double getDiscount(String orderId) throws RemoteException;
	
	public double getDiscountedPrice(String orderId) throws RemoteException;
	
	public List<OrderVo> getAllOrdersByHotel(String hotelId) throws RemoteException;
	
	public List<OrderVo> getAllOrdersByMember(String memberId) throws RemoteException;
	
	public List<OrderVo> getOrdersInConditionByHotel(String hotelId, OrderCondition condition) throws RemoteException;
	
	public List<OrderVo> getOrdersInConditionByMember(String memberId, OrderCondition condition) throws RemoteException;
	
	public List<OrderVo> getNotExecutedOrderByWebSite(Date date) throws RemoteException;
	
	public List<OrderVo> getAbnormalByWebSite(Date date) throws RemoteException;
	
	public ResultMessage submit() throws RemoteException;
	
	public ResultMessage setToAbnormal(String orderId) throws RemoteException;
	
	public ResultMessage cancel(String orderId) throws RemoteException;
	
	public ResultMessage checkIn(String orderId) throws RemoteException;

	public ResultMessage abnormalCheckIn(String orderId) throws RemoteException;
	
	public ResultMessage checkOut(String orderId) throws RemoteException;
	
	public ResultMessage revoke(String orderId) throws RemoteException;
	
	public ResultMessage delete(String orderId) throws RemoteException;
}
