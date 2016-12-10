package dataService.dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import common.ResultMessage;
import dataService.dao.service.MarketPromotionDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.MarketPromotionDataHelper;
import po.ActivityPromotionPo;
import po.LevelPo;
import po.MemberPo;
import vo.MemberVo;

public class MarketPromotionDaoImpl extends UnicastRemoteObject implements MarketPromotionDao{
	private static final long serialVersionUID = 1L;
	
	private DataFactory dataFactory;

	private MarketPromotionDataHelper marketPromotionDataHelper;
	
	private static MarketPromotionDaoImpl marketPromotionDaoImpl;
	
	public static MarketPromotionDaoImpl getInstance() throws RemoteException{
		if(marketPromotionDaoImpl == null)
			marketPromotionDaoImpl = new MarketPromotionDaoImpl();
		return marketPromotionDaoImpl;
	}
	
	private MarketPromotionDaoImpl() throws RemoteException {
		super();
		dataFactory = new DataFactoryImpl();
		marketPromotionDataHelper = dataFactory.getMarketPromotionDataHelper();
	}
	
	@Override
	public ResultMessage updateActivity(ActivityPromotionPo po){
		return marketPromotionDataHelper.updateActivity(po);
	}
	
	@Override
	public List<String> getActivity(){
		return marketPromotionDataHelper.getActivity();
	}
	
	@Override
	public ResultMessage updateLevelRule(List<LevelPo> list){
		updateMemberVIP(list, true);
		updateMemberVIP(list, false);
		return marketPromotionDataHelper.updateLevelRule(list);
	}

	@Override
	public ResultMessage deleteActivity(ActivityPromotionPo po){
		return marketPromotionDataHelper.deleteActivity(po);
	}

	@Override
	public List<LevelPo> getLevelList(){
		return marketPromotionDataHelper.getLevelList();
	}
	
	/**
	 * 网站营销人员更新等级策略时，同时调用此方法修改所有客户的等级
	 * @param list
	 */
	private void updateMemberVIP(List<LevelPo> list, boolean isMember){
		TreeMap<String,MemberPo> map = MemberDaoImpl.getInstance().getMap(isMember);
		Iterator<Map.Entry<String, MemberPo>> iterator = map.entrySet().iterator();
		
		while(iterator.hasNext()){
			Map.Entry<String, MemberPo> entry = iterator.next();
			MemberPo po = entry.getValue();
			
			//设置每个客户的等级
			Iterator<LevelPo> it = list.iterator();
			while(it.hasNext()){
				LevelPo level = it.next();
				int level_credit = level.getCredit();     //某个等级对应的信用值
				//检查在哪个等级区间内
				if(po.getCredit() >= level_credit){
					po.setVip(level.getLevel());
				}else{
					break;
				}
			}
			//更新数据层
			map.put(po.getId(), po);     
			MemberDaoImpl.getInstance().modifyInfo(new MemberVo(po));      //更新每个客户信息
		}
		MemberDaoImpl.getInstance().updateMap(map, isMember);                  //更新map
		
	}
	

}
