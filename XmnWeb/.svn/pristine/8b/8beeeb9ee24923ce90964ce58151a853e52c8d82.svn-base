/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.live_anchor.entity.LiveAnchorAddBatchBean;
import com.xmniao.xmn.core.live_anchor.entity.LiveRecordAddBatchBean;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecord;
import com.xmniao.xmn.core.live_anchor.service.LiveRecordAddBatchService;
import com.xmniao.xmn.core.live_anchor.service.TLiveRecordService;

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

public class LiveRecordAddBatchServiceTest {
	
	private ApplicationContext application;
	private LiveRecordAddBatchService recordAddBathService;
	private TLiveRecordService liveRecordService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		recordAddBathService = application.getBean(LiveRecordAddBatchService.class);
		liveRecordService = application.getBean(TLiveRecordService.class);
	}
	
//	@Test
	public void addBatch(){
		LiveRecordAddBatchBean bean =new LiveRecordAddBatchBean();
		bean.setZhiboTitle("2017-03-07批量添加通告");
		bean.setLiveTopic(1);
		bean.setSellerid(31948);
		bean.setSellername("二当家3");
		bean.setSellerAlias("二当家3(别名)");
		bean.setZhiboAddress("凯乐会KTV");
		bean.setAdvanceDefault(1);
		bean.setPlanStartDate(new Date());
		bean.setPlanEndDate(new Date());
		bean.setIsAppoint(1);
		bean.setTelphones("12345689562");
		bean.setAnchorRoomPassword("123456");
		bean.setHaveCoupon("1");
		
		LiveAnchorAddBatchBean anchor1=new LiveAnchorAddBatchBean();
		anchor1.setAnchorId(446);
		anchor1.setSequenceNo(1);
		anchor1.setPicUrls("img/M00/01/D3/wKgyMld_SkGAceawAAAxqVBpBWk361.jpg");
		anchor1.setTagIds("1;2");
		
		List<LiveAnchorAddBatchBean> anchorList =new ArrayList<LiveAnchorAddBatchBean>();
		anchorList.add(anchor1);
		bean.setAnchorList(anchorList);
		
		recordAddBathService.addBatchInfo(bean);
	}
	
	@Test
	public void mergeRecord(){
		TLiveRecord liveRecord= new TLiveRecord();
		liveRecord.setIds("3597,3598");
		liveRecordService.mergeRecord(liveRecord);
	}

}
