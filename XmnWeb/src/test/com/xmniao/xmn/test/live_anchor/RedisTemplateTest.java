/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xmniao.xmn.core.base.MapBeanUtil;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGift;
import com.xmniao.xmn.core.live_anchor.service.TLiveGiftService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;
import com.xmniao.xmn.core.xmnburs.service.BursService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RedisTemplateTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-4-24 下午4:04:57 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class RedisTemplateTest {
	
	private ApplicationContext application;
	
	private BursService bursService;
	
	private TLiveGiftService liveGiftService;
	
	/**
	 * 注入redisTemplate
	 */
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		bursService = application.getBean(BursService.class);
		redisTemplate = application.getBean(StringRedisTemplate.class);
		liveGiftService = application.getBean(TLiveGiftService.class);
	}
	

//	@Test
	public void redisSet(){
		TLiveGift giftParam=new TLiveGift();
		giftParam.setStatus(1);
		giftParam.setLimit(-1);
		List<TLiveGift> list = liveGiftService.getBaseList(giftParam);
		System.out.println(JSONObject.toJSONString(list));
		String jsonString = JSONObject.toJSONString(list);
		List<TLiveGift> liveGiftList = JSONObject.parseArray(jsonString, TLiveGift.class);
		for(TLiveGift liveGift:liveGiftList){
			System.out.println(liveGift.toString());
		}
	}
	
	@Test
	public void jsonToMap(){
		String giftJson = redisTemplate.opsForValue().get("live_gift_detail_key$23");
		JSONObject parseObject = JSONObject.parseObject(giftJson);
		Map<String,Object> map=(Map<String,Object>) parseObject;
		System.out.println(map.toString());
	}

}
