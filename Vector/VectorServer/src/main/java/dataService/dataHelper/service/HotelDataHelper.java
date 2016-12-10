package dataService.dataHelper.service;


import java.util.List;
import java.util.Map;

import common.ResultMessage;
import common.RoomType;
import po.HotelPo;

/**
 * @author 金灵益
 * @version 2016/12/03
 * @description
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
	 * @return 酒店的文字评论
	 */
	public List<String> getComments(String hotelId);
	
	/**
	 * 更新酒店文字评论
	 * @param hotelId
	 * @param comment
	 */
	public void updateComments(String hotelId,List<String> commentList);
	
	/**
	 * 初始化酒店房间信息，存储酒店的房间类型，数量，原始价格。不具体到单个房间
	 * @param hotelId
	 * @param type
	 * @param number
	 * @param price
	 */
	public void initRoom(String hotelId, RoomType type, int number, int price);
	
	/**
	 * 在搜索、执行订单时可用于确认该类型房间在预定期间内是否有空
	 * @param hotelId
	 * @param type
	 * @return
	 */
	public List<String> getBookDateList(String hotelId, RoomType type);
	
	/**
	 * 更新酒店每种类型的房间的预定日期和结束日期，不具体到单个房间
	 * 在客户确认该类型房间的预定期间时，更新文件
	 * 在酒店工作人员退房时，更新文件
	 * @param hotelId
	 * @param type
	 * @param list
	 */
	public void upBookDateList(String hotelId, RoomType type, List<String> list);
	
	/**
	 * @return 所有省份列表
	 */
	public List<String> getProvinceList();
	
	/**
	 * 根据省份返回其市级地区
	 * @param province
	 * @return
	 */
	public List<String> getCityList(String province);
	
	/**
	 * 根据省市返回所在地的商圈
	 * @param province
	 * @param city
	 * @return
	 */
	public List<String> getBusinessList(String province, String city);
}