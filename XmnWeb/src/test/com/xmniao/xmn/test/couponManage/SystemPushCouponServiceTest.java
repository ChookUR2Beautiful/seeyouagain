/**
 * 
 */
package com.xmniao.xmn.test.couponManage;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.coupon.entity.SystemPushCoupon;
import com.xmniao.xmn.core.coupon.service.SystemPushCouponService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SystemPushCouponServiceTest
 * 
 * 类描述： 系统推送发放优惠券Service测试类
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-7 下午6:00:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class SystemPushCouponServiceTest {
	
	private ApplicationContext application;
	private SystemPushCouponService systemPushCouponService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		systemPushCouponService = application.getBean(SystemPushCouponService.class);
	}
	
	@Test
	public void saveSystemPushInfoTest(){
		SystemPushCoupon systemPushCoupon=new SystemPushCoupon();
		systemPushCoupon.setCtype(0);
		systemPushCoupon.setCid(17);//寻蜜鸟平台通用优惠劵10元
		systemPushCoupon.setSendNum(10);
		systemPushCoupon.setUserIds("606872: ;: ;11800000005: ;11800000005,606873: ;: ;11800000006: ;11800000006,");
		systemPushCouponService.saveSystemPushInfo(systemPushCoupon);
	}

}
