package com.xmn.saas.controller.h5.activity;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.xmn.saas.base.AbstractController;
import com.xmn.saas.controller.h5.activity.vo.SellerCouponDetailRequset;
import com.xmn.saas.entity.activity.AwardRelation;
import com.xmn.saas.entity.common.SellerAccount;
import com.xmn.saas.service.base.RedisService;
import com.xmn.saas.utils.CalendarUtil;

public class AbstractActiviryController extends AbstractController {
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
	
	public AwardRelation[] transAwardRelation(SellerCouponDetailRequset[] couponDetailRequset){
		if(null==couponDetailRequset){
			return null;
		}
		AwardRelation[] awardRelations = new AwardRelation[couponDetailRequset.length];
		for (int i=0;i<couponDetailRequset.length;i++) {
			AwardRelation awardRelation=new AwardRelation();
			awardRelation.setAwardType(couponDetailRequset[i].getCouponType());
			awardRelation.setAwardName(couponDetailRequset[i].getCouponName());
			awardRelation.setAmount(couponDetailRequset[i].getSendNum());
			awardRelation.setId(couponDetailRequset[i].getCid());
			awardRelation.setCouponEndDate(couponDetailRequset[i].getCouponEndDate());
			awardRelations[i]=awardRelation;
		}
		return awardRelations;
	}
	
	
	/**
	 * 
	 * 方法描述：添加日期出参
	 * 创建人：jianming  
	 * 创建时间：2016年10月13日 下午5:31:42   
	 * @param modelAndView
	 */
	public void SetDefineDate(ModelAndView modelAndView){
		Calendar calendar = Calendar.getInstance(); 
		Integer year=calendar.get(Calendar.YEAR);        //获取完整的年份(4位)
		Integer month=calendar.get(Calendar.MONDAY)+1;  //获取当前月份(1-12)
		Integer date= calendar.get(Calendar.DATE);      //获取当前日(1-31)
		String initTime=year+"年"+month+"月"+date+"日";
		String currentDate=month+"月"+date+"日";
		String defaultStartTime="00:00"; 
		String defaultEndTime="23:59"; 
		Date nowDate=new Date();
		modelAndView.addObject("initTime", initTime);
		modelAndView.addObject("currentDate", currentDate);
		modelAndView.addObject("defaultStartTime", defaultStartTime);
		modelAndView.addObject("defaultEndTime", defaultEndTime);
		modelAndView.addObject("year", year);	
		modelAndView.addObject("month", month);
		modelAndView.addObject("date", date);
		modelAndView.addObject("nowDate",nowDate);
		modelAndView.addObject("nowDateFont",year.toString()+"-"+month.toString()+"-"+date.toString());
		try {
			modelAndView.addObject("initFormat",CalendarUtil.dateFormat(new Date(),"yyyy.MM.dd"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
