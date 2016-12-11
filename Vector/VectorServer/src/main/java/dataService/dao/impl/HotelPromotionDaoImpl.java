package dataService.dao.impl;

import java.util.List;

import common.ResultMessage;
import dataService.dao.service.HotelPromotionDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.HotelPromotionDataHelper;
import po.ActivityPromotionPo;
import po.BirthdayProPo;
import po.CompanyProPo;
import po.RoomPromotionPo;

/**
 * @version 2016-12-08
 * @author 金灵益
 *
 */
public class HotelPromotionDaoImpl implements HotelPromotionDao{
	
	private DataFactory dataFactory;
	
	private HotelPromotionDataHelper hotelPromotionDataHelper;
	
	private static HotelPromotionDaoImpl hotelPromotionDaoImpl;
	
	public static HotelPromotionDaoImpl getInstance(){
		if(hotelPromotionDaoImpl == null){
			hotelPromotionDaoImpl = new HotelPromotionDaoImpl();
		}
		return hotelPromotionDaoImpl;
	}

	private HotelPromotionDaoImpl(){
		super();
		dataFactory = new DataFactoryImpl();
		hotelPromotionDataHelper = dataFactory.getHotelPromotionDataHelper();
	}
	
	
	
	public ResultMessage upActPromotion(String hotelId, ActivityPromotionPo po){
		return hotelPromotionDataHelper.updateActivity(hotelId, po);
	}
	
	public ResultMessage delActPromotion(String hotelId, ActivityPromotionPo po){
		return hotelPromotionDataHelper.deleteActivity(hotelId, po);
	}
	
	public List<String> getActProList(String hotelId){
		return hotelPromotionDataHelper.getActivity(hotelId);
	}

	public ResultMessage upBirthPromotion(String hotelId, BirthdayProPo po){
		return hotelPromotionDataHelper.updateBirthPromotion(hotelId, po);
	}
	
	public BirthdayProPo getBirthPromotion(String hotelId){
		return hotelPromotionDataHelper.getBirthPromotion(hotelId);
	}
	
	public ResultMessage updateCooperPro(String hotelId, CompanyProPo po){
		return hotelPromotionDataHelper.updateCompanyPro(hotelId, po);
	}
	
	public CompanyProPo getCooperPro(String hotelId){
		return hotelPromotionDataHelper.getCompanyPro(hotelId);
	}
	
	public ResultMessage upRoomPromotion(String hotelId, RoomPromotionPo po){
		return hotelPromotionDataHelper.updateRoomPromotion(hotelId, po);
	}
	
	public RoomPromotionPo getRoomPromotion(String hotelId){
		return hotelPromotionDataHelper.getRoomPromotion(hotelId);
	}
}
