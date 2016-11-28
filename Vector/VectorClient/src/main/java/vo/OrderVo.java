package vo;

import java.util.Date;

import common.RoomType;
import po.OrderPo;

public class OrderVo {
	private String orderId;
	private Date createTime;
	private Date executeTime;
	private Date finishTime; //退房时间
	private String hotel;
	private String hotelId;
	private RoomType roomType;
	private int numOfRoom;
	private int numOfGuest;
	private boolean childExist;
	private int originalPrice;
	private double discount;
	private int discountedPrice;
	
	public OrderVo(OrderPo po) {
		this.orderId = po.getOrderId();
		this.createTime = po.getCreateTime();
		this.executeTime = po.getExecuteTime();
		this.finishTime = po.getFinishTime();
		this.hotel = po.getHotel();
		this.hotelId = po.getHotelId();
		this.roomType = po.getRoomType();
		this.numOfRoom = po.getNumOfRoom();
		this.numOfGuest = po.getNumOfGuest();
		this.childExist = po.getChildExist();
		this.originalPrice = po.getOriginalPrice();
		this.discount = po.getDiscount();
		this.discountedPrice = po.getDiscountedPrice();
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	public Date getExecuteTime() {
		return this.executeTime;
	}
	public Date getfinishTime() {
		return this.finishTime;
	}
	public String getHotel() {
		return this.hotel;
	}
	public String getHotelId() {
		return this.hotelId;
	}
	public RoomType getRoomType() {
		return this.roomType;
	}
	public int getNumOfRoom() {
		return this.numOfRoom;
	}
	public int getNumOfGuest() {
		return this.numOfGuest;
	}
	public boolean getChildExist() {
		return this.childExist;
	}
	public int getOriginalPrice() {
		return this.originalPrice;
	}
	public double getDiscount() {
		return this.discount;
	}
	public int getDiscountedPrice() {
		return this.discountedPrice;
	}
	
	public void setCreateTime(Date time) {
		this.createTime = time;
	}
	public void setExecuteTime(Date time) {
		this.executeTime = time;
	}
	public void setFinishTime(Date time) {
		this.finishTime = time;
	}
	
}
