/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.Date;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.live_anchor.entity.TLiveBroadcast;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGivedGift;
import com.xmniao.xmn.core.live_anchor.quartz.TLiveReferrerQuartzService;
import com.xmniao.xmn.core.live_anchor.service.TLiveBroadcastService;
import com.xmniao.xmn.core.live_anchor.service.TLiveGivedGiftService;

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

public class LivebroadcastServiceTest {

	private ApplicationContext application;
	private TLiveBroadcastService broadcastService;
	private TLiveGivedGiftService liveGivedService;
	private TLiveReferrerQuartzService referrerQuartzService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		broadcastService = application.getBean(TLiveBroadcastService.class);
		liveGivedService = application.getBean(TLiveGivedGiftService.class);
		referrerQuartzService = application.getBean(TLiveReferrerQuartzService.class);
	}

//	@Test
	public void insert(){
		TLiveBroadcast broadcast = new TLiveBroadcast();
		broadcast.setContent("你知道我来到远方，不曾想过回到伤心的北方");
		broadcast.setCreateTime(new Date());
		broadcast.setUpdateTime(new Date());
		
		broadcast.setAssignRoom(1);
		broadcast.setRecordId(1088);
		
		broadcastService.add(broadcast);
		System.out.println("添加广播成功!");
	}
	
//	@Test
	public void generalCount(){
		TLiveGivedGift liveGivedGift=new TLiveGivedGift();
		Map<String, Object> generalCount = liveGivedService.generalCount(liveGivedGift);
		System.out.println(generalCount.toString());
	}
	
	@Test
	public void liveReferrerCensusByDay() throws Exception{
		referrerQuartzService.liveReferrerCensusByDay();
	}
}
