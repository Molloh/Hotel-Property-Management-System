package presentation.controller.impl.hotel;

import presentation.controller.service.hotel.HotelInfoViewControllerService;
import presentation.controller.service.hotel.HotelPromotionViewControllerService;
import presentation.view.hotel.HotelPromotionView;
import vo.ActivityPromotionVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class HotelPromotionViewControllerImpl implements HotelPromotionViewControllerService {
    private String hotelId;

    public HotelPromotionViewControllerImpl(String hotelId) {
        this.hotelId = hotelId;
    }


    @Override
    public List<ActivityPromotionVo> getCurrentActStrategy() {
        return null;
    }
}
