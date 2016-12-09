package dataService.dao.impl;


import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import common.AccountType;
import common.ResultMessage;
import dataService.dao.service.MemberDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.MemberDataHelper;
import po.AccountPo;
import po.MemberPo;
import vo.MemberVo;

/**
 * Created by Administrator on 2016-11-27.
 */

public class MemberDaoImpl implements MemberDao {

    private TreeMap<String,MemberPo> map_member;
    private TreeMap<String,MemberPo> map_enterprise;
    
    private MemberDataHelper memberDataHelper;

    private DataFactory dataFactory;

    private static MemberDaoImpl memberDataServiceImpl;

    public static MemberDaoImpl getInstance(){
        if(memberDataServiceImpl == null){
            memberDataServiceImpl = new MemberDaoImpl();
        }

        return memberDataServiceImpl;
    }

    public MemberDaoImpl(){
        if(dataFactory==null){
            dataFactory = new DataFactoryImpl();
            memberDataHelper = dataFactory.getMemberDataHelper();
            this.map_member		= memberDataHelper.getMemberData(true);
            this.map_enterprise = memberDataHelper.getMemberData(false);
        }
    }


    public int getCredit(String id) {
    	MemberVo vo = getInfo(id);
    	if(!vo.equals(null))
    		return vo.getCredit();
    	else
    		return -1;
    }

    public ResultMessage chargeCredit(String id, int amount){
    	// amount<0?      id not exist?
    	TreeMap<String,MemberPo> map = getMap(isMember(id)) ;
    	
        Iterator<Map.Entry<String, MemberPo>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String,MemberPo> entry = iterator.next();
            if( entry.getKey().equals(id) ) {
                MemberPo po = entry.getValue();
                po.setCredit(po.getCredit()+amount);
                MemberVo vo = new MemberVo(po);
                updateInfo(vo);
                return ResultMessage.SUCCEED;
            }

        }
        return ResultMessage.FAIL;
    }

    public MemberVo getInfo(String id){
    	TreeMap<String,MemberPo> map = getMap(isMember(id)) ;
        Iterator<Map.Entry<String, MemberPo>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, MemberPo> entry = iterator.next();
            if (entry.getKey().equals(id)) {
                MemberPo po = entry.getValue();
                MemberVo vo = new MemberVo(po);
                return vo;
            }
        }
        return null;
    }

    public ResultMessage modifyInfo(MemberVo vo){
    	TreeMap<String,MemberPo> map = getMap(isMember(vo.getId())) ;
        Iterator<Map.Entry<String, MemberPo>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, MemberPo> entry = iterator.next();
            if ( entry.getKey().equals(vo.getId())) {
            	updateInfo(vo);
                return ResultMessage.SUCCEED;
            }
        }
        return ResultMessage.FAIL;
    }

    public ResultMessage insertInfo(MemberVo vo){
    	TreeMap<String,MemberPo> map = getMap(isMember(vo.getId())) ;
    	 if(!map.containsKey(vo.getId())) {
    		 MemberPo po = new MemberPo(vo.getId(),vo.getName(),vo.getPhone(),
    				 vo.getAddress(),vo.getSex(),vo.getCredit(),
    				 vo.getBirthday(),vo.getVip());
             map.put(vo.getId(), po);
             updateMap(map,isMember(vo.getId()));
             memberDataHelper.updateMemberData(map,isMember(vo.getId()));
             return ResultMessage.SUCCEED;
         }
         else
             return ResultMessage.FAIL; //已存在
    }
    
    public ResultMessage updateInfo(MemberVo vo){
    	String id = vo.getId() ;
    	TreeMap<String,MemberPo> map = getMap(isMember(id)) ;
    	if(map.containsKey(id))
        { 
    		MemberPo po = new MemberPo(vo.getId(),vo.getName(),vo.getPhone(),
				 vo.getAddress(),vo.getSex(),vo.getCredit(),
				 vo.getBirthday(),vo.getVip());
            map.put(id, po);
            updateMap(map,isMember(id));
            memberDataHelper.updateMemberData(map,isMember(id));
            return ResultMessage.SUCCEED;
        }
        else
            return ResultMessage.FAIL;
    }
    
    public ResultMessage deleteInfo(String id){
    	  TreeMap<String,MemberPo> map = getMap(isMember(id)) ;
    	  if(map.containsKey(id))
          {
              map.remove(id);
              updateMap(map,isMember(id));
              memberDataHelper.updateMemberData(map,isMember(id));
              return ResultMessage.SUCCEED;
          }
          else
              return ResultMessage.FAIL;
    }
    
    public TreeMap<String,MemberPo> getMap(boolean isMember){
    	TreeMap<String,MemberPo> map;
    	if(isMember)
    		map = this.map_member;
    	else 
    		map = this.map_enterprise;
    	return map;
    }
    
    public void updateMap(TreeMap<String,MemberPo> map,boolean isMember){
    	if(isMember)
    		this.map_member=map;
    	else
    		this.map_enterprise=map;
    }
    
    public boolean isMember(String id){
    	char label = id.charAt(0);
    	if(label=='N')
    		return true;
    	else
    		return false;
    }
}
