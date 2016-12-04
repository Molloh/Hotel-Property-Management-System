package businessLogic.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import po.HotelPo;
import po.HotelRoom;
import vo.HotelRoomVo;
import vo.HotelVo;

public class HotelPoToVo {
	public HotelVo poTovo(HotelPo po){
		HotelVo vo = new HotelVo();
		vo.setId(po.getId());
		vo.setHotelAddress(po.getHotelAddress());
		vo.setCommentList(po.getCommentList());
		vo.setHotelInfo(po.getHotelInfo());
		vo.setHotelName(po.getHotelName());
		vo.setHotelTel(po.getHotelTel());
		vo.setInBusiness(po.getInBusiness());
		vo.setNumOfpoint(po.getNumOfpoint());
		vo.setOriginPrice(po.getOriginPrice());
		vo.setPoints(po.getPoStrings());
		vo.setStars(po.getStars());
		
		Map<String, HotelRoom> roomListPo = po.getRoom();
		Map<String, HotelRoomVo> roomListVo = new HashMap<String, HotelRoomVo>();
		
		Iterator<Map.Entry<String, HotelRoom>> iterator = roomListPo.entrySet().iterator(); 
		while(iterator.hasNext()){
			Map.Entry<String, HotelRoom> entry = iterator.next();
			HotelRoom roomPo = entry.getValue();
			HotelRoomVo roomVo = new HotelRoomVo(roomPo.getHotelId());
			
			roomVo.setCheckinDate(roomPo.getCheckInDate());
			roomVo.setCheckoutDate(roomPo.getCheckOutdate());
			roomVo.setisEmpty(roomPo.getisEmpty());
			roomVo.setOriginalPrice(roomPo.getPrice());
			roomVo.setRoomID(roomPo.getRoomID());
			roomVo.setRoomType(roomPo.getRoomtype());
			
			roomListVo.put(roomVo.getRoomID(), roomVo);
		}
		
		vo.setRooms(roomListVo);
		return vo;
	}
}
