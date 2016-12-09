package businessLogic.service;

import java.util.List;

import common.RoomType;
import vo.HotelVo;

/**
 * @version 2016-12-04
 * @author 金灵益 
 * @description 酒店搜索，排序所需的服务
 */
public interface HotelListService {

	/**
	 * @version 2016-12-01
	 * @param list
	 * @param isRise true为升序
	 * @return 按酒店名称排序
	 */
	public List<HotelVo> sortByName(List<HotelVo> list, boolean isRise);
	
	/**
	 * @version 2016-12-01
	 * @param list
	 * @param isRise true为升序
	 * @return 按星级排序
	 */
	public List<HotelVo> sortByStar(List<HotelVo> list, boolean isRise);
	
	/**
	 * @version 2016-12-01
	 * @param list
	 * @param isRise true为升序
	 * @return 按评分排序
	 */
	public List<HotelVo> sortByPoint(List<HotelVo> list, boolean isRise);
	
	/**
	 * @version 2016-12-01
	 * @param list
	 * @param isRise true为升序
	 * @return 按价格排序
	 */
	public List<HotelVo> sortByPrice(List<HotelVo> list, RoomType type, boolean isRise);
	
	/**
	 * @version 2016-12-01
     * @author 金灵益 
	 * @param key
	 * @description 酒店的输入关键字查找
	 * @return
	 */
	public List<HotelVo> findByKeyword(String key);
	
	/**
	 * @version 2016-12-03
	 * @param province
	 * @param city
	 * @param business
	 * @return 必须输入省份、城市、商圈三个条件才能得到搜索结果
	 */
	public List<HotelVo> findByAddress(String province, String city, String business);
	
	/**
	 * @version 2016-12-09
	 * @param type
	 * @param low
	 * @param high
	 * @return 按照原始价格搜索
	 */
	public List<HotelVo> findByOriginalPrice(RoomType type, int low, int high, List<HotelVo> list);
	
	/**
	 * @version 2016-12-03
	 * @param least
	 * @param max
	 * @param list
	 * @return 通过评分区间搜索酒店
	 */
	public List<HotelVo> findByPoint(double least, double max, List<HotelVo> list);
	
	/**
	 * @version 2016-12-03
	 * @param least
	 * @param max
	 * @param list
	 * @return 通过星级区间搜索酒店
	 */
	public List<HotelVo> findByStars(int least, int max, List<HotelVo> list);
	
	/**
	 * @version 2016-12-03
	 * @param type
	 * @param list
	 * @return 通过房间类型返回符合条件的酒店
	 */
	public List<HotelVo> findByRoomType(RoomType type, List<HotelVo> list);
	
	/**
	 * @version 2016-12-04
	 * @return 所有有酒店的省份
	 */
	public List<String> getProvinceList();
	
	/**
	 * @version 2016-12-04
	 * 必须先确定省份
	 * @return 该省份下拥有酒店的所有城市
	 */
	public List<String> getCityList(String province);
	
	/**
	 * @version 2016-12-04
	 * @return 必须先得到城市地址，返回该城市所有商圈
	 */
	public List<String> getBusinessList(String city);
}