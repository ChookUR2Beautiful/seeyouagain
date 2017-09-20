package com.xmniao.xmn.core.business_cooperation.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.member.controller.MemberProvidedController;
import com.xmniao.xmn.core.member.entity.MemberProvodedRequest;
import com.xmniao.xmn.core.member.entity.ResponseContainer;
import com.xmniao.xmn.core.member.service.MemberProvidedService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DisputeOrderController
 * 
 * 类描述： 提现商家信息
 * 
 * 创建人：chenerxin
 * 
 * 创建时间：2014年12月05日14时42分
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Controller
@RequestMapping(value = "business_cooperation/getMoneyJoint")
public class GetMoneyJointController extends BaseController {
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
		mv.addObject("requestInit", "business_cooperation/getMoneyJoint/init/list");
		mv.setViewName("business_cooperation/getMoneyJointList");
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
		int total=1;
		try{
			request.setUsertype("4");
			 result = memberProvidedService.get(request);
			 if(null!=result){
				 total = result.getTotal();
			 }
		} catch (Exception e) {
			log.error("查询会员提现异常", e);
		}
		mv.addObject("total", total);
		mv.addObject("result", result);
		mv.setViewName("business_cooperation/getMoneyJointTable");
		return mv;
	}
	/**
	 * @author dong'jietao 
	 * @date 2015年5月27日 下午2:34:47
	 * @TODO 合作商提现详细信息
	 * @param queryParmas
	 * @return
	 */
	@RequestMapping(value = "/businessDetails")
	public ModelAndView businessDetails(Long id){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("business_cooperation/businessDetails");
		memberProvidedService.jointWithdrawals(id,modelAndView);
		memberProvidedService.jointDetails(id,modelAndView);
		return modelAndView;
	}
	
}

