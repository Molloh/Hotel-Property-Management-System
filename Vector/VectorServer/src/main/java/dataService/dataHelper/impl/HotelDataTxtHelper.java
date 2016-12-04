package dataService.dataHelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.ResultMessage;
import common.RoomType;
import dataService.dataHelper.service.HotelDataHelper;
import po.HotelPo;
import po.HotelRoom;

public class HotelDataTxtHelper implements HotelDataHelper{
	private String rootPath = "src/main/resources/textData/hotel/";
	
	@Override
	public Map<String, HotelPo> getHotelData() {
		//读取数据
		File file = new File(rootPath + "hotelList.txt");
		Map<String,HotelPo> map = new HashMap<String, HotelPo>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				String[] data = str.split("/");
				HotelPo po = new HotelPo();
				po.setId(data[0]);
				po.setHotelName(data[1]);
				po.setHotelAddress(data[2]);
				po.setInBusiness(data[3]);
				po.setHotelTel(data[4]);
				po.setStars(Integer.valueOf(data[5]));
				po.setPoints(Double.valueOf(data[6]));
				po.setNumOfpoint(Integer.valueOf(data[7]));
				po.setHotelInfo(data[8]);
				
				po.setRooms(getHotelRoom(data[0]));
				po.setCommentList(getComments(data[0]));
				map.put(data[0], po);
				str = br.readLine();
			}

			br.close();
			
			return map;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ResultMessage addHotelData(HotelPo po){
		File file = new File(rootPath + "hotelList.txt");
		try{
			File rootFile = new File(rootPath + po.getId());	
			rootFile.mkdir();
	
			File commentfile = new File(rootFile.getAbsolutePath(),"comments.txt");	
			commentfile.createNewFile();
			
			File roomfile = new File(rootFile.getAbsolutePath(),"roomList.txt");	
			roomfile.createNewFile();
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
			BufferedWriter writer = new BufferedWriter(fw);
			
			String str = po.getId() + "/" + po.getHotelName() + "/" + po.getHotelAddress() + "/"
					+ po.getInBusiness() + "/" + po.getHotelTel() + "/" + (po.getStars()+"") + "/"
					+ (po.getPoStrings()+"") + "/" + (po.getNumOfpoint()+"") + "/" + po.getHotelInfo();
			
			writer.write(str);
			writer.newLine();
			writer.close();
			return ResultMessage.SUCCEED;
		}catch(Exception e){
			e.printStackTrace();
			return ResultMessage.FAIL;
		}
	}
	
