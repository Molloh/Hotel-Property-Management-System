package businessLogic.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import businessLogic.service.OrderBlService;
import common.OrderCondition;
import common.ResultMessage;
import common.RoomType;
import dataService.dao.service.MemberDao;
import dataService.dao.service.OrderDao;
import po.OrderPo;
import rmi.RemoteHelper;
import vo.OrderVo;

public class OrderBlServiceImpl implements OrderBlService {
	
	private OrderDao orderDao;
	private MemberDao memberDao;
	private static OrderBlServiceImpl orderBlServiceImpl;
	
    public static OrderBlServiceImpl getInstance(){
        if( orderBlServiceImpl == null)
            orderBlServiceImpl = new OrderBlServiceImpl() ;
        return orderBlServiceImpl;
    }
    
    private OrderBlServiceImpl(){
        orderDao = RemoteHelper.getInstance().getOrderDao();
        memberDao = RemoteHelper.getInstance().getMemberDao();
    }
	
	@Override
	public String getOrderId(String orderId) {
		return orderDao.findOrder(orderId).getOrderId();
	}
	
	@Override
	public OrderCondition getOrderCondition(String orderId) {
		return orderDao.findOrder(orderId).getCondition();
	}
	
	@Override
	public String getMemberId(String orderId) {
		return orderDao.findOrder(orderId).getMemberId();
	}
    
	@Override
	public String getMemberName(String orderId) {
		return orderDao.findOrder(orderId).getMemberName();
	}

	@Override
	public Date getCreateTime(String orderId) {
		return orderDao.findOrder(orderId).getCreateTime();
	}
	
	@Override
	public Date getCheckInTime(String orderId) {
		return orderDao.findOrder(orderId).getCheckInTime();
	}
	
	@Override
	public Date getCheckOutTime(String orderId) {
		return orderDao.findOrder(orderId).getCheckOutTime();
	}
	
	@Override
	public String getHotelName(String orderId) {
		return orderDao.findOrder(orderId).getHotel();
	}
	
	@Override
	public String getHotelId(String orderId) {
		return orderDao.findOrder(orderId).getHotelId();
	}
	
	@Override
	public RoomType getRoomType(String orderId) {
		return orderDao.findOrder(orderId).getRoomType();
	}
	
	@Override
	public int getNumOfRoom(String orderId) {
		return orderDao.findOrder(orderId).getNumOfRoom();
	}
	
	@Override
	public int getNumOfGuest(String orderId) {
		return orderDao.findOrder(orderId).getNumOfGuest();
	}
	
	@Override
	public boolean getChildExist(String orderId) {
		return orderDao.findOrder(orderId).getChildExist();
	}
	
	@Override
	public int getOriginalPrice(String orderId) {
		return orderDao.findOrder(orderId).getOriginalPrice();
	}
	
	@Override
	public double getDiscount(String orderId) {
		return orderDao.findOrder(orderId).getDiscount();
	}

	@Override
	public double getDiscountedPrice(String orderId) {
		return orderDao.findOrder(orderId).getDiscountedPrice();
	}


	@Override
	public List<OrderVo> getAllOrdersByHotel(String hotelId) {
		List<OrderVo> voList = new ArrayList<OrderVo>();
		List<OrderPo> poList = orderDao.getAllByHotel(hotelId);
		for(int i = 0; i < poList.size(); i++) {
			voList.add(new OrderVo(poList.get(i)));
		}
		return voList;
	}
	
	@Override
	public List<OrderVo> getAllOrdersByMember(String memberId) {
		List<OrderVo> voList = new ArrayList<OrderVo>();
		List<OrderPo> poList = orderDao.getAllByMember(memberId);
		for(int i = 0; i < poList.size(); i++) {
			voList.add(new OrderVo(poList.get(i)));
		}
		return voList;
	}

	@Override
	public List<OrderVo> getOrdersInConditionByHotel(String hotelId, OrderCondition condition) {
		List<OrderVo> allList = getAllOrdersByHotel(hotelId);
		List<OrderVo> conditionList = new ArrayList<OrderVo>();
		for(int i = 0; i < allList.size(); i++) {
			if(allList.get(i).getCondition() == condition) {
				conditionList.add(allList.get(i));
			}
		}
		return conditionList;
	}
	
