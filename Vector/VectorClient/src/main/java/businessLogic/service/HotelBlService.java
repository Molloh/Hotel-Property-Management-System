/**
* @version 2016-12-01
* @author 金灵益
* @description 负责酒店管理界面所需要的服务
*/
package businessLogic.service;
import common.RoomType;
import vo.HotelVo;


/**
* 酒店的属性有：
* 编号，名称，所属商圈，简介，地址，联系方式，星级
* 总房间数，评分，空余房间数，房间类型，价格
* 酒店的查询可以通过选择酒店分类、输入关键字进行模糊查找
* @author 金灵益
* @version 2016-11-20
*
*/
public interface HotelBlService {

	/**
	 * 增加酒店，当创建好酒店ID时调用方法初始化酒店
	 * @param hotelId
	 */
	public void addHotel(String hotelId);
	
	/**
	 * 当酒店基本信息变化时，只更新酒店列表
	 * @param vo
	 */
	public void updateBasicInfo(HotelVo vo);
	
	/**
	 * 删除酒店 
	 * @param hotelId
	 */
	public void deleteHotel(String hotelId);
	
	/**
	 * 酒店工作人员录入客房信息
	 * @param hotelId
	 * @param type
	 * @param number
	 * @param price
	 */
	public void initializeRoom(String hotelId, RoomType type, int number, int price);
	
	/**
	 * 当酒店人员执行退房时，调用此方法
	 * @param orderId
	 * @param type
	 */
	public void checkoutRoom(String orderId, RoomType type);
	
	/**
	 * 当客户预定房间，酒店工作人员执行订单或延迟订单，调用此方法
	 * @param orderId
	 * @param type
	 * @param startDate
	 * @param endDate
	 */
	public void updateBookRoom(String orderId, RoomType type, String startDate, String endDate);
	
	/**
	 * 根据预定日期返回该时间段内该类型房间的空余数量
	 * @param type
	 * @param date
	 * @return
	 */
	public int getReadyRoom(RoomType type, String startDate, String endDate);

	/**
	 * @description 客户给予文字评价
	 * @param giveComment
	 */
	public void comment(String giveComment);

	/**
	 * @description 客户给予评分
	 * @param poStrings
	 */
	public void givePoStrings(double poStrings);

}