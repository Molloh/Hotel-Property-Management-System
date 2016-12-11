package presentation.controller.service.member;

import vo.OrderVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public interface MemberOrderViewControllerService {

    List<OrderVo> getAllOrders();

    void setMemberId(String memberId);
}
