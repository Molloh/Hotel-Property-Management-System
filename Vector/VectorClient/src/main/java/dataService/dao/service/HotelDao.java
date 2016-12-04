/**
 * @version 2016-12-04
 * @author 金灵益
 */
package dataService.dao.service;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import common.ResultMessage;
import common.RoomType;
import po.HotelPo;

public interface HotelDao extends Remote{

	/**
	 * 添加酒店
	 * @param po
	 * @return
	 */
	public ResultMessage addHotelPO(HotelPo po) throws RemoteException;
	
	/**
	 * 更新酒店列表信息
	 * @param po
	 * @return
	 */
	public ResultMessage updateHotelList(HotelPo po) throws RemoteException;
	
	/**
	 * 删除酒店
	 * @param hotelId
	 * @return
	 */
	public ResultMessage deleteHotelPO(String hotelId) throws RemoteException;
	
	/**
	 * 按ID查找返回酒店
	 * @param hotelId
	 * @return
	 */
	public HotelPo findHotel(String hotelId) throws RemoteException;
	
	/**
	 * 输入关键字返回所有符合条件的酒店
	 * @param key
	 * @return
	 */
	public List<HotelPo> keyFind(String key) throws RemoteException;

	/**
	 * 更新酒店房间信息,具体到每个房间
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage updateRoom(HotelPo po) throws RemoteException;

	/**
	 * 更新酒店文字评论
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage updateComment(HotelPo po) throws RemoteException;
	
	/**
	 * 初始化酒店房间信息，存储酒店的房间类型，数量，原始价格。不具体到单个房间
	 * @param hotelId
	 * @param type
	 * @param number
	 * @param price
	 */
	public ResultMessage initHotelTypeRoom(String hotelId, RoomType type, int number, int price) throws RemoteException;
	
	/**
	 * 更新酒店每种类型的房间的预定日期和结束日期，不具体到单个房间
	 * 在客户确认该类型房间的预定期间时，更新文件
	 * 在酒店工作人员退房时，更新文件
	 * @param po
	 * @param type
	 * @return
	 */
	public ResultMessage updateBookDate(HotelPo po, RoomType type) throws RemoteException;
}