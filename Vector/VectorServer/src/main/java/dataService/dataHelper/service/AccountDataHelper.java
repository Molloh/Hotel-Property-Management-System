package dataService.dataHelper.service;

import java.util.TreeMap;

import common.AccountType;
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
    public TreeMap<String, AccountPo> getAccountData(AccountType type);

    /**
     * 向数据文件中写入用户数据
     * @param map
     */
    public void updateAccountData(TreeMap<String, AccountPo> map,AccountType type);

}
