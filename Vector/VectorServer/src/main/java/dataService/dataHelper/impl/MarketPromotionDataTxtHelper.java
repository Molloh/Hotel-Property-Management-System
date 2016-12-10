package dataService.dataHelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import common.ResultMessage;
import dataService.dataHelper.service.MarketPromotionDataHelper;
import po.ActivityPromotionPo;
import po.LevelPo;

public class MarketPromotionDataTxtHelper implements MarketPromotionDataHelper{
	private File rootFile;
	
	public MarketPromotionDataTxtHelper(){
		rootFile = new File("src/main/resources/textData/market");
		rootFile.mkdir();
	}
	
	@Override
	public ResultMessage updateActivity(ActivityPromotionPo po){
		File actFile = new File(rootFile.getAbsolutePath() + "/promotion.txt");
		
		try {
			actFile.createNewFile();
			List<String> actList = getActivity();
			
			String str = po.getPromotionName() + "/" + po.getStartDate() + "/" + po.getEndDate() + "/" 
					     + (po.getDiscount()+"") + "/" + (po.getDecPrice()+"");
			
			if(actList.isEmpty()) actList.add(str);
			else{
				Iterator<String> it = actList.iterator();
				int flag = -1, count = 0;   //flag判断是否存在该活动策略
			
				while(it.hasNext()){
					if(it.next().startsWith(po.getPromotionName())){
						flag = 0; break;
					}
					count ++;
				}
				
				//若存在该活动策略，则更新；不存在则增加一条
				if(flag == -1)     actList.add(str);
				else               actList.set(count, str);
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(actFile.getAbsoluteFile()));
			
			Iterator<String> it = actList.iterator();
			while(it.hasNext()){
				writer.write(it.next());
				writer.newLine();
			}
			writer.close();
			
			return ResultMessage.SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}
	
	@Override
	public ResultMessage deleteActivity(ActivityPromotionPo po){
		File actFile = new File(rootFile.getAbsolutePath() + "/promotion.txt");
		
		try {
			actFile.createNewFile();

			List<String> actList = getActivity();
			Iterator<String> it = actList.iterator();
		
			BufferedWriter bw = new BufferedWriter(new FileWriter(actFile.getAbsoluteFile()));
			
			while(it.hasNext()){
				String str = it.next();
				if(str.startsWith(po.getPromotionName()))
					continue;
				bw.write(str);
				bw.newLine();
			}
			bw.close();
			
			return ResultMessage.SUCCEED;
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return ResultMessage.FAIL;
	}
	
	@Override
	public List<String> getActivity(){
		File actFile = new File(rootFile.getAbsolutePath() + "/promotion.txt");
		List<String> list = new ArrayList<String>();
		
		try {
			actFile.createNewFile();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(actFile), "UTF-8"));
			String str = br.readLine();
			
			while(str != null){
				list.add(str);
				str = br.readLine();
			}
			
			br.close();
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public ResultMessage updateLevelRule(List<LevelPo> list){
		File levelFile = new File(rootFile.getAbsolutePath() + "/rank.txt");
		
		try {
			levelFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(levelFile.getAbsoluteFile()));
			
			Iterator<LevelPo> it = list.iterator();
			while(it.hasNext()){
				LevelPo po = it.next();
				String str = (po.getLevel()+"") + "/" + (po.getCredit()+"") + "/" + (po.getDiscount()+"");
				writer.write(str);
				writer.newLine();
			}
			writer.close();
			
			return ResultMessage.SUCCEED;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.FAIL;
	}

	@Override
	public List<LevelPo> getLevelList() {
		File levelFile = new File(rootFile.getAbsolutePath() + "/rank.txt");
		List<LevelPo> levelList = new ArrayList<LevelPo>();
		
		try {
			levelFile.createNewFile();
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(levelFile), "UTF-8"));
			
			String str = br.readLine();
			while(str != null){
				String [] token = str.split("/");
				LevelPo po = new LevelPo(Integer.parseInt(token[0]), Integer.parseInt(token[1]), Double.parseDouble(token[2]));
				levelList.add(po);
				str = br.readLine();
			}
			br.close();
			
			return levelList;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
