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
import dataService.dao.service.HotelPromotionDao;
import po.ActivityPromotionPo;
import po.BirthdayProPo;
import po.CompanyProPo;
import po.RoomPromotionPo;
import rmi.RemoteHelper;
import vo.ActivityPromotionVo;
import vo.BirthdayProVo;
import vo.CompanyProVo;
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
				|| vo.getStartDate().after(vo.getEndDate()))
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
				|| vo.getStartDate().after(vo.getEndDate()))	
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
	
	public List<Double> getCurrentActDiscount(String hotelId){
		List<ActivityPromotionVo> list = getCurrentActStrategy(hotelId);
		List<Double> discountList = new ArrayList<Double>();
		if(list.isEmpty())  return discountList;
		Iterator<ActivityPromotionVo> it = list.iterator();
		
		while(it.hasNext()){
			discountList.add(it.next().getDiscount());
		}
		return discountList;
	}
	
	@Override
	public List<ActivityPromotionVo> getCurrentActStrategy(String hotelId){
		List<ActivityPromotionVo> list = new ArrayList<ActivityPromotionVo>();
		
		try {
			List<String> actStr = hotelPromotionDao.getActProList(hotelId);
			Iterator<String> it = actStr.iterator();
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH");
			Date now = new Date();
			
			//得到当前时期有效的活动促销列表
			while(it.hasNext()){
				String [] token = it.next().split("/");
				/*
				 * 活动开始日期 token[1]
				 * 活动结束日期 token[2]
				 * 若当前在某次活动期间内
				 */
				try {
					Date start = df.parse(token[1]);
					Date end = df.parse(token[2]);
					
					if(now.before(end) && now.after(start)){
						ActivityPromotionVo vo = new ActivityPromotionVo(token[0], start, end,
							                   Double.parseDouble(token[3]));
						list.add(vo);
					}
				} catch (ParseException e) {
					e.printStackTrace();
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
	public double getCooperationStrategy(String hotelId, String memberId){
		try {
			CompanyProVo vo = new CompanyProVo(hotelPromotionDao.getCooperPro(hotelId));
			List<String> idList = vo.getCompanyList();
			
			//检查该酒店的合作企业是否有该企业
			if(idList.contains(memberId))
				return vo.getDiscount();
			
			return 1;
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return 1;
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
	
	
	public double getOrderRoomDiscount(String hotelId, int numOfRoom){
		RoomPromotionVo vo = getOrderRoomStrategy(hotelId, numOfRoom);
		if(vo != null)   return vo.getDiscount();
		return 1;
	}
	
	@Override
	public RoomPromotionVo getOrderRoomStrategy(String hotelId, int numOfRoom){
		try {
			RoomPromotionPo po = hotelPromotionDao.getRoomPromotion(hotelId);

			if(po != null){
				RoomPromotionVo vo = new RoomPromotionVo(po);
				//若预订数量满足房间预订促销策略
				if(numOfRoom >= vo.getNumOfRoom())
					return vo;
			}

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
	public double getBirthStrategy(String hotelId, Date birthDay){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String bitrh_str = df.format(birthDay);
		String today = df.format(new Date());
		//若当天为客户生日，则得到酒店生日促销策略
		if(today.equals(bitrh_str)){
			try {
				return hotelPromotionDao.getBirthPromotion(hotelId).getDiscount();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
	
}
