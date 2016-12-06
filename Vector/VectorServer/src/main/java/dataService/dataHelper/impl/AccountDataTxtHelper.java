package dataService.dataHelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import dataService.dataHelper.service.AccountDataHelper;
import po.AccountPo;

/**
 * Updated by lienming on 2016-11-27.
 */
public class AccountDataTxtHelper implements AccountDataHelper {

    File file = new File(getClass().getResource("/textData/account.txt").getPath());
    //
	//File file = new File("src/main/resources/textData/account.txt");
    public TreeMap<String, AccountPo> getAccountData() {
        TreeMap<String, AccountPo> TreeMap = new TreeMap<String, AccountPo>();
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(
                    file), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String str = br.readLine();

            while (str != null) {

                String[] data = str.split(";");

                String username=data[0];
                String password=data[1];
                String Id = data[2];
                int logState=  Integer.parseInt(data[3]);

                AccountPo accountPo=new AccountPo(username,password,Id,logState);

                TreeMap.put(Id, accountPo);

                str = br.readLine();

            }

            br.close();

            return TreeMap;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccountData(TreeMap<String, AccountPo> TreeMap) {
        //写入用户数据
        try {
            FileWriter fw = new FileWriter(this.file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(fw);

            //对TreeMap进行遍历
            Iterator iterator = TreeMap.entrySet().iterator();
            while(iterator.hasNext()){ 
                Map.Entry entry = (Map.Entry)iterator.next();
                AccountPo accountPo = (AccountPo)entry.getValue();
                String str = accountPo.getMemberName()+";"+accountPo.getPassword()+";"
                        +accountPo.getId()+";"+accountPo.getLogState();
                writer.write(str);
                writer.write("\r\n");
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