	@Override
	public List<OrderVo> getOrdersInConditionByMember(String memberId, OrderCondition condition) {
		List<OrderVo> allList = getAllOrdersByMember(memberId);
		List<OrderVo> conditionList = new ArrayList<OrderVo>();
		for(int i = 0; i < allList.size(); i++) {
			if(allList.get(i).getCondition() == condition) {
				conditionList.add(allList.get(i));
			}
		}
		return conditionList;
	}
	
	@Override
	public List<OrderVo> getNotExecutedOrderByWebSite(Date date) {
		List<OrderVo> voList = new ArrayList<OrderVo>();
		List<OrderPo> poList = orderDao.getNotExecuted(date);
		for(int i = 0; i < poList.size(); i++) {
			voList.add(new OrderVo(poList.get(i)));
		}
		return voList;
	}
	
	@Override
	public List<OrderVo> getAbnormalByWebSite(Date date) {
		List<OrderVo> voList = new ArrayList<OrderVo>();
		List<OrderPo> poList = orderDao.getAbnormal(date);
		for(int i = 0; i < poList.size(); i++) {
			voList.add(new OrderVo(poList.get(i)));
		}
		return voList;
	}
	
	@Override
	public ResultMessage submit(String memberId, String planCheckInTime, String hoteId, int numOfDays,
			RoomType roomType, int numOfRoom, int numOfGuest, boolean childExist) {
		Date d = new Date();  
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");  
		String dateNowStr = sdf.format(d);
		Random rm = new Random();
		double pross = (1 + rm.nextDouble()) * Math.pow(10, 5);
		String randomStr = String.valueOf(pross).substring(1, 6);
		String orderId = dateNowStr + randomStr;
		
		Date createTime = new Date();
		//获取酒店此种房间单价
		//获取所有适用的打折策略并取最低的计算折扣
		//由单价，天数，折扣计算最终价格
		//由上面所有信息创建orderPo对象并存入数据层
		return null;
	}

	@Override
	public ResultMessage setToAbnormal(String orderId) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		orderPo.setCondition(OrderCondition.ABNORMAL);
		memberDao.chargeCredit(orderPo.getMemberId(), - orderPo.getDiscountedPrice());
		return orderDao.updateOrder(orderPo);
	}	

	@Override
	public ResultMessage cancel(String orderId) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		orderPo.setCondition(OrderCondition.CANCELED);
		return orderDao.updateOrder(orderPo);
	}
	
	@Override
	public ResultMessage checkIn(String orderId) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		orderPo.setCondition(OrderCondition.EXECUTING);
		orderPo.setCheckInTime(new Date());
		memberDao.chargeCredit(orderPo.getMemberId(), orderPo.getDiscountedPrice());
		return orderDao.updateOrder(orderPo);
	}

	@Override
	public ResultMessage abnormalCheckIn(String orderId) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		if(orderPo.getCondition() == OrderCondition.ABNORMAL) {
			orderPo.setCondition(OrderCondition.EXECUTING);
			orderPo.setCheckInTime(new Date());
			memberDao.chargeCredit(orderPo.getMemberId(), 2 * orderPo.getDiscountedPrice());
			return orderDao.updateOrder(orderPo);
		}
		else {
			return ResultMessage.FAIL;
		}
	}
	
	@Override
	public ResultMessage checkOut(String orderId) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		orderPo.setCondition(OrderCondition.EXECUTED);
		orderPo.setCheckOutTime(new Date());
		return orderDao.updateOrder(orderPo);
	}

	@Override
	public ResultMessage revoke(String orderId, double allOrHalf) {
		OrderPo orderPo = orderDao.findOrder(orderId);
		orderPo.setCondition(OrderCondition.REVOKED);
		orderPo.setRevokeTime(new Date());
		memberDao.chargeCredit(orderPo.getMemberId(), (int)allOrHalf * orderPo.getDiscountedPrice());
		return orderDao.updateOrder(orderPo);
	}
	
	@Override
	public ResultMessage delete(String orderId) {
		return orderDao.deleteOrder(orderId);
	}

}
