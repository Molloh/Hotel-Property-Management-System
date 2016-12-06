package presentation.controller.impl;

import presentation.controller.service.MemberMainViewControllerService;

/**
 * @author Molloh
 * @version 2016/11/27
 * @description
 */
public class MemberMainViewControllerImpl implements MemberMainViewControllerService {
    private String memberId;

    public MemberMainViewControllerImpl(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String getMemberName()   {
        return "Molloh";
    }

    @Override
    public String getHotel() {
        return null;
    }
}
