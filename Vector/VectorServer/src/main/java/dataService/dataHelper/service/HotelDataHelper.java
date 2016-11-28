package dataService.dataHelper.service;


import java.util.List;
import java.util.Map;

import common.ResultMessage;
import po.HotelPo;
import po.HotelRoom;

/**
 * @ author 金灵益
 * @ version 2016/11/20
 * @ description
 */
public interface HotelDataHelper {
	/**
	 * @return	从数据文件,hotelList中读取酒店数据
	 */
	public Map<String,HotelPo> getHotelData();
	
	/**
	 * 向hoteLlist写入酒店数据,初始化酒店房间、评论文件
	 * @param po
	 */
	public ResultMessage addHotelData(HotelPo po);
	
	/**
	 * 向数据文件中hotelList写入酒店数据
	 * @param list	
	 */
	public ResultMessage updateHotelListData(Map<String, HotelPo> map);
	
	/**
	 * 从数据文件中删除数据
	 * @param hotelId
	 */
	public ResultMessage deleteHotelData(String hotelId);
	
	/**
	 * 
	 * @param hotelId
	 * @return 酒店房间信息
	 */
	public Map<String, HotelRoom> getHotelRoom(String hotelId);
	
	/**
	 * 
	 * @param hotelId
	 * @return 酒店的文字评论
	 */
	public List<String> getComments(String hotelId);
	
	/**
	 * 更新酒店房间数据
	 * @param hotelId
	 * @param map
	 */
	public void updateHotelRoom(String hotelId,Map<String,HotelRoom> map);
	
	/**
	 * 更新酒店文字评论
	 * @param hotelId
	 * @param comment
	 */
	public void updateComments(String hotelId,List<String> commentList);
}