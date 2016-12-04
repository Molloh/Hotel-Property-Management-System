package businessLogic.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.service.HotelBlService;
import common.RoomType;
import dataService.dao.service.HotelDao;
import po.HotelPo;
import po.HotelTypeRoomPo;
import rmi.RemoteHelper;
import vo.HotelTypeRoomVo;
import vo.HotelVo;

public class HotelBlServiceImpl implements HotelBlService{

	private HotelDao hotelDao;
	private static HotelBlServiceImpl hotelBlServiceImpl;
	private HotelVo vo;
	
	public static HotelBlServiceImpl getInstance(){
		if( hotelBlServiceImpl == null)
			hotelBlServiceImpl = new HotelBlServiceImpl() ;
		return hotelBlServiceImpl;	
	}

	private HotelBlServiceImpl(){
		hotelDao = RemoteHelper.getInstance().getHotelDao();
	}
	
    @Override
    public void addHotel(String hotelId){
    	HotelPo po = new HotelPo(hotelId);    	
    	try {
			hotelDao.addHotelPO(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void updateBasicInfo(HotelVo vo){
    	try {
			hotelDao.updateHotelList(voTopo(vo));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    @Override
    public void deleteHotel(String hotelId){
    	try {
			hotelDao.deleteHotelPO(hotelId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    public HotelVo getHotelVo(String hotelId){
    	try {
			HotelPo po = hotelDao.findHotel(hotelId);
			HotelVo vo = new HotelVo(po);
			this.vo = vo;
			return vo;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    @Override
    public void initializeRoom(String hotelId, RoomType type, int number, int price){
    	try {
			hotelDao.initHotelTypeRoom(hotelId, type, number, price);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 
     * @param type
     * @return 通过房间类型返回该类型的房间信息
     */
    private HotelTypeRoomVo getHotelTypeRoom(RoomType type){
    	List<HotelTypeRoomVo> typeRoomList = vo.getTypeRoom();
		Iterator<HotelTypeRoomVo> it = typeRoomList.iterator();
		//得到该类型房间的信息
		HotelTypeRoomVo htrv = null;
		while(it.hasNext()){
			htrv = it.next();
			if(htrv.getType().equals(type))
				break;
		}
		return htrv;
    }
    
    @Override
    public void checkoutRoom(String orderId, RoomType type){
    	HotelTypeRoomVo htrv = getHotelTypeRoom(type);

		List<String> list = htrv.getBookDate();
		Iterator<String> it = list.iterator();
		
		int i = 0;
		//若有相应的订单号，则表示当前状态为酒店人员执行退房
		while(it.hasNext()){
			String [] token = it.next().split("/");
			if(token[0].equals(orderId)){
				list.remove(i);
				break;
			}
			i ++;
		}
		
		//更新房间信息
		htrv.setBookDate(list);
		List<HotelTypeRoomVo> typeroomlist = vo.getTypeRoom();
		typeroomlist.set(typeroomlist.indexOf(getHotelTypeRoom(type)), htrv); //元素替换
				
		vo.setHotelTypeRoomList(typeroomlist);
		try {
			hotelDao.updateBookDate(voTopo(vo), type);
		} catch (RemoteException e) {
			e.printStackTrace();	
		}
		
    }
    
    @Override
    public void updateBookRoom(String orderId, RoomType type, String startDate, String endDate){
		HotelTypeRoomVo htrv = getHotelTypeRoom(type);
		String date = orderId + "/" + startDate + "/" + endDate;
		
		List<String> list = htrv.getBookDate();
		Iterator<String> it = list.iterator();
		int i = 0, flag = 0;
		//若有相应的订单号，则表示当前状态为酒店人员执行订单
		while(it.hasNext()){
			String [] token = it.next().split("/");
			if(token[0].equals(orderId)){
				list.set(i, date);
				flag = -1;
				break;
			}
			i ++;
		}
		//若没有相应的订单号，则表示客户是在预定状态
		if(flag == 0)
			list.add(date);
		
		//更新房间信息
		htrv.setBookDate(list);
		List<HotelTypeRoomVo> typeroomlist = vo.getTypeRoom();
		typeroomlist.set(typeroomlist.indexOf(getHotelTypeRoom(type)), htrv);
		
		vo.setHotelTypeRoomList(typeroomlist);
		try {
			hotelDao.updateBookDate(voTopo(vo), type);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }
    
    @Override
	public int getReadyRoom(RoomType type, String startDate, String endDate) {
		HotelTypeRoomVo htrv = getHotelTypeRoom(type);
		int readyRoom = htrv.getNumOfTypeRoom() - htrv.getBookDate().size();
		return readyRoom;
	}

	public void comment(String giveComment) {
		List<String> comment = vo.getCommentList();
		comment.add(giveComment);
		vo.setCommentList(comment);
		try {
			hotelDao.updateComment(voTopo(vo));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void givePoStrings(double poStrings) {
		double points = vo.getPoStrings();
		int num = vo.getNumOfpoint();
		points = (points * num + poStrings) / (num+1);
		vo.setNumOfPoint(num + 1);
		vo.setPoStrings(points);
		
		updateBasicInfo(vo);
	}

	private HotelPo voTopo(HotelVo vo){
		//HotelTypeRoomVo --> po
		
		List<HotelTypeRoomPo> typeRoomListPo = new ArrayList<HotelTypeRoomPo>();
		Iterator<HotelTypeRoomVo> it = vo.getTypeRoom().iterator();
		while(it.hasNext()){
			HotelTypeRoomVo htrv = it.next();
			HotelTypeRoomPo htrp = new HotelTypeRoomPo(htrv.getType(),htrv.getNumOfTypeRoom(),
					                                   htrv.getPrice(),htrv.getBookDate());
			typeRoomListPo.add(htrp);
		}
		
		HotelPo po = new HotelPo(vo.getId(), vo.getHotelName(),vo.getProvince(),vo.getHotelCity(), vo.getHotelPosition(),
				vo.getInBusiness(), vo.getHotelTel(), vo.getStars(), vo.getPoStrings(), vo.getNumOfpoint(),
				vo.getHotelInfo(), vo.getCommentList(), typeRoomListPo);
		return po;
	}
}
