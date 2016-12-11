package presentation.controller.impl.member;

import common.RoomType;
import presentation.controller.service.member.MemberOrderInfoViewControllerService;
import presentation.controller.service.member.MemberOrderViewControllerService;

import java.util.Date;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class MemberOrderInfoViewControllerImpl implements MemberOrderInfoViewControllerService {
    @Override
    public String getOrderCondition(String orderId) {
        return null;
    }

    @Override
    public String getMemberId(String orderId) {
        return null;
    }

    @Override
    public String getMemberName(String orderId) {
        return null;
    }

    @Override
    public Date getCreateTime(String orderId) {
        return null;
    }

    @Override
    public Date getCheckInTime(String orderId) {
        return null;
    }

    @Override
    public Date getCheckOutTime(String orderId) {
        return null;
    }

    @Override
    public String getHotelName(String orderId) {
        return null;
    }

    @Override
    public String getHotelId(String orderId) {
        return null;
    }

    @Override
    public RoomType getRoomType(String orderId) {
        return null;
    }

    @Override
    public int getNumOfRoom(String orderId) {
        return 0;
    }

    @Override
    public int getNumOfGuest(String orderId) {
        return 0;
    }

    @Override
    public boolean getChildExist(String orderId) {
        return false;
    }

    @Override
    public int getOriginalPrice(String orderId) {
        return 0;
    }

    @Override
    public double getDiscount(String orderId) {
        return 0;
    }

    @Override
    public double getDiscountedPrice(String orderId) {
        return 0;
    }
}