	@Override
	public ResultMessage updateHotelListData(Map<String, HotelPo> map) {
		//写入数据  包括更新hotelList列表和酒店的房间和评论
		File file = new File(rootPath + "hotelList.txt");
		try {		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			//对map进行遍历
			Iterator<Map.Entry<String,HotelPo>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<String,HotelPo> entry = iterator.next();
				HotelPo po = entry.getValue();
				String str = po.getId() + "/" + po.getHotelName() + "/" + po.getHotelAddress() + "/"
						+ po.getInBusiness() + "/" + po.getHotelTel() + "/" + (po.getStars()+"") + "/"
						+ (po.getPoStrings()+"") + "/" + (po.getNumOfpoint()+"") + "/" + po.getHotelInfo();
				
	//			updateHotelRoom(po.getId(),po.getRoom());
	//			updateComments(po.getId(),po.getCommentList());
				writer.write(str);
				
				writer.newLine();
			}
			writer.close();	
			return ResultMessage.SUCCEED;
		} catch (Exception e) {
			e.printStackTrace();
			return ResultMessage.FAIL;
		}	
	}

	@Override
	public ResultMessage deleteHotelData(String hotelId) {
		//在酒店列表中删除酒店信息
		Map<String, HotelPo> map = getHotelData();
		if(!map.containsKey(hotelId))
			return ResultMessage.FAIL;
		
		Map<String,HotelPo> updateMap = new HashMap<String, HotelPo>();
		Iterator<Map.Entry<String,HotelPo>> iterator = map.entrySet().iterator();
		
		while(iterator.hasNext()){
			Map.Entry<String,HotelPo> entry = iterator.next();
			HotelPo po = entry.getValue();
			if( !po.getId().equals(hotelId) ){
				updateMap.put(po.getId(), po);	
			}	
		}
		updateHotelListData(updateMap);
		
		//删除该酒店房间、评价文件
		File hotelfile = new File(rootPath + hotelId);
		hotelfile.mkdir();
			
		for(File files : hotelfile.listFiles()){
			files.delete();
		}
		
		hotelfile.delete();

		return ResultMessage.SUCCEED;		
	}

	
	@Override
	public Map<String, HotelRoom> getHotelRoom(String hotelId) {
		File file = new File(rootPath + hotelId + "/roomList.txt");
		Map<String, HotelRoom> map = new HashMap<String, HotelRoom>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				String[] data = str.split("/");
				HotelRoom room = new HotelRoom(hotelId);
				room.setRoomID(data[0]);
				String type = data[1];
				switch(type){
				case "SINGLE": room.setRoomType(RoomType.SINGLE);  break;
				case "DOUBLE": room.setRoomType(RoomType.DOUBLE);  break;
				case "FAMILY": room.setRoomType(RoomType.FAMILY);  break;
				}
				room.setOriginalPrice(Integer.valueOf(data[2]));
				if(data[3].equals("true"))  room.setisEmpty(true);
				if(data[3].equals("false")) room.setisEmpty(false);
				room.setCheckinDate(data[4]);
				room.setCheckoutDate(data[5]);
				
				map.put(data[0], room);
				str = br.readLine();
			}

			br.close();
			
			return map;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public List<String> getComments(String hotelId){
		File file = new File(rootPath + hotelId + "/comments.txt");
		List<String> comment = new ArrayList<String>();
		
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				String result = "";
				while( !str.trim().equals("*") ){
					result += str + "\n";
					str = br.readLine();
				}
				comment.add(result);
				str = br.readLine();
			}
			
			br.close();
			return comment;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public void updateHotelRoom(String hotelId,Map<String,HotelRoom> map){
		try {	
			File rootFile = new File(rootPath + hotelId);
			rootFile.mkdir();
		
			File file = new File(rootFile.getAbsolutePath(),"roomList.txt");	
			if(!file.exists())
				file.createNewFile();

			
			file.createNewFile();	
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			//对map进行遍历
			Iterator<Map.Entry<String,HotelRoom>> iterator = map.entrySet().iterator();
			
			while(iterator.hasNext()){
				Map.Entry<String,HotelRoom> entry = iterator.next();
				HotelRoom room = entry.getValue();
				
				String str = room.getRoomID() + "/";
				switch (room.getRoomtype()){
				case SINGLE: str += "SINGLE";break;
				case DOUBLE: str += "DOUBLE";break;
				case FAMILY :str += "FAMILY"; break;
				}
				
				str += "/" + room.getPrice() + "/";
				if(room.getisEmpty() == true)       str += "true/";
				if(room.getisEmpty() == false)      str += "false/";			
				
				str += room.getCheckInDate() + "/" + room.getCheckOutdate();
					
				writer.write(str);		
				writer.newLine();
			}
			writer.close();	
		} catch (Exception e) {
			e.printStackTrace();	
		}	
	}
	
	
	@Override
	public void updateComments(String hotelId,List<String> commentList){
		
		try {
			File rootFile = new File(rootPath + hotelId);
			rootFile.mkdir();
		
			File file = new File(rootFile.getAbsolutePath(),"comments.txt");	
			if(!file.exists())
				file.createNewFile();
			
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
				
			Iterator<String> iterator = commentList.iterator();
			while(iterator.hasNext()){
				writer.write(iterator.next());
				writer.newLine();
				writer.write("*");
				writer.newLine();
			}		
			writer.close();	
		} catch (Exception e) {
			e.printStackTrace();	
		}	
	}
}