package com.xmniao.xmn.core.business_statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.business_statistics.entity.BusinessAction;
import com.xmniao.xmn.core.business_statistics.service.BusinessActionService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：BusinessActionController
 * 
 * 类描述：商家行为统计控制器类
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-16 11:00:22
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */


@Controller
@RequestMapping("businessStatistics/businessAction")
public class BusinessActionController extends BaseController{
	
	
	@Autowired BusinessActionService businessActionService;
	
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView  mv = new ModelAndView();
		mv.addObject("requestInit", "businessStatistics/businessAction/init/list");
		mv.setViewName("business_statistics/businessAction/businessActionList");
		return mv;
	} 
	
	
	
	/**
	 * 统计列表
	 * @param businessAction
	 * @return
	 */
	@RequestMapping("init/list")
	public ModelAndView getList(BusinessAction businessAction){
		ModelAndView  mv = new ModelAndView();
		BusinessAction total =null;
		Pageable<BusinessAction> pageable = new Pageable<BusinessAction>(businessAction);
		 try{
			 total =businessActionService.getCensusTotal(businessAction);
			 pageable.setContent(businessActionService.getList(businessAction));
			 pageable.setTotal(businessActionService.count(businessAction));
		 }catch(Exception e){
			log.error("商家行为统计列表页面异常", e);
		 }
		 mv.addObject("total", total);
		mv.addObject("pageable", pageable);
		mv.setViewName("business_statistics/businessAction/businessActionTable");
		return mv;
	}

}
