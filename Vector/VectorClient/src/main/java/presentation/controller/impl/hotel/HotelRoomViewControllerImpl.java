package presentation.controller.impl.hotel;

import common.RoomType;
import presentation.controller.service.hotel.HotelRoomViewControllerService;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class HotelRoomViewControllerImpl implements HotelRoomViewControllerService {
    private String hotelId;

    public HotelRoomViewControllerImpl(String hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public void initializeRoom(String hotelId, RoomType type, int number, int price) {

    }
}
