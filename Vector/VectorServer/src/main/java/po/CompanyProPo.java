package po;

import java.io.Serializable;
import java.util.List;

import common.HotelPromotionType;

/**
 * @version 2016-12-07
 * @author 金灵益
 * @description 合作企业促销策略，属性：折扣，优惠减少价格，合作企业列表
 */
public class CompanyProPo implements Serializable {
	private static final long serialVersionUID = 1L;
	private final HotelPromotionType type = HotelPromotionType.COOPERATION;
	private double discount;                    //折扣
	private int decPrice;                       //优惠减价
	private List<String> company;               //合作企业账号
	
	public CompanyProPo(double discount, int decPrice, List<String> company){
		this.discount = discount;
		this.decPrice = decPrice;
		this.company = company;
	}
	
	public HotelPromotionType getPromotionType(){
		return type;
	}
	
	public List<String> getCompanyList(){
		return company;
	}
	
	public double getDiscount(){
		return discount;
	}
	
	public int getDecPrice(){
		return decPrice;
	}
}
