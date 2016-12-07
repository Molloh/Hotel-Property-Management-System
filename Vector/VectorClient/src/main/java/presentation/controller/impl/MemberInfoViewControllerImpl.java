package presentation.controller.impl;

import businessLogic.impl.MemberBlServiceImpl;
import businessLogic.service.MemberBlService;
import common.Sex;
import presentation.controller.SingletonId;
import presentation.controller.service.MemberInfoViewControllerService;
import presentation.controller.service.MemberMainViewControllerService;
import vo.MemberVo;

/**
 * @author Molloh
 * @version 2016/11/27
 * @description
 */
public class MemberInfoViewControllerImpl implements MemberInfoViewControllerService {
    private static MemberInfoViewControllerService memberInfoViewController;

    private MemberBlService member;
    private MemberVo memberVo;

    private String memberId;

    private MemberInfoViewControllerImpl() {
        member = MemberBlServiceImpl.getInstance();
        memberVo = member.getInfo(memberId);
    }

    public static MemberInfoViewControllerService getInstance() {
        if(memberInfoViewController == null)
            memberInfoViewController = new MemberInfoViewControllerImpl();

        return memberInfoViewController;
    }

    @Override
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String getMemberName() {
        return memberVo.getName();
    }

    @Override
    public String getBirthDay() {
        return "1997-3-19";
    }

    @Override
    public String getAddress() {
        return memberVo.getAddress();
    }

    @Override
    public String getCredit() {
        return String.valueOf(memberVo.getCredit());
        //return "111";
    }

    @Override
    public Sex getSex() {
        return memberVo.getSex();
    }


    @Override
    public void setSex() {

    }

    @Override
    public void setMemberName() {

    }

    @Override
    public void setBirthDay() {

    }

    @Override
    public void setAddress() {

    }

}
