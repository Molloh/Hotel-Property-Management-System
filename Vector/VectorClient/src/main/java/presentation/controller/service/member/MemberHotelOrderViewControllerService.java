package presentation.controller.service.member;

import common.ResultMessage;
import common.RoomType;

/**
 * @author Molloh
 * @version 2016/12/25
 * @description
 */
public interface MemberHotelOrderViewControllerService {
    ResultMessage submit(String memberId, String planCheckInTimeStr, String hoteId, int numOfDays,
                         RoomType roomType, int numOfRoom, int numOfGuest, boolean childExist);
}
