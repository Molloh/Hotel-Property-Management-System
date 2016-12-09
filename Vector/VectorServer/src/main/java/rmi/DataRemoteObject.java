package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import common.AccountType;
import common.ResultMessage;
import common.RoomType;
import dataService.dao.impl.AccountDaoImpl;
import dataService.dao.impl.HotelDaoImpl;
import dataService.dao.impl.MemberDaoImpl;
import dataService.dao.service.AccountDao;
import dataService.dao.service.HotelDao;
import dataService.dao.service.MemberDao;
import po.AccountPo;
import po.HotelPo;
import vo.AccountVo;
import vo.MemberVo;

/**
 * Updated by lienming on 2016-12-08.
 */
public class DataRemoteObject extends UnicastRemoteObject implements AccountDao,MemberDao,HotelDao{

	 
	private static final long serialVersionUID = 1L;

    private AccountDao accountDao;
    private MemberDao memberDao ;
    private HotelDao hotelDao;
    
    protected DataRemoteObject() throws RemoteException{
        accountDao = AccountDaoImpl.getInstance();
        memberDao = MemberDaoImpl.getInstance();
        hotelDao = HotelDaoImpl.getInstance();
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

    public ResultMessage modifyPassword(String id, String newPassword) 
    		throws RemoteException{
        return accountDao.modifyPassword(id,newPassword);
    }

    public AccountVo findAccount(String id) throws RemoteException{
        return accountDao.findAccount(id);
    }

    public String insertAccount(String name,String password,AccountType type)
    		throws RemoteException{
        return accountDao.insertAccount(name,password,type);
    }

    public ResultMessage updateAccount(AccountVo vo) throws RemoteException{
        return accountDao.updateAccount(vo);
    }

    public ResultMessage deleteAccount(String id) throws RemoteException{
        return accountDao.deleteAccount(id);
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
    
}