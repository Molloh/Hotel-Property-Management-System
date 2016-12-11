package presentation.controller.impl.marketer;

import presentation.controller.service.marketer.MarketerPromotionViewControllerService;
import vo.ActivityPromotionVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class MarketerPromotionViewControllerImpl implements MarketerPromotionViewControllerService {
    private String hotelId;

    public MarketerPromotionViewControllerImpl(String hotelId) {
        this.hotelId = hotelId;
    }


    @Override
    public List<ActivityPromotionVo> getCurrentActStrategy() {
        return null;
    }
}
