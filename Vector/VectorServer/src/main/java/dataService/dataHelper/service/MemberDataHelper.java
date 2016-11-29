package dataService.dataHelper.service;

import java.util.TreeMap;

import po.MemberPo;

public interface MemberDataHelper {

    public TreeMap<String, MemberPo> getMemberData();

    public void updateMemberData(TreeMap<String, MemberPo> map);
}
