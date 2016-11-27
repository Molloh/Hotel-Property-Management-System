package presentation.controller.Impl;

import businessLogic.impl.MemberBlServiceImpl;
import businessLogic.service.MemberBlService;
import common.ResultMessage;
import presentation.controller.service.MemberMainViewControllerService;
import vo.MemberVo;

public class MemberMainViewControllerServiceImpl implements MemberMainViewControllerService{

    private MemberBlService memberBlService ;  //下层

    private static MemberMainViewControllerServiceImpl memberImpl;  //本层

    public static MemberMainViewControllerServiceImpl getInstance(){
        if(memberImpl==null)
            memberImpl = new MemberMainViewControllerServiceImpl() ;
        return memberImpl;
    }

    private MemberMainViewControllerServiceImpl(){
        memberBlService = MemberBlServiceImpl.getInstance();
    }


    public int getCredit(String id) {
    	return memberBlService.getCredit(id);
    }

    public ResultMessage chargeCredit(String id, int amount) {
    	return memberBlService.chargeCredit(id, amount);
    }

    public MemberVo getInfo(String id) {
    	return memberBlService.getInfo(id);
    }

    public ResultMessage modifyInfo(MemberVo vo) {
    	return memberBlService.modifyInfo(vo);
    }
}
