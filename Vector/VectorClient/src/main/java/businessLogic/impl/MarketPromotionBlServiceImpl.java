package businessLogic.impl;

import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import businessLogic.service.MarketPromotionBlService;
import common.ResultMessage;
import dataService.dao.service.MarketPromotionDao;
import po.ActivityPromotionPo;
import po.LevelPo;
import rmi.RemoteHelper;
import vo.ActivityPromotionVo;
import vo.LevelVo;

public class MarketPromotionBlServiceImpl implements MarketPromotionBlService{
	private MarketPromotionDao marketPromotionDao;
	private static MarketPromotionBlServiceImpl marketPromotionBlServiceImpl;
	
	public static MarketPromotionBlServiceImpl getInstance(){
		if(marketPromotionBlServiceImpl == null)
			marketPromotionBlServiceImpl = new MarketPromotionBlServiceImpl();
		return marketPromotionBlServiceImpl;
	}

	private MarketPromotionBlServiceImpl(){
		marketPromotionDao = RemoteHelper.getInstance().getMarketPromotionDao();
	}
	
	@Override
	public ResultMessage addActivityStrategy(ActivityPromotionVo vo){
		//输入的折扣非法，日期非法
		if(vo.getDiscount()<=0 || vo.getDiscount()>1
				|| (dateValid(vo.getStartDate(), vo.getEndDate(), null)== false))
			return ResultMessage.INVALID;
		
		try {
			List<String> activityList = marketPromotionDao.getActivity();
			Iterator<String> it = activityList.iterator();
			//检查促销活动名称有无重复
			while(it.hasNext()){
				String [] token = it.next().split("/");
				if(token[0].equals(vo.getPromotionName()))
					return ResultMessage.INVALID;     //名称重复，无法添加
			}	
			return marketPromotionDao.updateActivity(new ActivityPromotionPo(vo));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}
	
	@Override
	public ResultMessage upActivityStrategy(ActivityPromotionVo vo){
		//输入的折扣非法，日期非法
		if(vo.getDiscount()<=0 || vo.getDiscount()>1
				|| (dateValid(vo.getStartDate(), vo.getEndDate(), null)== false))	
			return ResultMessage.INVALID;
		
		try {
			List<String> activityList = marketPromotionDao.getActivity();
			Iterator<String> it = activityList.iterator();
			//检查有无该促销活动
			while(it.hasNext()){
				String [] token = it.next().split("/");
				if(token[0].equals(vo.getPromotionName()))
					return marketPromotionDao.updateActivity(new ActivityPromotionPo(vo));
			}	
			
			return ResultMessage.INVALID;    //不存在该活动
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return ResultMessage.FAIL;
	}
	
	@Override
	public ResultMessage delActivityStrategy(ActivityPromotionVo vo){
		try {
			List<String> activityList = marketPromotionDao.getActivity();
			Iterator<String> it = activityList.iterator();
			//检查有无该促销活动
			while(it.hasNext()){
				String [] token = it.next().split("/");
				if(token[0].equals(vo.getPromotionName()))
					return marketPromotionDao.deleteActivity(new ActivityPromotionPo(vo));
			}	
			
			return ResultMessage.INVALID;    //不存在该活动
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.FAIL;
	}
	
	@Override
	public List<ActivityPromotionVo> getCurrentActStrategy(){
		List<ActivityPromotionVo> list = new ArrayList<ActivityPromotionVo>();
		
		try {
			List<String> actStr = marketPromotionDao.getActivity();
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
	public ResultMessage updateLevelStrategy(List<LevelVo> list){
		Iterator<LevelVo> it = list.iterator();
		LevelVo vo1 = it.next();
		
		LevelPo po1 = new LevelPo(vo1.getLevel(), vo1.getCredit(), vo1.getDiscount());
		List<LevelPo> poList = new ArrayList<LevelPo>();
		poList.add(po1);
		
		while(it.hasNext()){	
			LevelVo vo2 = it.next();
			LevelPo po2 = new LevelPo(vo2.getLevel(), vo2.getCredit(), vo2.getDiscount());
			/*
		     * 检查折扣，信用值，等级有效性
		     * 信用值，等级必须递增
		     */
			if(vo1.getDiscount()<=0 || vo1.getDiscount()>1 || vo1.getCredit()<0 || vo1.getLevel()<=0)
				return ResultMessage.INVALID;
			if(vo2.getCredit() <= vo1.getCredit() || (vo2.getLevel() != (vo1.getLevel()+1)))
				return ResultMessage.INVALID;
			
			vo1 = vo2;
			
			poList.add(po2);
		}

		/*
		 * 当网站营销人员修改等级策略后，所有的客户等级必须随之变化
		 */
		try {
			return marketPromotionDao.updateLevelRule(poList);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return ResultMessage.FAIL;
	}
		
	@Override
	public LevelVo getLevelStrategy(int level){
		try {
			List<LevelPo> levelList = marketPromotionDao.getLevelList();
			Iterator<LevelPo> it = levelList.iterator();
			
			while(it.hasNext()){
				LevelPo po = it.next();
				if(po.getLevel() == level)
					return new LevelVo(po);
			}
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return null;
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
