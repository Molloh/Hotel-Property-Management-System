package dataService.dataHelper.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import common.OrderCondition;
import common.RoomType;
import dataService.dataHelper.service.OrderDataHelper;
import po.OrderPo;

public class OrderDataTxtHelper implements OrderDataHelper {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public Map<String, OrderPo> getOrderData() {
		File file = new File("src/main/resources/textData/orderData.txt");
		Map<String, OrderPo> map = new HashMap<String, OrderPo>();
		try {
			InputStreamReader reader = new InputStreamReader(new FileInputStream(
					file), "UTF-8");
			BufferedReader br = new BufferedReader(reader);
			String str = br.readLine();
			
			while (str != null) {
				String[] data = str.split(";");
				
				String orderId = data[0];
				OrderCondition condition = OrderCondition.valueOf(data[1]);
				String memberId = data[2];
				String memberName = data[3];
				Date createTime = sdf.parse(data[4]);
				Date checkInTime = sdf.parse(data[5]);
				Date checkOutTime = sdf.parse(data[6]); // 退房时间
				String hotel = data[7];
				String hotelId = data[8];
				RoomType roomType = RoomType.valueOf(data[9]);
				int numOfRoom = Integer.parseInt(data[10]);
				int numOfGuest = Integer.parseInt(data[11]);
				boolean childExist = Boolean.parseBoolean(data[12]);
				int originalPrice = Integer.parseInt(data[13]);
				double discount = Double.parseDouble(data[14]);
				int discountedPrice = Integer.parseInt(data[15]);
				
				OrderPo orderPo = new OrderPo(orderId, condition, memberId, memberName, createTime, checkInTime,
						checkOutTime, hotel, hotelId, roomType, numOfRoom, numOfGuest, childExist, originalPrice,
						discount, discountedPrice);
				map.put(orderId, orderPo);
				
				str = br.readLine();
				
			}

			br.close();
			
			return map;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateOrderData(Map<String, OrderPo> map) {
		//写入数据
		File file = new File("src/main/resources/textData/orderData.txt");
		try {		
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter writer = new BufferedWriter(fw);
			
			//对map进行遍历
			Iterator<Map.Entry<String, OrderPo>> iterator = map.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<String, OrderPo> entry = iterator.next();
				OrderPo orderPo = entry.getValue();
				String str = orderPo.getOrderId() + ";" + orderPo.getCondition().toString() + ";"
						+ orderPo.getMemberId() + ";" + orderPo.getMemberName() + ";"
						+ sdf.format(orderPo.getCreateTime()) + ";" + sdf.format(orderPo.getCheckInTime()) + ";"
						+ sdf.format(orderPo.getCheckOutTime()) + ";" + orderPo.getHotel() + ";" + orderPo.getHotelId()
						+ ";" + orderPo.getRoomType().toString() + ";" + String.valueOf(orderPo.getNumOfRoom()) + ";"
						+ String.valueOf(orderPo.getNumOfGuest()) + ";" + String.valueOf(orderPo.getChildExist()) + ";"
						+ String.valueOf(orderPo.getOriginalPrice()) + ";" + String.valueOf(orderPo.getDiscount()) + ";"
						+ String.valueOf(orderPo.getDiscountedPrice());
				
				writer.write(str);
				writer.write("\r\n");
			}
			
			writer.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


}
