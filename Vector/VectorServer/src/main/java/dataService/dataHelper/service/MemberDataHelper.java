package dataService.dataHelper.service;

import java.util.TreeMap;

import po.MemberPo;

public interface MemberDataHelper {

    public TreeMap<String, MemberPo> getMemberData(boolean isMember);

    public void updateMemberData(TreeMap<String, MemberPo> map,boolean isMember);
}
