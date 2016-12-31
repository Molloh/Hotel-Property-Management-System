package presentation.controller.impl.hotel;

import businessLogic.impl.HotelPromotionBlServiceImpl;
import businessLogic.service.HotelBlService;
import businessLogic.service.HotelPromotionBlService;
import common.ResultMessage;
import presentation.common.SingletonItem;
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
    private static HotelPromotionViewControllerService INSTANCE = new HotelPromotionViewControllerImpl();

    private HotelPromotionBlService promotion;

    private HotelPromotionViewControllerImpl() {
        hotelId = SingletonItem.getInstance().getHotelId();
        promotion = HotelPromotionBlServiceImpl.getInstance();
    }

    public static HotelPromotionViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<ActivityPromotionVo> getCurrentActStrategy() {
        return promotion.getCurrentActStrategy(hotelId);
    }

    @Override
    public ResultMessage delActivityStrategy(ActivityPromotionVo vo) {
        return promotion.delActivityStrategy(hotelId, vo);
    }
}
