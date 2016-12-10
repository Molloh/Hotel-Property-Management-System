package po;

import java.io.Serializable;

import common.HotelPromotionType;
import vo.ActivityPromotionVo;

/**
 * @version 2016-12-06
 * @author 金灵益
 * @description 活动促销策略，属性：（促销类型），名称，开始时间，结束时间，折扣，（折扣范围），减价
 *              酒店、网站通用
 */
public class ActivityPromotionPo implements Serializable {
	private static final long serialVersionUID = 1L;
	private final HotelPromotionType type = HotelPromotionType.ACTIVITY;
	private String promotionName;
	private String startDate;
	private String endDate;
	private double discount;                 //折扣
	private int decPrice;                    //减少的价格大小
	
	public ActivityPromotionPo(String promotionName, String startDate, String endDate, 
			                   double discount, int decPrice){
		this.promotionName = promotionName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discount = discount;
		this.decPrice = decPrice;
	}
	
	public ActivityPromotionPo(ActivityPromotionVo vo){
		this.promotionName = vo.getPromotionName();
		this.startDate = vo.getStartDate();
		this.endDate = vo.getEndDate();
		this.discount = vo.getDiscount();
		this.decPrice = vo.getDecPrice();
	}
	
	public HotelPromotionType getPromotionType(){
		return type;
	}
	
	public void setPromotionName(String name){
		this.promotionName = name;
	}
	
	public String getPromotionName(){
		return promotionName;
	}
	
	public void setStartDate(String date){
		this.startDate = date;
	}
	
	public String getStartDate(){
		return startDate;
	}
	
	public void setEndDate(String date){
		this.endDate = date;
	}
	
	public String getEndDate(){
		return endDate;
	}
	
	public void setDiscount(double dis){
		this.discount = dis;
	}
	
	public double getDiscount(){
		return discount;
	}
	
	public void setDecPrice(int dec){
		this.decPrice = dec;
	}
	
	public int getDecPrice(){
		return decPrice;
	}
	

}