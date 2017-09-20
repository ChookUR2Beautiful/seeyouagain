/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.fresh.service.BillFreshService;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SaleController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年2月14日 下午6:16:38 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("fresh/sale")
public class SaleController extends BaseController {
	
	@Autowired
	private BillFreshService billFreshService;
	
	@RequestMapping("/init")
	public Object init(){
		ModelAndView modelAndView = new ModelAndView("fresh/saleList");
		Map<String,Object> todayOrder=billFreshService.getOrderByDay(DateUtil.formatDate(DateUtil.Y_M_D,new Date()));
		Map<String,Object> yesOrder=billFreshService.getOrderByDay(DateUtil.getSpecifiedDate(DateUtil.YESTERDAY));
		modelAndView.addObject("todayOrder",todayOrder);
		modelAndView.addObject("yesOrder",yesOrder);
		return modelAndView;
	}
	
	@RequestMapping("/orderCount")
	@ResponseBody
	public Object countOrderNum(){
		String specifiedDate = DateUtil.getSpecifiedDate(DateUtil.LAST_MONTY);
		List<Map<String,Object>> list=billFreshService.countOrderNum(specifiedDate);
		return list;
	}
	
	
	
}
