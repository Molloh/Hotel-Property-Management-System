package businessLogic.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import businessLogic.service.HotelListService;
import common.RoomType;
import dataService.dao.service.HotelDao;
import po.HotelPo;
import rmi.RemoteHelper;
import vo.HotelTypeRoomVo;
import vo.HotelVo;

public class HotelListServiceImpl implements HotelListService{
	private List<HotelVo> hotelList = new ArrayList<HotelVo>();  //只通过地址和商圈筛选得到
	private HotelDao hotelDao;
	private List<String> provinceAndCityBusinessList;
	private static HotelListServiceImpl hotelListServiceImpl;
  
    public static HotelListServiceImpl getInstance(){
        if( hotelListServiceImpl == null)
        	hotelListServiceImpl = new HotelListServiceImpl() ;
        return hotelListServiceImpl;
    }

    private HotelListServiceImpl(){
        hotelDao = RemoteHelper.getInstance().getHotelDao();
        provinceAndCityBusinessList = getProvinceAndCityBusiness();
    }

	@Override
	public List<HotelVo> sortByName(List<HotelVo> list, boolean isRise) {
		if(isRise){
			list.sort((HotelVo h1,HotelVo h2) -> h1.getHotelName().compareTo(h2.getHotelName()));
        } else {
        	list.sort((HotelVo h1,HotelVo h2) -> h2.getHotelName().compareTo(h1.getHotelName()));
        }
		return list;
	}

