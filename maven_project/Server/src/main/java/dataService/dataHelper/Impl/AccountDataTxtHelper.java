package dataService.dataHelper.Impl;

import dataService.dataHelper.AccountDataHelper;
import po.AccountPo;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Administrator on 2016-11-13.
 */
public class AccountDataTxtHelper implements AccountDataHelper {

    public Map<String, AccountPo> getAccountData() {
        Map<String, AccountPo> map = new HashMap<String, AccountPo>();
        File file = new File("src/txtData/account.txt");
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

                map.put(Id, accountPo);

                str = br.readLine();

            }

            br.close();

            return map;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateAccountData(Map<String, AccountPo> map) {
        //写入用户数据
        File file = new File("src/txtData/account.txt");
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(fw);

            //对map进行遍历
            Iterator<Map.Entry<String, AccountPo>> iterator = map.entrySet().iterator();
            while(iterator.hasNext()){
                Map.Entry<String, AccountPo> entry = iterator.next();
                AccountPo accountPo = entry.getValue();
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
