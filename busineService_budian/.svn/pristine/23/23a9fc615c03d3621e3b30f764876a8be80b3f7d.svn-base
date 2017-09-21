/**
 * 
 */
package com.xmniao.test.live;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmniao.domain.live.ResponseData;
import com.xmniao.domain.live.TVerExcitationReceive;
import com.xmniao.service.live.RechargeDividendsService;
import com.xmniao.util.PayIDGenerate;

/**
 * 
 * 项目名称：busineService
 * 
 * 类名称：RechargeDividendsServiceTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-5-26 下午3:55:37 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:conf/busine-base.xml" })
public class RechargeDividendsServiceTest {
	
	private Logger log =Logger.getLogger(RechargeDividendsServiceTest.class);
	
	@Autowired
	private RechargeDividendsService rechargeDividendsService;
	
//	@Test
	public void add(){
		TVerExcitationReceive record =new TVerExcitationReceive();
		
		record.setOrderNo(PayIDGenerate.createPayId());
		int count = rechargeDividendsService.add(record );
		System.out.println(count);
	}
	
	@Test
	public void saveReceiveB() throws Exception{
		Map<String, String> param = new HashMap<String,String>();
		param.put("orderNo", "051705081537405661");
		param.put("uid", "604809");
		param.put("projectName", "B");
		param.put("fansRankId", "31");
		ResponseData responseData = null;
		try {
			responseData = rechargeDividendsService.saveReceiveB(param);
		} catch (Exception e) {
			this.log.error("调用saveReceiveB方法异常:"+e);
			e.printStackTrace();
		}
		System.out.println(responseData);
	}
	
//	@Test
	public void getList(){
		
		TVerExcitationReceive record =new TVerExcitationReceive();
		record.setOrderNo("1705261600564389");
		List<TVerExcitationReceive> list = rechargeDividendsService.getList(record);
		System.out.println("============输出记录============== start");
		for(TVerExcitationReceive receive :list){
			System.out.println(receive.toString());
		}
		
		System.out.println("============输出记录============== end ");
	}
}
