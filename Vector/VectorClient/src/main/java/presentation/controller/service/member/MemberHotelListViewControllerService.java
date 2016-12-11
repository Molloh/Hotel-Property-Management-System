package presentation.controller.service.member;

import common.RoomType;
import vo.HotelVo;

import java.util.List;

/**
 * @ author Molloh
 * @ version 2016/11/6
 * @ description
 */
public interface MemberHotelListViewControllerService {

    List<HotelVo> findByKeyword(String key);

    List<HotelVo> findByAddress(String province, String city, String cbd);

    List<HotelVo> findByOriginalPrice(RoomType type, int low, int high, List<HotelVo> list);

    List<HotelVo> findByPoint(double least, double max, List<HotelVo> list);

    List<HotelVo> findByRoomType(RoomType type, List<HotelVo> list);

    List<HotelVo> findByStars(int num, List<HotelVo> list);

    List<String> getProvinceList();

    List<String> getCityList(String province);

    List<String> getCbdList(String province, String city);

}
