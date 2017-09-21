/**    
 * 文件名：VerTeamServiceImpl.java    
 *    
 * 版本信息：    
 * 日期：2017年4月20日    
 * Copyright (c) 广东寻蜜鸟网络技术有限公司  2017     
 * 版权所有    
 *    
 */
package com.xmniao.service.live;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.MapUtil;
import com.xmniao.dao.live.LiveOrderDao;
import com.xmniao.thrift.busine.common.FailureException;
import com.xmniao.thrift.busine.common.ResponseData;
import com.xmniao.thrift.busine.live.VerTeamService;
import com.xmniao.urs.dao.LiverDao;
import com.xmniao.urs.dao.UrsEarningsRelationDao;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：VerTeamServiceImpl
 * 
 * 类描述： 
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2017年4月20日 下午3:41:06 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service("verTeamServiceImpl")
public class VerTeamServiceImpl implements VerTeamService.Iface{

	// 初始化日志类
	private final Logger log = Logger.getLogger(VerTeamServiceImpl.class);
	
	@Autowired
	private LiveOrderDao liveOrderDao;
	
	@Autowired
	private LiverDao liverDao;
	
	@Autowired
	private UrsEarningsRelationDao ursRelationDao;
	
	/**
	 * 绑定V客间接团队(上级)关系
	 */
	@Override
	public ResponseData bindIndirectTeam(Map<String, String> paramMap)
			throws FailureException, TException {
		Integer uid=Integer.parseInt(paramMap.get("uid"));
		Integer indirectUid=Integer.parseInt(paramMap.get("indirectUid"));
		log.info("给【"+uid+"】绑定间接上级【"+indirectUid+"】");
		return new ResponseData(1, "空接口，暂未实现", null);
	}

	/**
	 * 获取V客的团队业绩
	 */
	@Override
	public ResponseData getTeamPerformance(Map<String, String> paramMap)
			throws FailureException, TException {
		Integer uid=Integer.parseInt(paramMap.get("uid"));
		Integer teamType = paramMap.get("teamType")==null?0:Integer.parseInt(paramMap.get("teamType"));

		log.info("给【"+uid+"】计算其"+this.getTeamName(teamType)+"团队业绩");
		Map<String,Object> performanceMap = new HashMap<>();
		
		try{
			if(teamType==0 || teamType==1){
				performanceMap.putAll(this.getDirectPerformance(paramMap));	
			}
			if(teamType==0 || teamType==2){
				performanceMap.putAll(this.getIndirectPerformance(paramMap));	
			}
			BigDecimal allPerformance = 
					(performanceMap.get("directPerformance")==null?BigDecimal.ZERO:(BigDecimal)performanceMap.get("directPerformance"))
					.add(performanceMap.get("indirectPerformance")==null?BigDecimal.ZERO:(BigDecimal)performanceMap.get("indirectPerformance"));
			performanceMap.put("allPerformance", allPerformance);
			return new ResponseData(0, "成功", MapUtil.formatMapStr(performanceMap));
		}catch (Exception e){
			log.info("统计异常：",e);
			return new ResponseData(1, "查询异常", null);
		}
	}

	private String getTeamName(int teamType){
		String tempName = null;
		switch (teamType) {
		case 0:
			tempName="所有";
			break;
		case 1:
			tempName="直接";
			break;
		case 2:
			tempName="间接";
			break;
		default:
			tempName="未知";
			break;
		}
		return tempName;
	}
	
	/*
	 * 获取用户的直属团队业绩
	 */
	private Map<String,Object> getDirectPerformance(Map<String,String> paramMap){

		return liveOrderDao.getVerDirectPerformance(paramMap);
	}
	

	
	/*
	 * 获取用户的间接挂靠团队业绩
	 */
	private Map<String,Object> getIndirectPerformance(Map<String,String> paramMap){
		//1.查看该用户下的所有间接下级
		Integer uid = Integer.parseInt(paramMap.get("uid"));
		List<String> uidList = ursRelationDao.getVerIndirectSubordinateList(uid);
		BigDecimal  indirectPerformance=BigDecimal.ZERO;
		Long indirectCount=0L;
		Map<String,Object> map = new HashMap<>();
		StringBuffer sb = new StringBuffer();
		//2.查看其所有间接下级的直属团队业绩
		for(int i=0;i<uidList.size();i++){
			String subordinateUid=uidList.get(i);
			sb.append(subordinateUid);
			if((i+1)<uidList.size()){
				sb.append("|");
			}
		}
		
		if(uidList.size()>0){
			paramMap.put("uidStr", sb.toString());
			map = liveOrderDao.getVerIndirectPerformance(paramMap);
			indirectPerformance = indirectPerformance.add((BigDecimal)map.get("directPerformance"));
			indirectCount = indirectCount+(Long)map.get("directCount");
		}

		map.clear();
		map.put("indirectPerformance", indirectPerformance);
		map.put("indirectCount", indirectCount);
		return map;
	}
}
