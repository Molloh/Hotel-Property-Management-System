package businessLogic.impl;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import businessLogic.service.HotelBlService;
import common.ResultMessage;
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
		if(hotelDao == null)
			hotelDao = RemoteHelper.getInstance().getHotelDao();
	}
	
	
	@Override
	public HotelVo getHotelVo(String hotelId){
		try {
			this.vo = new HotelVo(hotelDao.findHotel(hotelId));
			return vo;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
    @Override
    public ResultMessage addHotel(String hotelId){
    	HotelPo po = new HotelPo(hotelId);    	
    	
    	try {
			return hotelDao.addHotelPO(po);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return ResultMessage.FAIL;
    }
    
    
    @Override
    public ResultMessage updateBasicInfo(HotelVo vo){
    	try {
			return hotelDao.updateHotelList(voTopo(vo));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return ResultMessage.FAIL;
    }
    
    
    @Override
    public ResultMessage deleteHotel(String hotelId){
    	try {
			return hotelDao.deleteHotelPO(hotelId);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return ResultMessage.FAIL;
    }
    
    
    @Override
    public ResultMessage initializeRoom(String hotelId, RoomType type, int number, int price){
    	try {
			return hotelDao.initHotelTypeRoom(hotelId, type, number, price);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	return ResultMessage.FAIL;
    }

    
    @Override
    public ResultMessage checkoutRoom(RoomType type, int number){
		try {
			return hotelDao.updateOrderedRoom(vo.getId(), type, number, false);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
    }
    
    
    @Override
    public ResultMessage bookRoom(RoomType type, int number){
    	try {
    		//订房数量不超过剩余房间数
    		if(number <= getReadyRoom(type))
			 return hotelDao.updateOrderedRoom(vo.getId(), type, number, true);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    	//房间数量不够
    	return ResultMessage.FAIL;
    }
    
    
    @Override
	public int getReadyRoom(RoomType type) {
		try {
			return hotelDao.getReadyRoom(vo.getId(), type);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return 0;
	}
    

    @Override
	public ResultMessage comment(String giveComment) {
		List<String> comment = vo.getCommentList();
		//酒店评论为空的情况
		if(comment == null){
			comment = new ArrayList<String>();
		}
		comment.add(giveComment);
		vo.setCommentList(comment);
		try {
			return hotelDao.updateComment(voTopo(vo));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}

    
	@Override
	public ResultMessage givePoStrings(String orderId, double poStrings) {
		//计算评分
		double points = vo.getPoStrings();
		int num = vo.getNumOfpoint();
		points = (points * num + poStrings) / (num+1);
		
		//保留1位小数
		DecimalFormat df = new DecimalFormat("#.0");
		points = Double.parseDouble(df.format(points));
		vo.setNumOfPoint(num + 1);                  //评分人数发生变化
		vo.setPoStrings(points);
		ResultMessage r1 = OrderBlServiceImpl.getInstance().setToFinished(orderId);   //更新订单状态
		ResultMessage r2 = updateBasicInfo(vo);
		
		if(r1.equals(ResultMessage.SUCCEED) && r2.equals(ResultMessage.SUCCEED)) return ResultMessage.SUCCEED;
		else  return ResultMessage.FAIL;
	}

	
	/**
	 * HotelVo -> HotelPo
	 * @param vo
	 * @return
	 */
	private HotelPo voTopo(HotelVo vo){
		//HotelTypeRoomVo --> po
		
		List<HotelTypeRoomPo> typeRoomListPo = new ArrayList<HotelTypeRoomPo>();
		Iterator<HotelTypeRoomVo> it = vo.getTypeRoom().iterator();
		while(it.hasNext()){
			HotelTypeRoomVo htrv = it.next();
			HotelTypeRoomPo htrp = new HotelTypeRoomPo(htrv.getType(),htrv.getNumOfTypeRoom(),
					                                   htrv.getPrice());
			typeRoomListPo.add(htrp);
		}
		
		HotelPo po = new HotelPo(vo.getId(), vo.getHotelName(),vo.getProvince(),vo.getHotelCity(), vo.getHotelPosition(),
				vo.getInBusiness(), vo.getHotelTel(), vo.getStars(), vo.getPoStrings(), vo.getNumOfpoint(),
				vo.getHotelInfo(), vo.getCommentList(), typeRoomListPo);
		return po;
	}
}
