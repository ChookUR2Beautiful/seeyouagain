package com.xmniao.xmn.core.business_statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.business_statistics.entity.TransactionFlow;
import com.xmniao.xmn.core.business_statistics.service.TransactionFlowService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：TransactionFlowController
 * 
 * 类描述：交易流水控制器类
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-16 11:00:22
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */


@Controller
@RequestMapping("businessStatistics/transactionFlow")
public class TransactionFlowController extends BaseController {
	@Autowired TransactionFlowService flowService;
	
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView  mv = new ModelAndView();
		mv.addObject("requestInit", "businessStatistics/transactionFlow/init/list");
		mv.setViewName("business_statistics/transactionFlow/transactionFlowList");
		return mv;
	} 

	/**
	 * 统计列表
	 * @param businessAction
	 * @return
	 */
	@RequestMapping("init/list")
	public ModelAndView getList(TransactionFlow transactionFlow){
		ModelAndView  mv = new ModelAndView();
		TransactionFlow total =null;
		Pageable<TransactionFlow> pageable = new Pageable<TransactionFlow>(transactionFlow);
		 try{
			 total =flowService.getCensusTotal(transactionFlow);
			 pageable.setContent(flowService.getList(transactionFlow));
			 pageable.setTotal(flowService.count(transactionFlow));
		 }catch(Exception e){
			log.error("查询商家交易流水统计列表页面异常", e);
		 }
		mv.addObject("total", total);
		mv.addObject("pageable", pageable);
		mv.setViewName("business_statistics/transactionFlow/transactionFlowTable");
		return mv;
	}
}
