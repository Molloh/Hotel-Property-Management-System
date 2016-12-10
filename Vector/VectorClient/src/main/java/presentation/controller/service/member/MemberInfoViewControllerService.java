package presentation.controller.service.member;

import common.Sex;

/**
 * @author Molloh
 * @version 2016/11/27
 * @description
 */
public interface MemberInfoViewControllerService {

    public String getMemberName();

    public String getBirthDay();

    public String getAddress();

    public String getCredit();

    public String getPhone();

    public Sex getSex();

    public String getEnterPrise();

    public void setMemberId(String memberId);

    public void setSex();

    public void setPhone(String phoneNum);

    public void setMemberName(String memberName);

    public void setBirthDay(String birthDay);

    public void setAddress(String address);

    public void setEnterPrise(String enterPrise);

}
