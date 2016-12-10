package dataService.dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import common.ResultMessage;
import dataService.dao.service.MarketPromotionDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.MarketPromotionDataHelper;
import po.ActivityPromotionPo;
import po.LevelPo;

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
	
	

}
