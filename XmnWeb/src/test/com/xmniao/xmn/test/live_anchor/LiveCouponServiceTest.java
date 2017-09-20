/**
 * 
 */
package com.xmniao.xmn.test.live_anchor;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xmniao.xmn.core.live_anchor.entity.TFansCouponIssueRef;
import com.xmniao.xmn.core.live_anchor.entity.TLiveCoupon;
import com.xmniao.xmn.core.live_anchor.service.TFansCouponIssueRefService;
import com.xmniao.xmn.core.live_anchor.service.TLiveCouponService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LivebroadcastServiceTest
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-24 下午3:24:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public class LiveCouponServiceTest {

	private ApplicationContext application;
	private TLiveCouponService couponService;
	private TFansCouponIssueRefService couponIssueRefService;
	
	@Before
	public void before() {
		application = new ClassPathXmlApplicationContext("classpath:/com/xmniao/xmn/resource/config/spring-context.xml");
		couponService = application.getBean(TLiveCouponService.class);
		couponIssueRefService = application.getBean(TFansCouponIssueRefService.class);
	}

//	@Test
	public void getList(){
		TLiveCoupon coupon= new TLiveCoupon();
		List<TLiveCoupon> list = couponService.getList(coupon);
		for(TLiveCoupon couponInfo:list){
			System.out.println(couponInfo.toString());
		}
	}
	
	@Test
	public void getVoucherList(){
		TLiveCoupon liveCoupon= new TLiveCoupon();
		liveCoupon.setCid(289);
		List<TFansCouponIssueRef> voucherList = couponIssueRefService.getVoucherList(liveCoupon);
		for(TFansCouponIssueRef voucherInfo:voucherList){
			System.out.println(voucherInfo.toString());
		}
	}
}
