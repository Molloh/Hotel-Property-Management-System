package presentation.controller.service.marketer;

import java.time.LocalDate;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface MarketerProInfoViewControllerService {

    String getPromotionType();

    String getPromotionName();

    LocalDate getStartDate();

    LocalDate getEndDate();

    String getDiscount();

    void setPromotionName(String name);

    void setStartDate(LocalDate date);

    void setEndDate(LocalDate date);

    void setDiscount(String dis);

    void delete();

}
