package businessLogic.impl;

import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import businessLogic.service.HotelBlService;
import common.ResultMessage;
import common.RoomType;
import dataService.dao.service.HotelDao;
import rmi.RemoteHelper;
import vo.HotelRoomVo;
import vo.HotelVo;

public class HotelBlServiceImpl implements HotelBlService{

	private HotelDao hotelDao;
	private static HotelBlServiceImpl hotelBlServiceImpl;
	private String hotelId;
	private HotelVo vo;
	
    public static HotelBlServiceImpl getInstance(String hotelId){
        if( hotelBlServiceImpl == null)
        	hotelBlServiceImpl = new HotelBlServiceImpl(hotelId) ;
        return hotelBlServiceImpl;
    }

    private HotelBlServiceImpl(String hotelId){
        hotelDao = RemoteHelper.getInstance().getHotelDao();
        this.hotelId = hotelId;
        try {
			vo = new HotelPoToVo().poTovo(hotelDao.findHotel(hotelId));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
    }

	public ResultMessage showhotelInfo() {
		return null;
	}

	public int getReadyRoom() {
		Map<String,HotelRoomVo> roomList = vo.getRoom();
		Iterator<Map.Entry<String,HotelRoomVo>> iterator = roomList.entrySet().iterator();

		int readyRoom = 0;
		while(iterator.hasNext()){
			Map.Entry<String, HotelRoomVo> entry = iterator.next();
			HotelRoomVo roomVo = entry.getValue();
			if(roomVo.getisEmpty()){
				readyRoom ++;
			}
		}
		return readyRoom;
	}

	public void comment(String giveComment) {
		vo.giveComment(giveComment);
	}

	public List<String> getComment() {
		return vo.getCommentList();
	}


	public void givePoStrings(double poStrings) {
		vo.givePoStrings(poStrings);
	}


	public double getPoStrings() {
		return vo.getPoStrings();
	}

	public String getInBusiness() {
		return vo.getInBusiness();
	}


	public int getOriginPrice(RoomType Type) {
		return 0;
	}

}
