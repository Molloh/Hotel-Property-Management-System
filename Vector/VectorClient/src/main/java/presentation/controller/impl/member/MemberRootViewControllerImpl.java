package presentation.controller.impl.member;

import businessLogic.impl.MemberBlServiceImpl;
import businessLogic.service.MemberBlService;
import presentation.controller.service.member.MemberRootViewControllerService;
import vo.MemberVo;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MemberRootViewControllerImpl implements MemberRootViewControllerService {
    private static final MemberRootViewControllerService INSTANCE = new MemberRootViewControllerImpl();

    private MemberBlService member;

    private String memberId;

    private MemberRootViewControllerImpl() {
        member = MemberBlServiceImpl.getInstance();
    }

    public static MemberRootViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String getMemberName() {
        MemberVo memberVo = member.getInfo(memberId);
        return memberVo.getName();
        //return "Molloh";
    }
}
