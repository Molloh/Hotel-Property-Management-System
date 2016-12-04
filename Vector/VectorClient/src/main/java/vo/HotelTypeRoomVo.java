package vo;

import java.io.Serializable;
import java.util.List;

import common.RoomType;
import po.HotelTypeRoomPo;

/**
 * @version 2016/12/03
 * @author 金灵益
 * @description 酒店总体拥有的房间类型，每种房间类型拥有数量，原始价格，开始日期，结束日期属性
 */
public class HotelTypeRoomVo implements Serializable {
	private static final long serialVersionUID = 1L;

	private RoomType type;
	private int number;            //房间数量
	private int price;
	private List<String> list;     //存储订单号，开始日期和结束日期
	
	public HotelTypeRoomVo(HotelTypeRoomPo po){
		this.type = po.getType();
		this.number = po.getNumOfTypeRoom();
		this.price = po.getPrice();
		this.list = po.getBookDate();
	}
	
	public RoomType getType(){
		return type;
	}
	
	public int getNumOfTypeRoom(){
		return number;
	}
	
	public int getPrice(){
		return price;
	}
	
	public List<String> getBookDate(){
		return list;
	}
	
	public void setBookDate(List<String> list){
		this.list = list;
	}
}
