package com.xmniao.xmn.core.businessman.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.member.controller.MemberProvidedController;
import com.xmniao.xmn.core.member.entity.MemberProvodedRequest;
import com.xmniao.xmn.core.member.entity.ResponseContainer;
import com.xmniao.xmn.core.member.service.MemberProvidedService;
import com.xmniao.xmn.core.util.StringUtils;

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
@RequestMapping(value = "businessman/getMoneySeller")
public class GetMoneySellerController extends BaseController {
protected final Logger log = Logger.getLogger(MemberProvidedController.class);
	
	@Autowired
	private MemberProvidedService memberProvidedService;
	
	@Autowired SellerService sellerService;
	
	
	
	/**
	 * 初始化
	 * @return
	 */
	@RequestMapping("init")
	public ModelAndView init(){
		ModelAndView mv = new ModelAndView();
		mv.addObject("requestInit", "businessman/getMoneySeller/init/list");
		mv.setViewName("businessman/getMoneySellerList");
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
		int total = 0;
		mv.addObject("page", request.getPage());
		request.setUsertype("1");//商家提现类型
		try{
			 result = memberProvidedService.get(request);
			 if(null!=result){
				 total = result.getTotal();
			 }
			 this.log.info("返回值>>>>>"+result.getData());
		} catch (Exception e) {
			log.error("查询会员提现异常", e);
		}
		mv.addObject("total", total);
		mv.addObject("result", result);
		mv.setViewName("businessman/getMoneyTable");
		return mv;
	}
}
