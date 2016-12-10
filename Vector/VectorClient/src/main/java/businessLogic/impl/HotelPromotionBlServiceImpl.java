package businessLogic.impl;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import businessLogic.service.HotelPromotionBlService;
import common.ResultMessage;
import common.RoomType;
import dataService.dao.service.HotelPromotionDao;
import po.ActivityPromotionPo;
import po.BirthdayProPo;
import po.CompanyProPo;
import po.RoomPromotionPo;
import rmi.RemoteHelper;
import vo.ActivityPromotionVo;
import vo.BirthdayProVo;
import vo.CompanyProVo;
import vo.MemberVo;
import vo.RoomPromotionVo;

public class HotelPromotionBlServiceImpl implements HotelPromotionBlService{
	
	private HotelPromotionDao hotelPromotionDao;
	private static HotelPromotionBlServiceImpl hotelPromotionBlServiceImpl;
	
	public static HotelPromotionBlServiceImpl getInstance(){
		if(hotelPromotionBlServiceImpl == null)
			hotelPromotionBlServiceImpl = new HotelPromotionBlServiceImpl();
		return hotelPromotionBlServiceImpl;
	}

	private HotelPromotionBlServiceImpl(){
		hotelPromotionDao = RemoteHelper.getInstance().getHotelPromotionDao();
	}
	
