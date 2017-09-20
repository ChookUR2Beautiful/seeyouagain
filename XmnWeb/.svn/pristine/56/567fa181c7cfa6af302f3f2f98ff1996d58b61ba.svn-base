/**
 * 
 */
package com.xmniao.xmn.test.fresh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.fresh.entity.TFreshActivityAuction;
import com.xmniao.xmn.core.fresh.service.FreshActivityAuctionService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreshActivityAuctionServiceTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-2 上午9:35:47 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class FreshActivityAuctionServiceTest {
	
	private ApplicationContext application;
	private FreshActivityAuctionService auctionService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		auctionService = application.getBean(FreshActivityAuctionService.class);
	}
	
	@Test
	public void getList(){
		TFreshActivityAuction auction=new TFreshActivityAuction();
		List<TFreshActivityAuction> list = auctionService.getList(auction);
		for(TFreshActivityAuction auctionInfo:list){
			System.out.println(auctionInfo.toString());
		}
		
		Map<String,Object> parameter=new HashMap<String,Object>();
		List<Integer> activityIds=new ArrayList<Integer>();
		activityIds.add(1000);
		activityIds.add(1001);
		activityIds.add(1002);
		activityIds.add(1003);
		parameter.put("activityIds", activityIds);
		
		List<TFreshActivityAuction> auctionRecordList = auctionService.getAuctionRecordList(parameter);
		for(TFreshActivityAuction auctionRecord:auctionRecordList){
			System.out.println(auctionRecord.toString());
		}
	}

}
