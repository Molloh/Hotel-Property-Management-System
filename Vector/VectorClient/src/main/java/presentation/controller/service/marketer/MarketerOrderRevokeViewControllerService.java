package presentation.controller.service.marketer;

import common.RoomType;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description
 */
public interface MarketerOrderRevokeViewControllerService {

    void setOrderId(String orderId);

    String getOrderCondition();

    String getMemberId();

    String getMemberName();

    String getCreateTime();

    String getCheckInTime();

    String getCheckOutTime();

    String getHotelName();

    String getHotelId();

    RoomType getRoomType();

    int getNumOfRoom();

    int getNumOfGuest();

    boolean getChildExist();

    int getOriginalPrice();

    double getDiscount();

    double getDiscountedPrice();
}
