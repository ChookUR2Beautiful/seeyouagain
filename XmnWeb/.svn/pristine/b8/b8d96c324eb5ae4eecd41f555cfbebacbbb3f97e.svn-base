/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecord;
import com.xmniao.xmn.core.live_anchor.service.BLiveMemberService;
import com.xmniao.xmn.core.live_anchor.service.LivePageHomeService;
import com.xmniao.xmn.core.live_anchor.service.TLiveAnchorService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LivePageHomeService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-2-16 下午2:36:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class LivePageHomeServiceTest {
	
	private ApplicationContext application;
	private LivePageHomeService livePageHomeService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		livePageHomeService = application.getBean(LivePageHomeService.class);
	}
	
//	@Test
	public void updateRecommendInfo(){
		//3095
		TLiveRecord liveRecord=new TLiveRecord();
		liveRecord.setId(3095);
		liveRecord.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_YES);
		
		int count = livePageHomeService.updateRecommendedInfo(liveRecord);
		if(count>0){
			System.out.println("更新成功!");
		}
	}
	
	@Test
	public void getListTest(){
		TLiveRecord liveRecord=new TLiveRecord();
		liveRecord.setRecommended(LiveConstant.RECOMMENDED_TYPE.RECOMMENDED_YES);
		List<TLiveRecord> list = livePageHomeService.getList(liveRecord);
		for(TLiveRecord record:list){
			System.out.println(record.toString());
		}
	}

}
