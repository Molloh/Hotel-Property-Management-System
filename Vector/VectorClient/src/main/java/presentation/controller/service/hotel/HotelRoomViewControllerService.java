package presentation.controller.service.hotel;

import common.RoomType;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface HotelRoomViewControllerService {

    void initializeRoom(String hotelId, RoomType type, int number, int price);
}
