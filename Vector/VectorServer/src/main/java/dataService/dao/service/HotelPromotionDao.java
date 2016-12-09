package dataService.dao.service;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import common.ResultMessage;
import po.ActivityPromotionPo;
import po.BirthdayProPo;
import po.CompanyProPo;
import po.RoomPromotionPo;
/**
 * @version 2016-12-09
 * @author 金灵益
 *
 */
public interface HotelPromotionDao extends Remote{
	
	/**
	 * 更新活动策略
	 * @param hotelId
	 * @param po
	 * @return
	 */
	public ResultMessage upActPromotion(String hotelId, ActivityPromotionPo po) throws RemoteException;
	
	/**
	 * 删除一个活动策略
	 * @param hotelId
	 * @param po
	 * @return
	 * @throws RemoteException
	 */
	public ResultMessage delActPromotion(String hotelId, ActivityPromotionPo po) throws RemoteException;
	
	/**
	 * 得到活动促销列表
	 * @param hotelId
	 * @return
	 */
	public List<String> getActProList(String hotelId) throws RemoteException;

	/**
	 * 更新客户生日优惠策略
	 * @param hotelId
	 * @param po
	 * @return
	 */
	public ResultMessage upBirthPromotion(String hotelId, BirthdayProPo po) throws RemoteException;
	
	/**
	 * 得到客户生日优惠策略
	 * @param hotelId
	 * @return
	 */
	public BirthdayProPo getBirthPromotion(String hotelId) throws RemoteException;
	
	/**
	 * 更新合作企业优惠策略
	 * @param hotelId
	 * @param po
	 * @return
	 */
	public ResultMessage updateCooperPro(String hotelId, CompanyProPo po) throws RemoteException;
	
	/**
	 * 得到合作企业优惠策略
	 * @param hotelId
	 * @return
	 */
	public CompanyProPo getCooperPro(String hotelId) throws RemoteException;
	
	/**
	 * 更新预订房间数量优惠策略
	 * @param hotelId
	 * @param po
	 * @return
	 */
	public ResultMessage upRoomPromotion(String hotelId, RoomPromotionPo po) throws RemoteException;
	
	/**
	 * 得到预定房间数量优惠策略
	 * @param hotelId
	 * @return
	 */
	public RoomPromotionPo getRoomPromotion(String hotelId) throws RemoteException;
}
