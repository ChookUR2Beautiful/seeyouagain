/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.live_anchor.service.TLiveReferrerService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LivebroadcastServiceTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-24 下午3:24:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class LiveReferrerServiceTest {

	private ApplicationContext application;
	private TLiveReferrerService referrerService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		referrerService = application.getBean(TLiveReferrerService.class);
	}

	
//	@Test
	public void liveReferrerCensusByDay() throws Exception{
		Integer uidReq=604863;
		Map<String,Object> mapReq=new HashMap<String,Object>();
		mapReq.put("uid", uidReq);
		List<Map<String, Object>> listOfMap = referrerService.getJuniorUidsByUid(mapReq);
		List<String> juniorUids=new ArrayList<String>();
		for(Map<String,Object> mapInfo:listOfMap){
			Object uid = mapInfo.get("uid");
			juniorUids.add(uid.toString());
		}
		
		if(juniorUids!=null && juniorUids.size()>0){
			Map<String,Object> mapParameter=new HashMap<String,Object>();
			mapParameter.put("uids", juniorUids);
			mapParameter.put("startTime", "2017-01-05");
			Map<String, Object> juniorRechargeMap = referrerService.juniorRechargeCountByUids(mapParameter);
			Map<String, Object> juniorRewardMap = referrerService.juniorRewardCountByUids(mapParameter);
			Map<String, Object> juniorConsumeMap = referrerService.juniorConsumeCountByUids(mapParameter);
			System.out.println(juniorRechargeMap.get("recharge"));
			System.out.println(juniorRewardMap.get("reward"));
			System.out.println(juniorConsumeMap.get("consume"));
		}
		
	}
	
	@Test
	public void test(){
		String uid="337542";
		List<String> juniorUidList = referrerService.getJuniorUidList(uid);
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("uids", juniorUidList);
		Map<String, Object> amountCountMap = referrerService.juniorAmountCountByUids(map);
		System.out.println(amountCountMap.get("amountTotal"));
	}
}
