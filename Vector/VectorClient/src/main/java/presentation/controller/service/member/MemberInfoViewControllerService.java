package presentation.controller.service.member;

import common.AccountType;
import common.Sex;

import java.time.LocalDate;

/**
 * @author Molloh
 * @version 2016/12/11
 * @description Member编辑个人信息处理
 */
public interface MemberInfoViewControllerService {

    String getMemberName();

    LocalDate getBirthDay();

    String getAddress();

    String getCredit();

    String getPhone();
    
    AccountType getAccountType();

    Sex getSex();

    String getEnterPrise();

    String getVIPLevel();

    void setMemberId(String memberId);

    void setSex(Sex sex);

    void setPhone(String phoneNum);

    void setMemberName(String memberName);

    void setBirthDay(LocalDate birthDay);

    void setAddress(String address);

    void setEnterPrise(String enterPrise);

}
