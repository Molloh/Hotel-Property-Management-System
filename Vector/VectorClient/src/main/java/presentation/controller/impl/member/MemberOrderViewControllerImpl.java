package presentation.controller.impl.member;

import businessLogic.impl.OrderBlServiceImpl;
import businessLogic.service.OrderBlService;
import presentation.controller.service.member.MemberOrderViewControllerService;
import vo.OrderVo;

import java.util.List;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description
 */
public class MemberOrderViewControllerImpl implements MemberOrderViewControllerService {
    private String memberId;
    private static MemberOrderViewControllerService INSTANCE = new MemberOrderViewControllerImpl();
    private OrderBlService order;

    private MemberOrderViewControllerImpl() {
        order = OrderBlServiceImpl.getInstance();
    }

    public static MemberOrderViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public List<OrderVo> getAllOrders() {
        return order.getAllOrdersByMember(memberId);
    }

    @Override
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

}
