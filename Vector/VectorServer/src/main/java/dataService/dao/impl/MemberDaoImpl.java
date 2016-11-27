package dataService.dao.impl;


import java.util.Iterator;
import java.util.Map;

import common.ResultMessage;
import dataService.dao.service.MemberDao;
import dataService.dataHelper.impl.DataFactoryImpl;
import dataService.dataHelper.service.DataFactory;
import dataService.dataHelper.service.MemberDataHelper;
import po.MemberPo;
import vo.MemberVo;

/**
 * Created by Administrator on 2016-11-27.
 */

public class MemberDaoImpl implements MemberDao {

    private Map<String,MemberPo> map;

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
        if(map == null){
            dataFactory = new DataFactoryImpl();
            memberDataHelper = dataFactory.getMemberDataHelper();
            map = memberDataHelper.getMemberData();
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
    	// amount<0?
        Iterator<Map.Entry<String, MemberPo>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String,MemberPo> entry = iterator.next();
            if( entry.getKey().equals(id) ) {
                MemberPo po = entry.getValue();
                po.setCredit(po.getCredit()+amount);
                update(po);
                return ResultMessage.SUCCEED;
            }

        }
        return ResultMessage.FAIL;
    }

    public MemberVo getInfo(String id){
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
        Iterator<Map.Entry<String, MemberPo>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<String, MemberPo> entry = iterator.next();
            if ( entry.getKey().equals(vo.getId())) {
            	MemberPo po = new MemberPo(vo.getId(),vo.getName(),vo.getPhone(),
            			vo.getAddress(), vo.getSex(), vo.getCredit());
                update(po);
                return ResultMessage.SUCCEED;
            }
        }
        return ResultMessage.FAIL;
    }

    public ResultMessage insert(MemberPo po){
    	 if(!map.containsKey(po.getId())) {
             map.put(po.getId(), po);
             memberDataHelper.updateMemberData(map);
             return ResultMessage.SUCCEED;
         }
         else
             return ResultMessage.FAIL; //已存在
    }
    
    public ResultMessage update(MemberPo po){
    	String id = po.getId() ;
        if(map.containsKey(id))
        {
            map.put(id, po);
            memberDataHelper.updateMemberData(map);
            return ResultMessage.SUCCEED;
        }
        else
            return ResultMessage.FAIL;
    }
    
    public ResultMessage delete(String id){
    	  if(map.containsKey(id))
          {
              map.remove(id);
              memberDataHelper.updateMemberData(map);
              return ResultMessage.SUCCEED;
          }
          else
              return ResultMessage.FAIL;
    }
    
}
