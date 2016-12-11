package presentation.controller.impl.member;

import businessLogic.impl.AccountBlServiceImpl;
import businessLogic.impl.MemberBlServiceImpl;
import businessLogic.service.AccountBlService;
import businessLogic.service.MemberBlService;
import common.AccountType;
import common.Sex;
import presentation.common.SingletonItem;
import presentation.controller.service.member.MemberInfoViewControllerService;
import vo.MemberVo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Molloh
 * @version 2016/11/27
 * @description
 */
public class MemberInfoViewControllerImpl implements MemberInfoViewControllerService {
    private static final MemberInfoViewControllerService INSTANCE = new MemberInfoViewControllerImpl();

    private MemberBlService member;
    private AccountBlService account;
    private MemberVo memberVo;

    private String memberId;

    private MemberInfoViewControllerImpl() {
    	memberId = SingletonItem.getInstance().getActivateId();
        member = MemberBlServiceImpl.getInstance();
        account = AccountBlServiceImpl.getInstance();
        memberVo = member.getInfo(memberId);
    }

    public static MemberInfoViewControllerService getInstance() {
        return INSTANCE;
    }

    @Override
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public AccountType getAccountType() {
    	return account.getAccountTypeById(memberId);
    }

    @Override
    public String getMemberName() {
        //return "as";
    	return memberVo.getName();
    }

    @Override
    public LocalDate getBirthDay() {
        Date date = memberVo.getBirthday();
        Instant instant = date.toInstant();
        LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

    @Override
    public String getAddress() {
        //return "nanjing";
        return memberVo.getAddress();
    }

    @Override
    public String getCredit() {
        return String.valueOf(memberVo.getCredit());
        //return "100";
    }

    @Override
    public String getPhone() {
    	return memberVo.getPhone();
        //return "15050582691";
    }

    @Override
    public Sex getSex() {
        //return Sex.FEMALE;
        return memberVo.getSex();
    }

    @Override
    public String getEnterPrise() {
        return memberVo.getAddress();
    }

    @Override
    public String getVIPLevel() {
        return "VIP " + String.valueOf(memberVo.getVip());
    }

    @Override
    public void setSex(Sex sex) {
        memberVo.setSex(sex);
    }

    @Override
    public void setPhone(String phoneNum) {
        memberVo.setPhone(phoneNum);
    }

    @Override
    public void setMemberName(String memberName) {
        memberVo.setName(memberName);
    }

    @Override
    public void setBirthDay(LocalDate birthDay) {
        Instant instant = Instant.from(birthDay.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        memberVo.setBirthday(date);
    }

    @Override
    public void setAddress(String address) {
        memberVo.setAddress(address);
    }

    @Override
    public void setEnterPrise(String enterPrise) {
        memberVo.setAddress(enterPrise);
    }

}
