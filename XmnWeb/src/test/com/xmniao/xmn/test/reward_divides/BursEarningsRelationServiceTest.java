/**
 * 
 */
package com.xmniao.xmn.test.reward_divides;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.reward_dividends.constant.RewardDividendsConstant;
import com.xmniao.xmn.core.reward_dividends.entity.BursEarningsRelation;
import com.xmniao.xmn.core.reward_dividends.service.BursEarningsRelationService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BursEarningsRelationServiceTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-7 下午6:00:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class BursEarningsRelationServiceTest {
	
	private ApplicationContext application;
	private BursEarningsRelationService earningsRelationService;
	
	
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		earningsRelationService = application.getBean(BursEarningsRelationService.class);
	}
	
//	@Test
	public void getObjectById(){
		long id=654;
		BursEarningsRelation bursEarningsRelation = earningsRelationService.getObjectById(id);
		System.out.println(bursEarningsRelation);
	}
	
//	@Test
	public void getList(){
		BursEarningsRelation relation=new BursEarningsRelation();
		List<BursEarningsRelation> list = earningsRelationService.getList(relation);
		for(BursEarningsRelation relationBean:list){
			System.out.println(relationBean.toString());
		}
	}
	
//	@Test
	public void getJuniorList(){
		BursEarningsRelation bursRelationInfo = new BursEarningsRelation();
		Integer uid=604898;
		bursRelationInfo.setUid(uid);
		bursRelationInfo.setObjectOriented(RewardDividendsConstant.OBJECT_ORIENTED.V_KE);
		List<BursEarningsRelation> juniorList = earningsRelationService.getJuniorList(bursRelationInfo);
		for(BursEarningsRelation juniorInfo:juniorList){
			System.out.println(juniorInfo.toString());
		}
		
		System.out.println(juniorList.size());
	}
	
	@Test
	public void getBaseList(){
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("objectOriented", RewardDividendsConstant.OBJECT_ORIENTED.V_KE);
		List<String> uids=new ArrayList<String>();
		//00000593820,00000606735,00000606736,00000604898
		uids.add("593820");
		uids.add("606735");
		uids.add("606736");
		uids.add("604898");
		paramMap.put("uids", uids);
		List<BursEarningsRelation> baseList = earningsRelationService.getBaseList(paramMap);
		for(BursEarningsRelation relationInfo:baseList){
			System.out.println(relationInfo.toString());
		}
	}
	

}
