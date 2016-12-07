package presentation.controller.service;

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

    public Sex getSex();

    public void setMemberId(String memberId);

    public void setSex();

    public void setMemberName();

    public void setBirthDay();

    public void setAddress();

}
