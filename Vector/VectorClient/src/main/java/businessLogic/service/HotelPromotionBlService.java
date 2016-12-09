package businessLogic.service;

import java.util.Date;
import java.util.List;

import common.ResultMessage;
import common.RoomType;
import vo.ActivityPromotionVo;
import vo.BirthdayProVo;
import vo.CompanyProVo;
import vo.MemberVo;
import vo.RoomPromotionVo;

/**
 * @version 2016-12-09
 * @author 金灵益
 * @description 酒店促销策略相关接口
 */
public interface HotelPromotionBlService {

	/**
	 * 增加一条活动策略
	 * @param hotelId
	 * @param vo
	 * @return
	 */
	public ResultMessage addActivityStrategy(String hotelId, ActivityPromotionVo vo);
	
	/**
	 * 更新一条活动策略
	 * @param hotelId
	 * @param vo
	 * @return
	 */
	public ResultMessage upActivityStrategy(String hotelId, ActivityPromotionVo vo);
	
	/**
	 * 删除一条活动策略
	 * @param hotelId
	 * @param vo
	 * @return
	 */
	public ResultMessage delActivityStrategy(String hotelId, ActivityPromotionVo vo);
	
	/**
	 * 得到当前的有效活动策略
	 * @param hotelId
	 * @return
	 */
	public List<ActivityPromotionVo> getCurrentActStrategy(String hotelId);
	
	/**
	 * 更新合作企业促销策略
	 * @param hotelId
	 * @param vo
	 * @return
	 */
	public ResultMessage updateCooperationStrategy(String hotelId, CompanyProVo vo);
	
	/**
	 * 若该企业为酒店合作企业，则得到相应关于合作企业促销策略
	 * @param hotelId
	 * @param memberId    企业账号
	 * @return
	 */
	public CompanyProVo getCooperationStrategy(String hotelId, String memberId);

	/**
	 * 更新房间预订促销策略
	 * @param hotelId
	 * @param vo
	 * @return
	 */
	public ResultMessage updateOrderRoomStrategy(String hotelId, RoomPromotionVo vo);
	
	
	/**
	 * 当在该酒店一次订单订房间数量满足条件，得到该酒店房间预订促销策略
	 * @param hotelId
	 * @param numOfRoom
	 * @return
	 */
	public RoomPromotionVo getOrderRoomStrategy(String hotelId, int numOfRoom);
	
	/**
	 * 当前为客户生日时，得到生日优惠策略
	 * @param hotelId
	 * @param birthDay
	 * @return
	 */
	public BirthdayProVo getBirthStrategy(String hotelId, Date birthDay);
	
	/**
	 * 更新该酒店客户生日促销策略
	 * @param hotelId
	 * @param vo
	 * @return
	 */
	public ResultMessage upBirthStrategy(String hotelId, BirthdayProVo vo);
	
	/**
	 * 根据促销策略和客户类型计算最优价格
	 * @param hotelId
	 * @param type  房间类型
	 * @param vo    客户的vo
	 * @return
	 */
	public int getStrategyPrice(String hotelId, RoomType type, MemberVo vo);
}
