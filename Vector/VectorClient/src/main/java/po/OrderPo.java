package po;

import java.util.Date;

import common.OrderCondition;
import common.RoomType;

/**
 * @ author Aobang 
 * @ version 2016/11/27 
 * @ description
 */
public class OrderPo {
	private String orderId;
	private OrderCondition condition;
	private String memberId;
	private String memberName;
	private Date createTime;
	private Date checkInTime;
	private Date checkOutTime; // 退房时间
	private String hotel;
	private String hotelId;
	private RoomType roomType;
	private int numOfRoom;
	private int numOfGuest;
	private boolean childExist;
	private int originalPrice;
	private double discount;
	private int discountedPrice;

	public OrderPo(String orderId, OrderCondition condition, String memberId, String memberName, Date createTime, Date checkInTime, Date checkOutTime, String hotel, String hotelId,
			RoomType roomType, int numOfRoom, int numOfGuest, boolean childExist, int originalPrice, double discount,
			int discountedPrice) {
		this.orderId = orderId;
		this.condition = condition;
		this.memberId = memberId;
		this.memberName = memberName;
		this.createTime = createTime;
		this.checkInTime = checkInTime;
		this.checkOutTime = checkOutTime;
		this.hotel = hotel;
		this.hotelId = hotelId;
		this.roomType = roomType;
		this.numOfRoom = numOfRoom;
		this.numOfGuest = numOfGuest;
		this.childExist = childExist;
		this.originalPrice = originalPrice;
		this.discount = discount;
		this.discountedPrice = discountedPrice;
	}
	
	public String getOrderId() {
		return this.orderId;
	}
	public OrderCondition getCondition() {
		return this.condition;
	}
	public String getMemberId() {
		return this.memberId;
	}
	public String getMemberName() {
		return this.memberName;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	public Date getCheckInTime() {
		return this.checkInTime;
	}
	public Date getCheckOutTime() {
		return this.checkOutTime;
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
	
	public void setCondition(OrderCondition condition) {
		this.condition = condition;
	}
	public void setCreateTime(Date time) {
		this.createTime = time;
	}
	public void setCheckInTime(Date time) {
		this.checkInTime = time;
	}
	public void setCheckOutTime(Date time) {
		this.checkOutTime = time;
	}
}
