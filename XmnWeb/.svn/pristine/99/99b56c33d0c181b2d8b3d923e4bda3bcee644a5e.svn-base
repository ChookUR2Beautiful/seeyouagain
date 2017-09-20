/**
 * 
 */
package com.xmniao.xmn.test.vstar;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.vstar.entity.TVstarEnroll;
import com.xmniao.xmn.core.vstar.entity.TVstarPlayerInfo;
import com.xmniao.xmn.core.vstar.entity.TVstarSellerInfo;
import com.xmniao.xmn.core.vstar.service.TVstarEnrollService;
import com.xmniao.xmn.core.vstar.service.TVstarPlayerInfoService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TVstarPlayerInfoServiceTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-4-24 下午4:04:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class TVstarPlayerInfoServiceTest {
	
	private ApplicationContext application;
	
	private TVstarPlayerInfoService starPlayerInfoService;
	
	private TVstarEnrollService vstarEnrollService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		starPlayerInfoService = application.getBean(TVstarPlayerInfoService.class);
		vstarEnrollService = application.getBean(TVstarEnrollService.class);
	}
	

//	@Test
	public void add(){
		TVstarPlayerInfo playerInfo = new TVstarPlayerInfo();
		playerInfo.setEnrollId(6);
		playerInfo.setNname("我就想改个名字");
		
		starPlayerInfoService.add(playerInfo );
	}
	
//	@Test
	public void getList(){
		TVstarPlayerInfo vstarPlayerInfo = new TVstarPlayerInfo();
		TVstarEnroll enroll = new TVstarEnroll();
		List<TVstarEnroll> enrollList = vstarEnrollService.getList(enroll);
		List<Integer> enrollIds= new ArrayList<Integer>();
		for(TVstarEnroll enrollBean:enrollList){
			Integer id = enrollBean.getId();
			enrollIds.add(id);
		}
		vstarPlayerInfo.setEnrollIds(enrollIds);
		List<TVstarPlayerInfo> list = starPlayerInfoService.getList(vstarPlayerInfo);
		for(TVstarPlayerInfo playerInfo:list){
			System.out.println(playerInfo.toString());
		}
		
	}
	
//	@Test
	public void getRankList(){
		TVstarPlayerInfo playerReq = new TVstarPlayerInfo();
		List<TVstarPlayerInfo> rankList = starPlayerInfoService.getRankList(playerReq);
		for(TVstarPlayerInfo playerInfo:rankList){
			System.out.println(playerInfo.toString());
		}
	}
	
//	@Test
	public void getRankCount(){
		TVstarPlayerInfo playerReq = new TVstarPlayerInfo();
		long rankCount = starPlayerInfoService.getRankCount(playerReq);
		System.out.println("参与排名选手数量:"+rankCount);
	}
	
//	@Test
	public void getSellerListByUid(){
		TVstarSellerInfo param= new TVstarSellerInfo();
		param.setUid(606674);
		List<TVstarSellerInfo> sellerList = starPlayerInfoService.getSellerListByUid(param);
		for(TVstarSellerInfo sellerInfo:sellerList){
			System.out.println(sellerInfo.toString());
		}
	}
	
//	@Test
	public void getSellerEarningsBySellerIdList(){
		Map<String, Object> param= new HashMap<String,Object>();
		List<Integer> sellerIdList=new ArrayList<Integer>();
		sellerIdList.add(33667);
		param.put("sellerIdList", sellerIdList);
		List<Map<String, Object>> sellerEarnings = starPlayerInfoService.getSellerEarningsBySellerIdList(param);
		for(Map<String, Object> earningsMap:sellerEarnings){
			Object money = earningsMap.get("money");
			JSONObject  commission= JSON.parseObject(earningsMap.get("commission").toString());
			BigDecimal mikeAmount = new BigDecimal(commission.get("mike_amount").toString());
			System.out.println("money="+money+",mikeAmount="+mikeAmount);
		}
	}
	
//	@Test
	public void getSignedSellerNum(){
		Map<String, Object> param= new HashMap<String,Object>();
		List<Integer> uidList = new ArrayList<Integer>();
		uidList.add(606674);
		param.put("uidList", uidList);
		List<Map<String,Object>> signedSellerNum = starPlayerInfoService.getSignedSellerNum(param);
		for(Map<String,Object> signedMap:signedSellerNum){
			System.out.println(signedMap.toString());
		}
	}
	
	@Test
	public void getVstarFansByUidList(){
		Map<String,Object> param=new HashMap<String,Object>();
		List<Integer> uidList=new ArrayList<Integer>();
		uidList.add(604991);
		param.put("uidList", uidList);
		List<Map<String, Object>> vstarFansList = starPlayerInfoService.getVstarFansByUidList(param);
		param.put("startTime", "2017-05-01");
		param.put("endTime", "2017-09-01");
		for(Map<String,Object> vstarMapInfo:vstarFansList){
			System.out.println(vstarMapInfo.toString());
		}
	}

}
