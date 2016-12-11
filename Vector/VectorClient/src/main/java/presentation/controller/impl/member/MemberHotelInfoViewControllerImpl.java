package presentation.controller.impl.member;

import businessLogic.impl.HotelBlServiceImpl;
import common.RoomType;
import presentation.controller.service.member.MemberHotelInfoViewControllerService;
import vo.HotelVo;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class MemberHotelInfoViewControllerImpl implements MemberHotelInfoViewControllerService {
    private static MemberHotelInfoViewControllerService INSTANCE = new MemberHotelInfoViewControllerImpl();

    private String hotelId;
    private HotelVo hotelVo;

    private MemberHotelInfoViewControllerImpl() {
    }

    public static MemberHotelInfoViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
        hotelVo = HotelBlServiceImpl.getInstance().getHotelVo(hotelId);
    }

    @Override
    public String getHotelName() {
        return hotelVo.getHotelName();
    }

    @Override
    public String getHotelStar() {
        return String.valueOf(hotelVo.getStars());
    }

    @Override
    public String getHotelPoint() {
        return String.valueOf(hotelVo.getNumOfpoint());
    }

    @Override
    public String getHotelDiscription() {
        return "aaa";
    }

    @Override
    public String getHotelRoomPrice(RoomType T) {
        return String.valueOf(hotelVo.getOriginPrice(T));
    }

    @Override
    public String getComment() {
        return "aaaa";
    }
}
