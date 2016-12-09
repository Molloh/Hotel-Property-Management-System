package dataService.dao.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import common.ResultMessage;
import po.OrderPo;

/**
 * @ author Aobang
 * @ version 2016/11/27
 * @ description
 */
public interface OrderDao extends Remote {
	
	public ResultMessage insertOrder (OrderPo po);
	
	public OrderPo findOrder(String orderId);
	
	public ResultMessage updateOrder(OrderPo po);
	
	public ResultMessage deleteOrder(String orderId);
	
	public List<OrderPo> getAllByHotel(String hotelId);
	
	public List<OrderPo> getAllByMember(String memberId);
	
	//网站营销人员查看每日未执行订单
	public List<OrderPo> getNotExecuted(Date date);
	
	//网站营销人员查看异常订单
	public List<OrderPo> getAbnormal(Date date);

}
