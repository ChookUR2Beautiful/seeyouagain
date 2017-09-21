/**
 * 
 */
package com.xmniao.test.vstar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.xmniao.domain.vstar.TVstarPlayerInfo;
import com.xmniao.service.vstar.VstarPlayerInfoService;

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
public class VstarPlayerInfoServiceTest {
	
	private Logger log =Logger.getLogger(VstarPlayerInfoServiceTest.class);
	
	
	/**
	 * 注入选手信息统计Service
	 */
	@Autowired
    private VstarPlayerInfoService playerInfoService;
	
//	@Test
	public void playerInfoCountTest(){
		this.log.info("统计新时尚大赛选手信息...");
		TVstarPlayerInfo playerReq = new TVstarPlayerInfo();
		playerReq.setNodeTotal(1);
		playerReq.setCurrentNode(0);
		playerReq.setLimit(200);
		long starTime = System.currentTimeMillis();
//		playerInfoService.executeCountWeek(playerReq);
		playerInfoService.executeCountMonth(playerReq);
		long endTime = System.currentTimeMillis();
		this.log.info("统计新时尚大赛选手信息...,耗时(毫秒):"+(endTime-starTime));
	}
	
	/**
	 * 
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-13下午4:20:06 <br/>
	 */
//	@Test
	public void getDivisionList(){
		List<Map<String,Object>> divisionList = playerInfoService.getDivisionList();
		for(Map<String,Object> division:divisionList){
			System.out.println(division.toString());
		}
	}
	
	@Test
	public void executRank(){
		playerInfoService.executeRank();
		playerInfoService.executeRankWeek();
		playerInfoService.executeRankMonth();
	}
	
//	@Test
	public void getFansCountInitList(){
		Map<String, Object> paramMap = new HashMap<String,Object>();
		List<Integer> uidList = new ArrayList<Integer>();
		uidList.add(337540);
		paramMap.put("uidList", uidList);
		List<Map<String,Object>> fansCountInitList = playerInfoService.getFansCountInitList(paramMap);
		for(Map<String,Object> map:fansCountInitList){
			System.out.println(map.toString());
		}
	}
}
