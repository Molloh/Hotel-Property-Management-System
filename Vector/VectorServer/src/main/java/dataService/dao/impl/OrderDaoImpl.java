package dataService.dao.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import common.OrderCondition;
import common.ResultMessage;
import dataService.dao.service.OrderDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.OrderDataHelper;
import po.OrderPo;

public class OrderDaoImpl implements OrderDao {
	
	private Map<String, OrderPo> map;
	
	private OrderDataHelper orderDataHelper;
	
	private DataFactory dataFactory;
	
	private static OrderDaoImpl orderDataServiceImpl;
	
	public static OrderDaoImpl getInstance(){
		if(orderDataServiceImpl == null){
			orderDataServiceImpl = new OrderDaoImpl();
		}
		return orderDataServiceImpl;
	}
	
	public OrderDaoImpl(){
		if(map == null){
			dataFactory = new DataFactoryImpl();
			orderDataHelper = dataFactory.getOrderDataHelper();
			map = orderDataHelper.getOrderData();
		}
	}
	
	@Override
	public ResultMessage insert(OrderPo po) {
        if(!map.containsKey(po.getOrderId())) {
            map.put(po.getOrderId(), po); 
            orderDataHelper.updateOrderData(map);
            return ResultMessage.SUCCEED;
        }
        else
            return ResultMessage.FAIL;
	}

	@Override
	public OrderPo find(String orderId) {
		OrderPo orderPo = map.get(orderId);
		return orderPo;
	}

	@Override
	public ResultMessage update(OrderPo po) {
		String orderId = po.getOrderId();
		if(map.containsKey(orderId)) {
			map.put(orderId,po);
			orderDataHelper.updateOrderData(map);
			return ResultMessage.SUCCEED;
		}
		return ResultMessage.FAIL;
	}

	@Override
	public ResultMessage delete(String orderId) {
        if(map.containsKey(orderId)) {
            map.remove(orderId);
            orderDataHelper.updateOrderData(map);
            
            return ResultMessage.SUCCEED;
        }
        else
            return ResultMessage.FAIL;
    }

	@Override
	public List<OrderPo> getAllByHotel(String hotelId) {
		List<OrderPo> orderList = new ArrayList<OrderPo>();
		Iterator<Map.Entry<String, OrderPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, OrderPo> entry = iterator.next();
			OrderPo orderPo = entry.getValue();
			if(orderPo.getHotelId() == hotelId){
				orderList.add(orderPo);
			}
		}
		return orderList;
	}

	@Override
	public List<OrderPo> getAllByMember(String memberId) {
		List<OrderPo> orderList = new ArrayList<OrderPo>();
		Iterator<Map.Entry<String, OrderPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, OrderPo> entry = iterator.next();
			OrderPo orderPo = entry.getValue();
			if(orderPo.getMemberId() == memberId){
				orderList.add(orderPo);
			}
		}
		return orderList;
	}

	@Override
	public List<OrderPo> getNotExecuted(Date date) {
		List<OrderPo> orderList = new ArrayList<OrderPo>();
		Iterator<Map.Entry<String, OrderPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, OrderPo> entry = iterator.next();
			OrderPo orderPo = entry.getValue();
			if (orderPo.getCreateTime() == date && orderPo.getCondition() == OrderCondition.CANCELED) {
				orderList.add(orderPo);
			}
		}
		return orderList;
	}

	@Override
	public List<OrderPo> getAbnormal(Date date) {
		List<OrderPo> orderList = new ArrayList<OrderPo>();
		Iterator<Map.Entry<String, OrderPo>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Map.Entry<String, OrderPo> entry = iterator.next();
			OrderPo orderPo = entry.getValue();
			if (orderPo.getCreateTime() == date && orderPo.getCondition() == OrderCondition.ABNORMAL) {
				orderList.add(orderPo);
			}
		}
		return orderList;
	}
	

}