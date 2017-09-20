package com.xmniao.xmn.core.member.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.member.entity.MemberProvodedRequest;
import com.xmniao.xmn.core.member.entity.ResponseContainer;
import com.xmniao.xmn.core.member.service.MemberProvidedService;
//import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：PuserController
 * 
 * 类描述： 用户提现
 * 
 * 创建人： chen'heng
 * 
 * 创建时间：2014-12-10 14:56:05
 * 
 * Copyright (c) 深圳市XXX有限公司-版权所有
 */

@Controller
@RequestMapping("member/memberProvided")
public class MemberProvidedController {
	
	protected final Logger log = Logger.getLogger(MemberProvidedController.class);
	
	@Autowired
	private MemberProvidedService memberProvidedService;
	
	
	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("requestInit", "member/memberProvided/init/list");
		mv.setViewName("member/memberProvided/memberProvidedList");
		return mv;	
	}
	
	/**
	 * 查询
	 * @param puser
	 * @return
	 */
	@RequestMapping(value = "init/list")
	public ModelAndView list(MemberProvodedRequest request) {
		ModelAndView mv = new ModelAndView();
		ResponseContainer result = null;
		mv.addObject("page", request.getPage());
		int total=0;
		try{
			 result = memberProvidedService.get(request);
			 if(null!=result){
				 total = result.getTotal();
			 }
		} catch (Exception e) {
			log.error("查询会员提现异常", e);
		}
		mv.addObject("total", total);
		mv.addObject("result", result);
		mv.setViewName("member/memberProvided/memberProvidedTable");
		return mv;
	}
}
