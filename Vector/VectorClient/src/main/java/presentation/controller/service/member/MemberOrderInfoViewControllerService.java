package presentation.controller.service.member;

import common.OrderCondition;
import common.RoomType;

import java.util.Date;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface MemberOrderInfoViewControllerService {

    String getOrderCondition(String orderId);

    String getMemberId(String orderId);

    String getMemberName(String orderId);

    Date getCreateTime(String orderId);

    Date getCheckInTime(String orderId);

    Date getCheckOutTime(String orderId);

    String getHotelName(String orderId);

    String getHotelId(String orderId);

    RoomType getRoomType(String orderId);

    int getNumOfRoom(String orderId);

    int getNumOfGuest(String orderId);

    boolean getChildExist(String orderId);

    int getOriginalPrice(String orderId);

    double getDiscount(String orderId);

    double getDiscountedPrice(String orderId);
}
