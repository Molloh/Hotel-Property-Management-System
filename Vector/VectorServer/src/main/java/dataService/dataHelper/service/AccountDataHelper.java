package dataService.dataHelper.service;

import java.util.Map;

import po.AccountPo;

/**
 * @ author lienming
 * @ version 2016-11-27
 * @ description
 */
public interface AccountDataHelper {
    /**
     * @return	从数据文件中读取用户数据
     */
    public Map<String, AccountPo> getAccountData();

    /**
     * 向数据文件中写入用户数据
     * @param map
     */
    public void updateAccountData(Map<String, AccountPo> map);

}
