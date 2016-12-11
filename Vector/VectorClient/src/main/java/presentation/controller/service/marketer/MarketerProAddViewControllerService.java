package presentation.controller.service.marketer;

import common.HotelPromotionType;

import java.time.LocalDate;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface MarketerProAddViewControllerService {

    void setPromotionType(HotelPromotionType type);

    void setPromotionName(String name);

    void setStartDate(LocalDate date);

    void setEndDate(LocalDate date);

    void setDiscount(String dis);

}
