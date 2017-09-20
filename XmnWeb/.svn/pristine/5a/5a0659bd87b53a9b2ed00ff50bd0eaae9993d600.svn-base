package com.xmniao.xmn.core.user_terminal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.common.service.AdvertisingService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.user_terminal.service.BannerAdvertisingService;
import com.xmniao.xmn.core.user_terminal.util.UserConstants;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BannerAdvertisingController
 * 
 * 类描述： 寻蜜鸟客户端活动banner
 * 
 * 创建人： dong'jt
 * 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="用户端管理")
@Controller
@RequestMapping(value="user_terminal/bannerAdvertising")
public class BannerAdvertisingController extends BaseController {
	
	@Autowired
	private BannerAdvertisingService bannerAdvertisingService;
	
	/**
	 * @author dong'jt
	 * 创建时间：2015年9月22日 上午11:22:27
	 * 描述：
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(){
		return "user_terminal/BannerAdvertisingList";
	}
	/** 
	 * @author dong'jt
	 * 创建时间：2015年9月22日 下午1:42:41
	 * 描述： 寻蜜鸟客户端活动banner列表获取
	 * @param advertising
	 * @return
	 */
	@RequestMapping(value="init/list")
	@ResponseBody
	public Object list(TAdvertising advertising){
		Pageable<TAdvertising> pageable = new Pageable<TAdvertising>(advertising);
		bannerAdvertisingService.putPageable(advertising, pageable);
		return pageable;	
	}
	/**
	 * @author dong'jt
	 * 创建时间：2015年9月22日 下午1:58:34
	 * 描述： 活动banner添加初始化页面
	 * @return
	 */
	@RequestMapping(value="/add/init")
	public ModelAndView addInit(){
		ModelAndView modelAndView = new ModelAndView("user_terminal/editBannerAdvertising");//修改返回页面为userClient/....
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
    /**
     * @author dong'jt
     * 创建时间：2015年9月23日 上午11:27:59
     * 描述：活动banner新增
     * @param advertising
     * @return
     */
	@SuppressWarnings("finally")
	@RequestLogging(name="活动banner新增")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TAdvertising advertising) {
		Resultable resultable = null;
		try {
			resultable=bannerAdvertisingService.addBanner(advertising);
		} catch (Exception e) {
			resultable = new Resultable(false, "新增异常！");
			this.log.error("活动banner新增："+e);
			bannerAdvertisingService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("活动banner新增", e, new Object[]{advertising}).getMessage()),0);
		} finally {
			return resultable;
		}
	}
    /**
     * @author dong'jt
     * 创建时间：2015年9月23日 上午11:26:48
     * 描述：     活动banner修改前初始化数据
     * @param request
     * @param id
     * @return
     */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("user_terminal/editBannerAdvertising");
		try {
			bannerAdvertisingService.updateBannerInitData(modelAndView,id);//更新前初始化数据
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} finally {
			return modelAndView;
		}
	}
	/** 
	 * @author dong'jt
	 * 创建时间：2015年9月22日 下午2:37:35
	 * 描述：活动banner修改
	 * @param advertising
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="修改banner")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TAdvertising  advertising) {
		Resultable resultable = null;
		try {
			resultable=bannerAdvertisingService.updateBannerData(advertising);
		} catch (Exception e) {
			resultable = new Resultable(false, "修改异常！");
			this.log.error("活动banner修改："+e);
			bannerAdvertisingService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("活动banner修改", e, new Object[]{advertising}).getMessage()),0);
		} finally {
			return resultable;
		}
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年9月30日 下午2:50:41
	 * 描述：修改banner为上线状态
	 * @param advertising
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="修改banner为上线状态")
	@RequestMapping(value = "/updateBannerStatusOnLine")
	@ResponseBody
	public Object updateBannerStatusOnLine(TAdvertising  advertising){
		Resultable resultable = null;
		try {
			resultable=bannerAdvertisingService.updateBannerStatus(advertising);
		} catch (Exception e) {
			resultable = new Resultable(false, "修改异常！");
			this.log.error("活动banner修改为上线状态："+e);
			bannerAdvertisingService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("活动banner修改为上线状态", e, new Object[]{advertising}).getMessage()),0);
		}
		return resultable;
	}
	/**
	 * 
	 * @author dong'jt
	 * 创建时间：2015年9月30日 下午2:50:50
	 * 描述：修改banner为下线状态
	 * @param advertising
	 * @return
	 */
	@SuppressWarnings("finally")
	@RequestLogging(name="修改banner为下线状态")
	@RequestMapping(value = "/updateBannerStatusOffLine")
	@ResponseBody
	public Object updateBannerStatusOffLine(TAdvertising  advertising){
		Resultable resultable = null;
		try {
			resultable=bannerAdvertisingService.updateBannerStatus(advertising);
		} catch (Exception e) {
			resultable = new Resultable(false, "修改异常！");
			this.log.error("活动banner为下线状态："+e);
			bannerAdvertisingService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("活动banner为下线状态", e, new Object[]{advertising}).getMessage()),0);
		}
		return resultable;
	}
}