	@Override
	public ResultMessage addActivityStrategy(String hotelId, ActivityPromotionVo vo){
		//输入的折扣非法，日期非法
		if(vo.getDiscount()<=0 || vo.getDiscount()>1
				|| (dateValid(vo.getStartDate(), vo.getEndDate(), null)== false))
			return ResultMessage.INVALID;
		
		try {
			List<String> activityList = hotelPromotionDao.getActProList(hotelId);
			Iterator<String> it = activityList.iterator();
			//检查促销活动名称有无重复
			while(it.hasNext()){
				String [] token = it.next().split("/");
				if(token[0].equals(vo.getPromotionName()))
					return ResultMessage.INVALID;     //名称重复，无法添加
			}	
			return hotelPromotionDao.upActPromotion(hotelId, new ActivityPromotionPo(vo));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}
	
	@Override
	public ResultMessage upActivityStrategy(String hotelId, ActivityPromotionVo vo){
		//输入的折扣非法，日期非法
		if(vo.getDiscount()<=0 || vo.getDiscount()>1
				|| (dateValid(vo.getStartDate(), vo.getEndDate(), null)== false))	
			return ResultMessage.INVALID;
		
		try {
			List<String> activityList = hotelPromotionDao.getActProList(hotelId);
			Iterator<String> it = activityList.iterator();
			//检查有无该促销活动
			while(it.hasNext()){
				String [] token = it.next().split("/");
				if(token[0].equals(vo.getPromotionName()))
					return hotelPromotionDao.upActPromotion(hotelId, new ActivityPromotionPo(vo));
			}	
			
			return ResultMessage.INVALID;    //不存在该活动
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}
	
	@Override
	public ResultMessage delActivityStrategy(String hotelId, ActivityPromotionVo vo){
		try {
			List<String> activityList = hotelPromotionDao.getActProList(hotelId);
			Iterator<String> it = activityList.iterator();
			//检查有无该促销活动
			while(it.hasNext()){
				String [] token = it.next().split("/");
				if(token[0].equals(vo.getPromotionName()))
					return hotelPromotionDao.delActPromotion(hotelId, new ActivityPromotionPo(vo));
			}	
			
			return ResultMessage.INVALID;    //不存在该活动
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.FAIL;
	}
	
	@Override
	public List<ActivityPromotionVo> getCurrentActStrategy(String hotelId){
		List<ActivityPromotionVo> list = new ArrayList<ActivityPromotionVo>();
		
		try {
			List<String> actStr = hotelPromotionDao.getActProList(hotelId);
			Iterator<String> it = actStr.iterator();
			
			String now = getNow("yyyy-MM-dd-HH");
			
			//得到当前时期有效的活动促销列表
			while(it.hasNext()){
				String [] token = it.next().split("/");
				/*
				 * token[1]   活动开始日期
				 * token[2]   活动结束日期
				 * 若当前在某次活动期间内
				 */
				if(dateValid(token[1], token[2], now) == true){
					ActivityPromotionVo vo = new ActivityPromotionVo(token[0], token[1], token[2],
							                   Double.parseDouble(token[3]));
					list.add(vo);
				}
			}
			
			return list;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ResultMessage updateCooperationStrategy(String hotelId, CompanyProVo vo){
		if(vo.getDiscount()<=0 || vo.getDiscount()>1)
			return ResultMessage.INVALID;
		
		try {
			return hotelPromotionDao.updateCooperPro(hotelId, new CompanyProPo(vo.getDiscount(),
					                                                    vo.getCompanyList()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.FAIL;
	}
	
	@Override
	public CompanyProVo getCooperationStrategy(String hotelId, String memberId){
		try {
			CompanyProVo vo = new CompanyProVo(hotelPromotionDao.getCooperPro(hotelId));
			List<String> idList = vo.getCompanyList();
			
			//检查该酒店的合作企业是否有该企业
			if(idList.contains(memberId))
				return vo;
			
			return null;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public ResultMessage updateOrderRoomStrategy(String hotelId, RoomPromotionVo vo){
		//检查输入合法性
		if(vo.getDiscount()<=0 || vo.getDiscount()>1 || vo.getNumOfRoom()<0)
			return ResultMessage.INVALID;
		
		try {
			return hotelPromotionDao.upRoomPromotion(hotelId, new RoomPromotionPo(vo.getPromotionName(),
					                            vo.getNumOfRoom(), vo.getDiscount()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.FAIL;
	}
	
	@Override
	public RoomPromotionVo getOrderRoomStrategy(String hotelId, int numOfRoom){
		
		try {
			RoomPromotionVo vo = new RoomPromotionVo(hotelPromotionDao.getRoomPromotion(hotelId));
			//若预订数量满足房间预订促销策略
			if(numOfRoom >= vo.getNumOfRoom())
				return vo;
			
			return null;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override	
	public ResultMessage upBirthStrategy(String hotelId, BirthdayProVo vo){
		//检查输入有效性
		if(vo.getDiscount() <= 0 && vo.getDiscount() > 1)
			return ResultMessage.INVALID;
		
		try {
			return hotelPromotionDao.upBirthPromotion(hotelId, new BirthdayProPo(vo.getDiscount()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}

	@Override
	public BirthdayProVo getBirthStrategy(String hotelId, Date birthDay){
		//得到当天日期
		String today = getNow("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String bitrh_str = df.format(birthDay);
		
		//若当天为客户生日，则得到酒店生日促销策略
		if(today.equals(bitrh_str)){
			try {
				return new BirthdayProVo(hotelPromotionDao.getBirthPromotion(hotelId));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	
	
	
	
	
	public int getStrategyPrice(String hotelId, RoomType type, MemberVo vo){
	
		return 0;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 1.检验当前时间是否在某个时间区间内
	 * 2.检验开始时间是否小于结束时间
	 * @param start
	 * @param end
	 * @param now
	 * @return
	 */
	private boolean dateValid(String start, String end, String now){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH");
		
		try {
			Date dt1 = df.parse(start);
			Date dt2 = df.parse(end);
			
			//此时判断当前是否在某个时间区间内
			if(now != null){
				Date dt0 = df.parse(now);
				if(dt0.getTime() >= dt1.getTime() && dt0.getTime() <= dt2.getTime())
					return true;
				else   return false;
			}
			//此时判断开始区间与结束时间是否有效
			else{
				if(dt1.getTime() < dt2.getTime()) return true;
				else                              return false;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * 
	 * @param format  日期格式
	 * @return 当前时期
	 */
	private String getNow(String format){
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(new Date());
	}
}
