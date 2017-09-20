/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.live_anchor.entity.BLiveFansRankDetail;
import com.xmniao.xmn.core.live_anchor.entity.BRankRedPacketDetail;
import com.xmniao.xmn.core.live_anchor.service.BLiveFansRankDetailService;
import com.xmniao.xmn.core.live_anchor.service.BRankRedPacketDetailService;

/**
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：RankRedPacketDetailServiceTest
 *
 * 类描述：粉丝级别返还模式服务测试类
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-6下午3:47:30
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public class RankRedPacketDetailServiceTest {
	private ApplicationContext application;
	private BRankRedPacketDetailService rankRestitutionService;
	private BLiveFansRankDetailService fansRankDetailService;

	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		rankRestitutionService = application.getBean(BRankRedPacketDetailService.class);
		fansRankDetailService = application.getBean(BLiveFansRankDetailService.class);
	}
	
	
//	@Test
	public void addTest(){
		BRankRedPacketDetail rankRestitution = new BRankRedPacketDetail();
		rankRestitution.setRankDetailId(10);
		rankRestitution.setDividendsRole(2);//分红角色 1内购 2外购
		rankRestitution.setConsumeLimitLowest(new BigDecimal(1));
		rankRestitution.setConsumeLimitHighest(new BigDecimal(100));
		rankRestitution.setCashLowest(new BigDecimal(2));
		rankRestitution.setCashHighest(new BigDecimal(4));
		rankRestitution.setCoinLowest(new BigDecimal(2));
		rankRestitution.setCoinHighest(new BigDecimal(4));
		rankRestitutionService.add(rankRestitution);
	}
	
	@Test
	public void getListTest(){
		BRankRedPacketDetail rankRestitution = new BRankRedPacketDetail();
		rankRestitution.setRankDetailId(10);
		List<BRankRedPacketDetail> list = rankRestitutionService.getList(rankRestitution);
		for(BRankRedPacketDetail restitution:list){
			System.out.println(restitution.toString());
		}
	}
	
//	@Test
	public void getFansRankDetailList(){
		BLiveFansRankDetail record=new BLiveFansRankDetail();
		List<BLiveFansRankDetail> list = fansRankDetailService.getList(record);
		for(BLiveFansRankDetail fansRankDetail:list){
			System.out.println(fansRankDetail.toString());
		}
	}
	
}
