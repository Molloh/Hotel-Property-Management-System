package presentation.controller.impl.member;

import businessLogic.impl.MemberBlServiceImpl;
import businessLogic.service.MemberBlService;
import common.Sex;
import presentation.controller.service.member.MemberInfoViewControllerService;
import vo.MemberVo;

/**
 * @author Molloh
 * @version 2016/11/27
 * @description
 */
public class MemberInfoViewControllerImpl implements MemberInfoViewControllerService {
    private static final MemberInfoViewControllerService INSTANCE = new MemberInfoViewControllerImpl();

    private MemberBlService member;
    private MemberVo memberVo;

    private String memberId;

    private MemberInfoViewControllerImpl() {/*
        member = MemberBlServiceImpl.getInstance();
        memberVo = member.getInfo(memberId);*/
    }

    public static MemberInfoViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String getMemberName() {
        return "as";
    	//return memberVo.getName();
    }

    @Override
    public String getBirthDay() {
        return "1997-3-19";
    }

    @Override
    public String getAddress() {
        return "nanjing";
        //return memberVo.getAddress();
    }

    @Override
    public String getCredit() {
        //return String.valueOf(memberVo.getCredit());
        return "100";
    }

    @Override
    public String getPhone() {
        return "15050582691";
    }

    @Override
    public Sex getSex() {
        return Sex.FEMALE;
        //return memberVo.getSex();
    }

    @Override
    public String getEnterPrise() {
        return "南京大学";
    }


    @Override
    public void setSex() {

    }

    @Override
    public void setPhone(String phoneNum) {

    }

    @Override
    public void setMemberName(String memberName) {

    }

    @Override
    public void setBirthDay(String birthDay) {

    }

    @Override
    public void setAddress(String address) {

    }

    @Override
    public void setEnterPrise(String enterPrise) {

    }


}
