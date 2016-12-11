package presentation.controller.service.member;

import common.RoomType;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface MemberHotelInfoViewControllerService {

    void setHotelId(String hotelId);

    String getHotelName();

    String getHotelStar();

    String getHotelPoint();

    String getHotelDiscription();

    String getHotelRoomPrice(RoomType T);

    String getComment();

}
