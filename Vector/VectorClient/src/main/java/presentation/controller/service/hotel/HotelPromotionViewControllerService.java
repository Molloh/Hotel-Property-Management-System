package presentation.controller.service.hotel;

import common.ResultMessage;
import vo.ActivityPromotionVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface HotelPromotionViewControllerService {

    List<ActivityPromotionVo> getCurrentActStrategy();

    ResultMessage delActivityStrategy(ActivityPromotionVo vo);

}
