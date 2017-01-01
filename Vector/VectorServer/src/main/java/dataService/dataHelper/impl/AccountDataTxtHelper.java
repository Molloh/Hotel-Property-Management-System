package dataService.dataHelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import common.AccountType;
import dataService.dataHelper.service.AccountDataHelper;
import po.AccountPo;

/**
 * Updated by lienming on 2016-11-27.
 */
public class AccountDataTxtHelper implements AccountDataHelper {
	File memberFile    = new File("src/main/resources/textData/account/memberAccount.txt");
	File enterpriseFile= new File("src/main/resources/textData/account/enterpriseAccount.txt");
	File hotelFile     = new File("src/main/resources/textData/account/hotelAccount.txt");
	File marketerFile  = new File("src/main/resources/textData/account/marketerAccount.txt");
	File managerFile   = new File("src/main/resources/textData/account/managerAccount.txt");
	
    public TreeMap<String, AccountPo> getAccountData(AccountType type) {
        TreeMap<String, AccountPo> TreeMap = new TreeMap<String, AccountPo>();
        File file = getFile(type) ; 
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(
                    file), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String str = br.readLine();
            
            while (str != null) {
           // 	str = SecurityHelper.decode(str);
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

    public void updateAccountData(TreeMap<String, AccountPo> TreeMap,AccountType type) {
        //写入用户数据
    	File file = getFile(type);
        try {
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter writer = new BufferedWriter(fw);
            //对TreeMap进行遍历
            Iterator iterator = TreeMap.entrySet().iterator();
            while(iterator.hasNext()){ 
                Map.Entry entry = (Map.Entry)iterator.next();
                AccountPo accountPo = (AccountPo)entry.getValue();
                String str = accountPo.getMemberName()+";"+accountPo.getPassword()+";"
                        +accountPo.getId()+";"+accountPo.getLogState();
                
           //     str = SecurityHelper.encode(str);
                writer.write(str);
                writer.write("\r\n");
            }

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public File getFile(AccountType type){
    	switch(type){
        case Member		: return this.memberFile;   
        case Enterprise : return this.enterpriseFile;
        case Hotel 		: return this.hotelFile;
        case Marketer	: return this.marketerFile;
        case Manager	: return this.managerFile;
        default			: return null;
        }
    }
    
}
