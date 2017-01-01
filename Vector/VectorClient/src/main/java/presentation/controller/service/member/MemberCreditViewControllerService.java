package presentation.controller.service.member;

import common.ResultMessage;
import vo.CreditRecordVo;
import vo.OrderVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/31
 * @description
 */
public interface MemberCreditViewControllerService {

    List<CreditRecordVo> getCreditRecordList(String id);

}
