package com.xmniao.xmn.core.marketingmanagement.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivity;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityApply;
import com.xmniao.xmn.core.marketingmanagement.service.ActivityApplyService;
import com.xmniao.xmn.core.marketingmanagement.service.TActivityService;
import com.xmniao.xmn.core.util.DateEditor;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ActivityApplyController
 * 
 * 类描述： 商家活动申请审核Controller
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年4月18日 下午3:45:43 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="活动申请")
@Controller
@RequestMapping(value = "marketingManagement/activityApply")
public class ActivityApplyController {
	
	protected final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private ActivityApplyService activityApplyService;
	
	@Autowired
	private TActivityService activityService;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateEditor());
	}
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public String init(){
		return "marketingManagement/activityApplyList";
	}
	
	/*
	 * 申请列表
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object getApplyList(TActivityApply tActivityApply){
		Pageable<TActivityApply> pageable = new Pageable<TActivityApply>(tActivityApply);
		pageable.setContent(activityApplyService.getList(tActivityApply));
		pageable.setTotal(activityApplyService.count(tActivityApply));
		return pageable;
	}
	
	/*
	 * 查看活动
	 */
	@RequestMapping(value = "init/activity")
	public ModelAndView commissionActivityDetails(HttpServletRequest request, @RequestParam("activityId") Long activityId) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/activityDetails");
		try {
			TActivity activity = activityService.getActivityAndRuls(activityId);
			modelAndView.addObject("activity", activity);
		} catch (NumberFormatException e) {
			this.log.error("查询活动明细异常", e);
		} 
		return modelAndView;
	}
	
	/*
	 * 查看并回复自定义活动
	 */
	@RequestMapping(value = "init/viewCustomizeApply")
	public ModelAndView viewCustomizeDetails(HttpServletRequest request, @RequestParam("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("marketingManagement/customizeApplyDetail");
		try {
			TActivityApply activityApply = activityApplyService.getActivityApply(id);
			modelAndView.addObject("activityApply", activityApply);
		} catch (NumberFormatException e) {
			this.log.error("查询活动明细异常", e);
		} 
		return modelAndView;
	}
	/*
	 * 回复商家定制活动建议
	 */
	@RequestMapping(value = "response")
	@ResponseBody
	public Object applyResponse(TActivityApply tActivityApply){
		return applyDeal(tActivityApply);
	}
	
	/*
	 * 申请审核操作
	 */
	@RequestMapping(value = "censor")
	@ResponseBody
	public Object applyCensor(TActivityApply tActivityApply){
		return applyDeal(tActivityApply);
	}
	
	/*
	 * 批量申请审核操作
	 */
//	@RequestMapping(value = "applyPassBatch")
//	@ResponseBody
//	public Object applyCensorBatch(TActivityApply tActivityApply){
//		return applyDeal(tActivityApply);
//	}
//	
//	@RequestMapping(value = "applyRefuse")
//	@ResponseBody
//	public Object applyRefuse(TActivityApply tActivityApply){
//		return applyDeal(tActivityApply);
//	}
//	
//	@RequestMapping(value = "applyRefuseBatch")
//	@ResponseBody
//	public Object applyRefuseBatch(TActivityApply tActivityApply){
//		return applyDeal(tActivityApply);
//	}
	
	private Object applyDeal(TActivityApply tActivityApply){
		Resultable resultable = null;
		try{
			activityApplyService.updateBatch(tActivityApply);
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		}
		return resultable;
	}
	/*
	 * 查看活动信息
	 */
	public Object getActivityInfo(){
		return null;
	}
}