	@Override
	public List<HotelVo> sortByStar(List<HotelVo> list, boolean isRise) {
		if(isRise){
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					int star1 = h1.getStars();
					int star2 = h2.getStars();
					if(star1 - star2 > 0) return 1;
					if(star1 - star2 < 0) return -1;
					else return 0;
				}
			});
		}else{
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					int star1 = h1.getStars();
					int star2 = h2.getStars();
					if(star2 - star1 > 0) return 1;
					if(star2 - star1 < 0) return -1;
					else return 0;
				}
			});
		}
		return list;
	}

	@Override
	public List<HotelVo> sortByPoint(List<HotelVo> list, boolean isRise) {
		if(isRise){
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					double p1 = h1.getPoStrings();
					double p2 = h2.getPoStrings();
					if(p1 - p2 > 0) return 1;
					if(p1 - p2 < 0) return -1;
					else return 0;
				}
			});
		}else{
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					double p1 = h1.getPoStrings();
					double p2 = h2.getPoStrings();
					if(p2 - p1 > 0) return 1;
					if(p2 - p1 < 0) return -1;
					else return 0;
				}
			});
		}
		return list;
	}

	@Override
	public List<HotelVo> sortByPrice(List<HotelVo> list, RoomType type, boolean isRise) {
		if(isRise){
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					int p1 = h1.getOriginPrice(type);
					int p2 = h2.getOriginPrice(type);
					if(p1 - p2 > 0) return 1;
					if(p1 - p2 < 0) return -1;
					else return 0;
				}
			});
		}else{
			Collections.sort(list, new Comparator<HotelVo>() {
				@Override
				public int compare(HotelVo h1, HotelVo h2) {
					int p1 = h1.getOriginPrice(type);
					int p2 = h2.getOriginPrice(type);
					if(p2 - p1 > 0) return 1;
					if(p2 - p1 < 0) return -1;
					else return 0;
				}
			});
		}
		return list;
	}

	@Override
	public List<HotelVo> findByKeyword(String key) {	
		List<HotelVo> list = new ArrayList<HotelVo>();
		try {
			Iterator<HotelPo> it = hotelDao.keyFind(key).iterator();
			while(it.hasNext()){
				HotelVo vo = new HotelVo(it.next());
				if(vo.showInfo().contains(key))
					list.add(vo);
			}
		
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public List<HotelVo> findByAddress(String province, String city, String business){
		hotelList = new ArrayList<HotelVo>();
		try{
			List<HotelPo> list = hotelDao.keyFind(province + " " + city + " " + business);
			Iterator<HotelPo> it = list.iterator();
			while(it.hasNext()){
				//po --> vo
				hotelList.add(new HotelVo(it.next()));
			}
		}catch(RemoteException e){
			e.printStackTrace();
		}
		return hotelList;
	}

	@Override
	public List<String> getProvinceList(){
		List<String> provinceList = new ArrayList<String>();
		Iterator<String> it = provinceAndCityBusinessList.iterator();
		
		while(it.hasNext()){
			String [] token = it.next().split("/");
			//避免省份重复
			if( !provinceList.contains(token[0]))
				provinceList.add(token[0]);
		}
		
		return provinceList;
	}
	
	@Override
	public List<String> getCityList(String province){
		List<String> cityList = new ArrayList<String>();
		Iterator<String> it = provinceAndCityBusinessList.iterator();
		
		while(it.hasNext()){
			String [] token = it.next().split("/");
			
			if(token[0].equals(province)){
				//取得该省份的市
				if( !cityList.contains(token[1]))
					 cityList.add(token[1]);
			}
			
		}
		return cityList;
	}
	
	@Override
	public List<String> getBusinessList(String city){
		List<String> businessList = new ArrayList<String>();

		Iterator<String> it = provinceAndCityBusinessList.iterator();

		while(it.hasNext()){
			String [] token = it.next().split("/");
			
			if(token[1].equals(city)){
				//取得该省份的市
				if( !businessList.contains(token[2]))
					businessList.add(token[2]);
			}
			
		}
		return businessList;
	}
	
	/**
	 * 提供所有酒店所在的省份及城市和商圈
	 * @return
	 */
	private List<String> getProvinceAndCityBusiness(){
		List<String> list = new ArrayList<String>();
		try {
			List<HotelPo> polist = hotelDao.keyFind("H");
			Iterator<HotelPo> it = polist.iterator();
			while(it.hasNext()){
				HotelPo po = it.next();
				String address = po.getProvince() + "/" + po.getHotelCity() + "/" + po.getInBusiness();
				list.add(address);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return list;
	}
		
	@Override
	public List<HotelVo> findByOriginalPrice(RoomType type, int low, int high, List<HotelVo> list) {
		List<HotelVo> findList = new ArrayList<HotelVo>();
	
		for(HotelVo hotel : list){
			List<HotelTypeRoomVo> typeRoomList = hotel.getTypeRoom();
			Iterator<HotelTypeRoomVo> it = typeRoomList.iterator();
			while(it.hasNext()){
				HotelTypeRoomVo vo = it.next();
				if(vo.getType().equals(type) && vo.getPrice()>=low && vo.getPrice()<=high)
					findList.add(hotel);
			}
			return findList;
		}
		return null;
	}
	
	@Override
	public List<HotelVo> findByPoint(double least, double max, List<HotelVo> list) {
		List<HotelVo> findList = new ArrayList<HotelVo>();
		for(HotelVo hotel : list){
			if(hotel.getPoStrings() >= least && hotel.getPoStrings() <= max){
				findList.add(hotel);
			}
		}
		return findList;
	}

	@Override
	public List<HotelVo> findByStars(int least, int max, List<HotelVo> list) {
		List<HotelVo> findList = new ArrayList<HotelVo>();
		for(HotelVo hotel : list){
			if(hotel.getStars() >= least && hotel.getStars() <= max){
				findList.add(hotel);
			}
		}
		return findList;
	}

	@Override
	public List<HotelVo> findByRoomType(RoomType type, List<HotelVo> list){
		List<HotelVo> findList = new ArrayList<HotelVo>();
		for(HotelVo hotel : list){
			List<HotelTypeRoomVo> typeRoomList = hotel.getTypeRoom();
			Iterator<HotelTypeRoomVo> it = typeRoomList.iterator();
			
			while(it.hasNext()){
				if(it.next().getType().equals(type))
					findList.add(hotel);
			}
		}
		return findList;
	}


}
