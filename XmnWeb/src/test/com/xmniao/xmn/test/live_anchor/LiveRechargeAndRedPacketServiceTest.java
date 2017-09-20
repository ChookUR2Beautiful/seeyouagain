/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.live_anchor.entity.LiveRequestBean;
import com.xmniao.xmn.core.live_anchor.service.TLiveRechargeAndRedPacketService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveRechargeAndRedPacket
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-1-20 上午9:57:47 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class LiveRechargeAndRedPacketServiceTest {
	
	private ApplicationContext application;
	private TLiveRechargeAndRedPacketService rechargerAndRedPacketService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		rechargerAndRedPacketService = application.getBean(TLiveRechargeAndRedPacketService.class);
	}

//	@Test
	public void getRechargeInfo() {
		Map<String,Object> requestMap=new HashMap<String,Object>();
		Map<String, Object> rechargeInfo = rechargerAndRedPacketService.getRechargeInfo(requestMap);
		System.out.println("充值统计信息:"+rechargeInfo.toString());
	}
	
//	@Test
	public void getRedPacketInfo() {
		Map<String,Object> requestMap=new HashMap<String,Object>();
		Map<String, Object> rechargeInfo = rechargerAndRedPacketService.getRedPacketInfo(requestMap);
		System.out.println("红包统计信息:"+rechargeInfo.toString());
	}
	
	@Test
	public void getRechargeGroupByPayment() {
		LiveRequestBean requestBean=new LiveRequestBean();
		List<LiveRequestBean> rechargeGroupByPayment = rechargerAndRedPacketService.getRechargeGroupByPayment(requestBean);
		System.out.println("充值分组统计信息:"+rechargeGroupByPayment.toString());
	}

}
