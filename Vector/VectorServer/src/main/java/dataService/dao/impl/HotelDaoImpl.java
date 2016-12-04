package dataService.dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.ResultMessage;
import dataService.dao.service.HotelDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.HotelDataHelper;
import po.HotelPo;

public class HotelDaoImpl extends UnicastRemoteObject implements HotelDao{
	private static final long serialVersionUID = 1L;
	
	private Map<String, HotelPo> map;
	
	private HotelDataHelper hotelDataHelper;
	
	private DataFactory dataFactory;
	
	private static HotelDaoImpl hotelDataServiceImpl;
	
	public static HotelDaoImpl getInstance() throws RemoteException{
		if(hotelDataServiceImpl == null){
			hotelDataServiceImpl = new HotelDaoImpl();
		}
		
		return hotelDataServiceImpl;
	}
	
	public HotelDaoImpl() throws RemoteException{
		super();
		if(map == null){
			dataFactory = new DataFactoryImpl();
			hotelDataHelper = dataFactory.getHotelDataHelper();
			map = hotelDataHelper.getHotelData();
		}
	}

	@Override
	public ResultMessage addHotelPO(HotelPo po) throws RemoteException{
		if(!map.containsKey(po.getId())) {	    
			map.put(po.getId(), po);	   
			hotelDataHelper.updateHotelListData(map);
			return ResultMessage.SUCCEED;   
		}
		//若已存在该po
		return ResultMessage.FAIL;	
	}

	@Override
	public ResultMessage updateHotelList(HotelPo po) throws RemoteException{
		String hotelId = po.getId();
		if(map.get(hotelId) != null){
			hotelDataHelper.updateHotelListData(map);
			return ResultMessage.SUCCEED;
		}
		return ResultMessage.FAIL;
	}

	@Override
	public ResultMessage updateRoom(HotelPo po) throws RemoteException{
		String hotelId = po.getId();
		if(map.get(hotelId) != null){
			hotelDataHelper.updateHotelRoom(hotelId, po.getRoom());
			return ResultMessage.SUCCEED;
		}
		return ResultMessage.FAIL;
	}
	
	@Override
	public ResultMessage updateComment(HotelPo po) throws RemoteException{
		String hotelId = po.getId();
		if(map.get(hotelId) != null){
			hotelDataHelper.updateComments(hotelId, po.getCommentList());
			return ResultMessage.SUCCEED;
		}
		return ResultMessage.FAIL;
	}
	
	@Override
	public ResultMessage deleteHotelPO(String hotelId) throws RemoteException{
		return hotelDataHelper.deleteHotelData(hotelId);
	}

	@Override
	public HotelPo findHotel(String hotelId) throws RemoteException{
		Iterator<Map.Entry<String,HotelPo>> iterator = map.entrySet().iterator();

		while(iterator.hasNext()){
			Map.Entry<String, HotelPo> entry = iterator.next();
			HotelPo po = entry.getValue();
			if(po.getId().equals(hotelId)){
				return po;
			}
		}
		return null;
	}

	@Override
	public List<HotelPo> keyFind(String key) throws RemoteException{
		List<HotelPo> hotelList = new ArrayList<HotelPo>();
		Iterator<Map.Entry<String,HotelPo>> iterator = map.entrySet().iterator();
		
		while(iterator.hasNext()){
			Map.Entry<String, HotelPo> entry = iterator.next();
			HotelPo po = entry.getValue();
			if(po.showInfo().contains(key)){
				hotelList.add(po);
			}
		}
		return hotelList;
	}

	@Override
	public String getBusiness(String hotelId) throws RemoteException{
		HotelPo po = findHotel(hotelId);
		if(po != null){
			return po.getInBusiness();
		}
		return null;
	}
}