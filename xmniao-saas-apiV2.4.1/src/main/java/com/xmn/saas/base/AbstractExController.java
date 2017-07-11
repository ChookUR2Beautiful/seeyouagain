package com.xmn.saas.base;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.xmn.saas.controller.h5.activity.vo.SellerCouponDetailRequset;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;

public class AbstractExController extends AbstractController {
	/**
	 * 注入操作rids封装类
	 */
	@Autowired
	private RedisService redisService;
	
	
	/***
	 * 
	 * 方法描述：获得当前登录用户
	 * 创建人：jianming  
	 * 创建时间：2016年9月27日 下午2:55:29   
	 * @return
	 * @throws IOException
	 */
	public SellerAccount getSellerAccount() throws IOException{
		String token=this.getToken();
		if(StringUtils.isBlank(token)){
			token=this.getCookieToken();
		}
		SellerAccount sellerAccount = redisService.getSessionCacheObject(token, SellerAccount.class);
		if(null==sellerAccount){throw new RuntimeException("登陆用户信息异常");}
		Integer sellerid = sellerAccount.getSellerid();

		if (sellerid == null || sellerid == 0) {
			throw new RuntimeException("sessionToken错误，没有找到sellerid");
		}
		return sellerAccount;
	}
	
	/**
	 * 
	 * 方法描述：获得当前登录用户id
	 * 创建人：jianming  
	 * 创建时间：2016年9月27日 下午2:58:02   
	 * @return
	 * @throws IOException
	 */
	public Integer getSellerId() throws IOException{
		String token=this.getToken();
		if(StringUtils.isBlank(token)){
			token=this.getCookieToken();
		}
		SellerAccount sellerAccount = redisService.getSessionCacheObject(token, SellerAccount.class);
		
		if(null==sellerAccount){throw new RuntimeException("登陆用户信息异常");}
		Integer sellerid = sellerAccount.getSellerid();

		if (sellerid == null || sellerid == 0) {
			throw new RuntimeException("sessionToken错误，没有找到sellerid");
		}
		return sellerid;
	}
	
	
	
}
