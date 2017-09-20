/**
 * 
 */
package com.xmniao.xmn.test.vstar;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.vstar.constant.VstarConstant;
import com.xmniao.xmn.core.vstar.entity.TVstarRewardSend;
import com.xmniao.xmn.core.vstar.service.TVstarRewardSendService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TVstarRewardSendServiceTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-4-24 下午4:04:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class TVstarRewardSendServiceTest {
	
	private ApplicationContext application;
	
	private TVstarRewardSendService rewardSendService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		rewardSendService = application.getBean(TVstarRewardSendService.class);
	}
	
//	@Test
	public void add(){
		TVstarRewardSend reward = new TVstarRewardSend();
		reward.setUid(604643);
		reward.setNname("淘宝");
		reward.setPhone("19400000013");
		reward.setRewardType(VstarConstant.REWARD_TYPE.REWARD_TYPE_6);
		reward.setRewardDescription("888鸟币");
		reward.setCreateTime(new Date());
		reward.setUpdateTime(new Date());
		reward.setStatus(1);
		rewardSendService.add(reward);
	}
	
	@Test
	public void getList(){
		TVstarRewardSend vstarRewardSend = new TVstarRewardSend();
		List<TVstarRewardSend> list = rewardSendService.getList(vstarRewardSend);
		for(TVstarRewardSend record:list){
			System.out.println(record.toString());
		}
		
	}

}
