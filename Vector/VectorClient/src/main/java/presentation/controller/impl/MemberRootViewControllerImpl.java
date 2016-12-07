package presentation.controller.impl;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.impl.MemberBlServiceImpl;
import businessLogic.service.AccountBlService;
import businessLogic.service.MemberBlService;
import presentation.controller.service.MemberRootViewControllerService;
import vo.MemberVo;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MemberRootViewControllerImpl implements MemberRootViewControllerService {
    private static MemberRootViewControllerService memberRootViewController;
    private AccountBlService account;
    private MemberBlService member;

    private String memberId;

    private MemberRootViewControllerImpl() {
        account = AccountBlServiceImpl.getInstance();
        member = MemberBlServiceImpl.getInstance();
    }

    public static MemberRootViewControllerService getInstance() {
        if(memberRootViewController == null)
            memberRootViewController = new MemberRootViewControllerImpl();

        return memberRootViewController;
    }

    @Override
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public void signOut() {
        account.logout(memberId);
    }

    @Override
    public String getMemberName() {
        MemberVo memberVo = member.getInfo(memberId);
        return memberVo.getName();
    }
}
