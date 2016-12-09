package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.List;

import common.AccountType;
import common.ResultMessage;
import common.RoomType;
import dataService.dao.impl.AccountDaoImpl;
import dataService.dao.impl.HotelDaoImpl;
import dataService.dao.impl.MemberDaoImpl;
import dataService.dao.impl.OrderDaoImpl;
import dataService.dao.service.AccountDao;
import dataService.dao.service.HotelDao;
import dataService.dao.service.MemberDao;
import dataService.dao.service.OrderDao;
import po.AccountPo;
import po.HotelPo;
import po.OrderPo;
import vo.AccountVo;
import vo.MemberVo;

/**
 * Created by Administrator on 2016-11-20.
 */
public class DataRemoteObject extends UnicastRemoteObject implements AccountDao,MemberDao,HotelDao,OrderDao{


	private static final long serialVersionUID = 1L;

    private AccountDao accountDao;
    private MemberDao memberDao ;
    private HotelDao hotelDao;
    private OrderDao orderDao;

    protected DataRemoteObject() throws RemoteException{
        accountDao = AccountDaoImpl.getInstance();
        memberDao = MemberDaoImpl.getInstance();
        hotelDao = HotelDaoImpl.getInstance();
        orderDao = OrderDaoImpl.getInstance();
    }


    /* AccountDao 接口方法 */
    public AccountType login(String id, String password)  throws RemoteException {
        return accountDao.login(id,password);
    }

    public ResultMessage logout(String id) throws RemoteException{
        return accountDao.logout(id);
    }

    public String register(String memberName, String password) throws RemoteException{
        return accountDao.register(memberName,password);
    }

    public ResultMessage modify(String id, String newPassword) throws RemoteException{
        return accountDao.modify(id,newPassword);
    }

    public AccountVo find(String id) throws RemoteException{
        return accountDao.find(id);
    }

    public ResultMessage insert(AccountPo po) throws RemoteException{
        return accountDao.insert(po);
    }

    public ResultMessage update(AccountPo po) throws RemoteException{
        return accountDao.update(po);
    }

    public ResultMessage delete(String id) throws RemoteException{
        return accountDao.delete(id);
    }

    /* MemberDao接口方法  */

    public int getCredit(String id)throws RemoteException {
    	return memberDao.getCredit(id);
    }

    public ResultMessage chargeCredit(String id, int amount)throws RemoteException {
    	return memberDao.chargeCredit(id, amount);
    }

    public MemberVo getInfo(String id)throws RemoteException {
    	return memberDao.getInfo(id);
    }

    public ResultMessage modifyInfo(MemberVo vo)throws RemoteException {
    	return memberDao.modifyInfo(vo);
    }


    /*HotelDao 接口方法*/
   	public ResultMessage addHotelPO(HotelPo po) throws RemoteException{
   		return hotelDao.addHotelPO(po);
   	}

   	public ResultMessage updateHotelList(HotelPo po) throws RemoteException{
   		return hotelDao.updateHotelList(po);
   	}

   	public ResultMessage deleteHotelPO(String hotelId) throws RemoteException{
   		return hotelDao.deleteHotelPO(hotelId);
   	}

   	public HotelPo findHotel(String hotelId) throws RemoteException{
   		return hotelDao.findHotel(hotelId);
   	}

   	public List<HotelPo> keyFind(String key) throws RemoteException{
   		return hotelDao.keyFind(key);
   	}

   	public ResultMessage updateComment(HotelPo po) throws RemoteException{
   		return hotelDao.updateComment(po);
   	}

   	public ResultMessage initHotelTypeRoom(String hotelId, RoomType type, int number, int price) throws RemoteException {
   		return hotelDao.initHotelTypeRoom(hotelId, type, number, price);
   	}

   	public ResultMessage updateBookDate(HotelPo po, RoomType type) throws RemoteException {
   		return hotelDao.updateBookDate(po, type);
   	}

   	   	/*OrderDao 接口方法*/

	public ResultMessage insertOrder(OrderPo po) throws RemoteException {
		return orderDao.insertOrder(po);
	}

	public OrderPo findOrder(String orderId) throws RemoteException {
		return orderDao.findOrder(orderId);
	}

	public ResultMessage updateOrder(OrderPo po) throws RemoteException {
		return orderDao.updateOrder(po);
	}

	public ResultMessage deleteOrder(String orderId) throws RemoteException {
		return orderDao.deleteOrder(orderId);
	}

	public List<OrderPo> getAllByHotel(String hotelId) throws RemoteException {
		return orderDao.getAllByHotel(hotelId);
	}

	public List<OrderPo> getAllByMember(String memberId) throws RemoteException {
		return orderDao.getAllByMember(memberId);
	}

	//网站营销人员查看每日未执行订单
	public List<OrderPo> getNotExecuted(Date date) throws RemoteException {
		return orderDao.getNotExecuted(date);
	}

	//网站营销人员查看异常订单
	public List<OrderPo> getAbnormal(Date date) throws RemoteException {
		return orderDao.getAbnormal(date);
	}

}