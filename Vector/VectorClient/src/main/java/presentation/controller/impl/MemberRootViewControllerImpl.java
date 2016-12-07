package presentation.controller.impl;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.service.AccountBlService;
import presentation.controller.service.MemberRootViewControllerService;

/**
 * @author Molloh
 * @version 2016/12/7
 * @description
 */
public class MemberRootViewControllerImpl implements MemberRootViewControllerService {
    private static MemberRootViewControllerService memberRootViewController;
    private AccountBlService accountBlService;

    private String memberId;

    private MemberRootViewControllerImpl() {
        accountBlService = AccountBlServiceImpl.getInstance();
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
        accountBlService.logout(memberId);
    }

    @Override
    public String getMemberName() {
        return null;
    }
}
