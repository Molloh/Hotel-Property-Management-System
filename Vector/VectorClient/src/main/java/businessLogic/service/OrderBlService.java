package businesslogic;

import java.rmi.RemoteException;
import java.util.List;

import common.ResultMessage;
import vo.OrderVo;

/**
 * @ author Aobang
 * @ version 2016/11/27
 * @ description
 */
public interface OrderBlService {
	
	public String getOrderUser(String orderId) throws RemoteException;
	
	public String getOrderPrice(String orderId) throws RemoteException;
	
	public String getOrderTime(String orderId) throws RemoteException;
	
	public List<OrderVo> getAbnormalOrders(String Id) throws RemoteException;
	
	public ResultMessage submit(String orderId) throws RemoteException;
	
	public ResultMessage delete(String orderId) throws RemoteException;
	
	public List<OrderVo> getAllOrders(String hotelId) throws RemoteException;
	
	public List<OrderVo> getFinshedOrders(String memberId) throws RemoteException;
	
	public List<OrderVo> getUnfinishedOrders(String memberId) throws RemoteException;
	
	public List<OrderVo> getCanceledOrders(String memberID) throws RemoteException;
	
	public ResultMessage execute(String orderId) throws RemoteException;
	
	public ResultMessage recover(String orderId) throws RemoteException;
	
	public ResultMessage revoke(String orderId) throws RemoteException;
}
