package presentation.controller.impl;

import presentation.controller.service.MemberInfoViewControllerService;

/**
 * @author Molloh
 * @version 2016/11/27
 * @description
 */
public class MemberInfoViewControllerImpl implements MemberInfoViewControllerService {
    @Override
    public String getMemberName() {
        return "Molloh";
    }

    @Override
    public String getBirthDay() {
        return "1997-3-19";
    }

    @Override
    public String getAddress() {
        return null;
    }

    @Override
    public String getVIPLevel() {
        return null;
    }

    @Override
    public boolean getSex() {
        return true;
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
