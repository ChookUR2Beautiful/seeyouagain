package com.xmniao.xmn.core.businessman.controller;

import javax.annotation.Resource;
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
import com.xmniao.xmn.core.businessman.entity.TbankApply;
import com.xmniao.xmn.core.businessman.service.BankApplyService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：BankApplyService
 * 
 * 类描述： 商家银行卡修改申请
 * 
 * 创建人： zhou'dekun
 * 
 * 创建时间：2015年1月4日17时02分54秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="商家管理")
@Controller
@RequestMapping(value = "businessman/bankApply")
public class BankApplyController extends BaseController {
	
	@Autowired
	private BankApplyService  bankApplyService;
	
	@Resource(name = "synthesizeServiceClient")
	private ThriftClientProxy synthesizeServiceClient;
	
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "businessman/bankApplyList";
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TbankApply bankApply) {
		bankApply.setAccounttype(1);
		Pageable<TbankApply> pageable = new Pageable<TbankApply>(bankApply);
		pageable.setContent(bankApplyService.getList(bankApply));
		pageable.setTotal(bankApplyService.count(bankApply));
		return pageable;
	}
	
	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/updateNot/init")
	public ModelAndView updateNot(HttpServletRequest request, @RequestParam("appid") String appid) {
		ModelAndView modelAndView = new ModelAndView("businessman/editBankApply");
		//modelAndView.addObject("isType", "update");
		try {
			TbankApply bankApply = bankApplyService.getObject(new Long(appid));
			this.log.info(bankApply);
			modelAndView.addObject("bankApply", bankApply);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} 
		return modelAndView;
		
	}
	/**
	 * 
	 * seeSupplementaryInformation(查看补充资料)
	 * 
	 * @author：dong'jietao
	 */
	@RequestMapping(value = "/seeSupplementaryInformation")
	public ModelAndView seeSupplementaryInformation(HttpServletRequest request, @RequestParam("appid") String appid) {
		ModelAndView modelAndView = new ModelAndView("businessman/supplementaryInformation");
		try {
			TbankApply bankApply = bankApplyService.getSupplementaryInformation(new Long(appid));
			this.log.info(bankApply);
			modelAndView.addObject("bankApply", bankApply);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} 
		return modelAndView;
	}
	/**
	 * 
	 * update(不通过)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="银行卡审核不通过")
	@RequestMapping(value = "/updateNot")
	@ResponseBody
	public Object updateNot(TbankApply  bankApply) {
		Object resultable=null;
		try {
			resultable= bankApplyService.updateNot(bankApply);
		} catch (Exception e) {
			this.log.error("银行卡审核不通过异常："+e);
			bankApplyService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("银行卡审核不通过", e, new Object[]{bankApply}).getMessage()),0);
		}
		return resultable;
	}
	/**
	 * 
	 * update(新增&通过)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="银行卡审核通过")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TbankApply  bankApply) {
		Object resultable=null;
		try {
			resultable= bankApplyService.updateServer(bankApply);//逻辑优化，逻辑部分全部搬入Server
		} catch (Exception e) {
			this.log.error("银行卡审核通过异常："+e);
			bankApplyService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("银行卡审核通过", e, new Object[]{bankApply}).getMessage()),0);
		}
		return resultable;

	}
}
