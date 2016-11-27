package dataService.dataHelper.service;

import po.MemberPo;

import java.util.Map;

public interface MemberDataHelper {

    public Map<String, MemberPo> getMemberData();

    public void updateMemberData(Map<String, MemberPo> map);
}
