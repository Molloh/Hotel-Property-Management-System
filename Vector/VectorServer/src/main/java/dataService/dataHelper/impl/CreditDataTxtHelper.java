package dataService.dataHelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import common.ResultMessage;
import dataService.dataHelper.service.CreditDataHelper;
import po.CreditRecordPo;

public class CreditDataTxtHelper implements CreditDataHelper{
	String memberFile    ="src/main/resources/textData/creditRecord/member/";
	String enterpriseFile="src/main/resources/textData/creditRecord/enterprise/";
	
	public List<CreditRecordPo> getCreditRecordData(String id){
		List<CreditRecordPo> list = new ArrayList<CreditRecordPo>();
		String txtFileName = getMemberTxtFile(id);
		File txtFile = new File(txtFileName);
		try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(
                    txtFile), "UTF-8");
            BufferedReader br = new BufferedReader(reader);
            String str = br.readLine();
            
            while (str != null) {
                String[] data = str.split(";");

                int creditNow = Integer.parseInt(data[0]);
                String reason    = data[1];
                String str_time  = data[2];
                int delta = Integer.parseInt(data[3]);
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                CreditRecordPo po = new CreditRecordPo(creditNow,reason,delta);
                list.add(po);

                str = br.readLine();
            }

            br.close();

            return list;

        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	public ResultMessage updateCreditRecordData(List<CreditRecordPo> list,String id){
		String txtFileName = getMemberTxtFile(id);
		File txtFile = new File(txtFileName);
		try {
			if(!txtFile.exists())
				return ResultMessage.FAIL;//txtFile.createNewFile();
			
			FileWriter fw = new FileWriter(txtFile);
			BufferedWriter br = new BufferedWriter(fw);
				
			Iterator<CreditRecordPo> iterator = list.iterator();
			while(iterator.hasNext()){
				br.write(iterator.next().parseString());
				br.newLine();
			}		
			br.close();	
		} catch (Exception e) {
			e.printStackTrace();	
		}	
		return ResultMessage.SUCCEED;
	}
	
	public ResultMessage newCredit(String id){
		String txtFileName = getMemberTxtFile(id);
		File txtFile = new File(txtFileName);
		try {
			if(txtFile.exists())
				return ResultMessage.FAIL;
			txtFile.createNewFile();
			FileWriter fw = new FileWriter(txtFile);
			BufferedWriter br = new BufferedWriter(fw);
			CreditRecordPo po = new CreditRecordPo();
			br.write(po.parseString());
			br.newLine();
			br.close();
		}catch (Exception e) {
			e.printStackTrace();	
		}	
		return ResultMessage.SUCCEED;
	}
	
	public String getMemberTxtFile(String id){
		return memberFile + id ; 
	}
	
}
