package com.xmniao.xmn.core.userData_statistics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.userData_statistics.entity.TUserTransactionFlow;
import com.xmniao.xmn.core.userData_statistics.service.UserTransactionFlowService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;


@Controller
@RequestMapping(value = "userData_statistics/userTransactionFlow")
public class UserTransactionFlowController extends BaseController{
	
	@Autowired
	private UserTransactionFlowService flowService;
	
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView  mv = new ModelAndView();
		mv.addObject("requestInit", "userData_statistics/userTransactionFlow/init/list");
		mv.setViewName("userData_statistics/userTransactionFlow/userTransactionFlowList");
		return mv;
	} 

	/**
	 * 统计列表
	 * @param businessAction
	 * @return
	 */
	@RequestMapping("init/list")
	public ModelAndView getList(TUserTransactionFlow transactionFlow){
		ModelAndView  mv = new ModelAndView();
		Pageable<TUserTransactionFlow> pageable = new Pageable<TUserTransactionFlow>(transactionFlow);
		TUserTransactionFlow userTransactionFlow= null;
		 try{
			 userTransactionFlow =flowService.getCensusTotal(transactionFlow);
			 pageable.setContent(flowService.getList(transactionFlow));
			 pageable.setTotal(flowService.count(transactionFlow));
		 }catch(Exception e){
			log.error("查询用户交易流水统计列表页面异常", e);
		 }
		mv.addObject("total", userTransactionFlow);
		mv.addObject("pageable", pageable);
		mv.setViewName("userData_statistics/userTransactionFlow/userTransactionFlowTable");
		return mv;
	}
	
}
