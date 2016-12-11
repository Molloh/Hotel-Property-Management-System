package presentation.controller.impl.hotel;

import presentation.controller.service.hotel.HotelInfoViewControllerService;
import presentation.view.hotel.HotelInfoView;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class HotelInfoViewControllerImpl implements HotelInfoViewControllerService {
    private static HotelInfoViewControllerService INSTANCE = new HotelInfoViewControllerImpl();
    private String hotelId;

    private HotelInfoViewControllerImpl(){}

    public static HotelInfoViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }

    @Override
    public String getHotelName() {
        return null;
    }

    @Override
    public String getHotelAddress() {
        return null;
    }

    @Override
    public String getHotelDiscription() {
        return null;
    }

    @Override
    public String getHotelPoint() {
        return null;
    }

    @Override
    public String getHotelStar() {
        return null;
    }

    @Override
    public String getHotelPhone() {
        return null;
    }

    @Override
    public void setHotelName(String hotelName) {

    }

    @Override
    public void setHotelAddress(String hotelAddress) {

    }

    @Override
    public void setHotelDiscription(String hotelDiscription) {

    }

    @Override
    public void setHotelPhone(String hotelPhone) {

    }

    @Override
    public void setHotelStar(String hotelStar) {

    }
}
