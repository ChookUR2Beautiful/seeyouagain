/**
 * 
 */
package com.xmniao.xmn.test.vstar;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.vstar.constant.VstarConstant;
import com.xmniao.xmn.core.vstar.entity.TVstarEnroll;
import com.xmniao.xmn.core.vstar.entity.TVstarRewardConf;
import com.xmniao.xmn.core.vstar.entity.TVstarRewardRecord;
import com.xmniao.xmn.core.vstar.service.TVstarEnrollService;
import com.xmniao.xmn.core.vstar.service.TVstarRewardConfService;
import com.xmniao.xmn.core.vstar.service.TVstarRewardRecordService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：fTVstarRewardServiceTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-4-24 下午4:04:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class fTVstarRewardServiceTest {
	
	private ApplicationContext application;
	
	private TVstarRewardConfService vstarRewardConfService;
	
	private TVstarRewardRecordService rewardRecordService;
	
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		vstarRewardConfService = application.getBean(TVstarRewardConfService.class);
		rewardRecordService = application.getBean(TVstarRewardRecordService.class);
	}
	

//	@Test
	public void add(){
		TVstarRewardConf t=new TVstarRewardConf();
		t.setPilotTime(30);
		t.setRewardCoin(new BigDecimal(88));
		vstarRewardConfService.add(t);
	}
	
//	@Test
	public void getObject(){
		TVstarRewardConf list = vstarRewardConfService.getObject(1l);
		System.out.println(list);
	}
	
//	@Test
	public void addRecord(){
		TVstarRewardRecord t = new TVstarRewardRecord();
		t.setPlayerId(118);
		t.setReceiveCoin(new BigDecimal(88));
		rewardRecordService.add(t);
	}
	
	@Test
	public void getRecord(){
		TVstarRewardRecord record = rewardRecordService.getObject(1l);
		System.out.println(record);
	}


}
