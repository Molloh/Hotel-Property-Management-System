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
	
	public ResultMessage insert (OrderPo po) throws RemoteException;
	
	public OrderPo find(String orderId) throws RemoteException;
	
	public ResultMessage update(OrderPo po) throws RemoteException;
	
	public ResultMessage delete(String orderId) throws RemoteException;
	
	public List<OrderPo> getAllByHotel(String hotelId) throws RemoteException;
	
	public List<OrderPo> getAllByMember(String memberId) throws RemoteException;
	
	//网站营销人员查看每日未执行订单
	public List<OrderPo> getNotExecuted(Date date) throws RemoteException;
	
	//网站营销人员查看异常订单
	public List<OrderPo> getAbnormal(Date date) throws RemoteException;

}
