/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.BursEarningsRank;
import com.xmniao.xmn.core.live_anchor.service.BursEarningsRankService;
import com.xmniao.xmn.core.live_anchor.service.TLiveAnchorService;
import com.xmniao.xmn.core.xmnburs.service.BursService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveRecordAddBatchServiceTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-7 下午6:00:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class BursEarningsRankServiceTest {
	
	private ApplicationContext application;
	private BursEarningsRankService earningsRankService;
	
	private BursService bursService;
	
	private TLiveAnchorService anchorService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		earningsRankService = application.getBean(BursEarningsRankService.class);
		bursService = application.getBean(BursService.class);
		anchorService = application.getBean(TLiveAnchorService.class);
	}
	
//	@Test
	public void selectByBean(){
		BursEarningsRank erank=new BursEarningsRank();
		erank.setUid(606778l);
		erank.setRankSource(1);
		BursEarningsRank selectByBean = earningsRankService.selectByBean(erank);
		System.out.println(selectByBean);
	}
	
//	@Test
	public void testEarningsRankByUids(){
		Object[] uids={1140,1150};
		List<Map<String,Object>> list = bursService.getUrsEarningsRankByUids(uids);
		for(Map<String,Object> map:list){
			System.out.println(map.toString());
		}
	}
	
	
	@Test
	public void testLoadEarningsRankInfo(){
		BLiver liver = new BLiver();
		List<BLiver> liveMemberList = anchorService.getLiveMemberList(liver);
		for(BLiver liverBean:liveMemberList){
			System.out.println(liverBean.toStringSimple());
		}
	}

}
