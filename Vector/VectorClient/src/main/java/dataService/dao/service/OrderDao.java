package dataService.dao.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
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
	
	//网站营销人员查看每日未执行订单
	public List<OrderPo> getSubmitted(String date) throws RemoteException;
	
	//网站营销人员查看异常订单
	public List<OrderPo> getFailed() throws RemoteException;
	
	
}
