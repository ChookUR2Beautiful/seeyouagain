package com.xmniao.xmn.core.businessman.controller;

import java.math.BigDecimal;
import java.util.Date;

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
import com.xmniao.xmn.core.businessman.entity.TAgioRecord;
import com.xmniao.xmn.core.businessman.entity.TSellerAgio;
import com.xmniao.xmn.core.businessman.service.AgioRecordService;
import com.xmniao.xmn.core.businessman.service.SellerAgioService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：SellerAccountController
 * 
 * 类描述： 商家折扣
 * 
 * 创建人：  chenerxin
 * 
 * 创建时间：2014年11月17日19时36分23秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="商家管理 >> 折扣设置")
@Controller
@RequestMapping(value = "businessman/sellerAgio")
public class SellerAgioController extends BaseController {

	@Autowired
	private SellerAgioService sellerAgioService;
	
	@Autowired
	private AgioRecordService agioRecordService;

	/**
	 * 
	 * init(初始化折扣信息)
	 * 
	 */
	@RequestMapping(value = "init")
	public ModelAndView init(ModelAndView model) {
		model.setViewName("businessman/sellerAgioList");
		return model;
	}

	/**
	 * 
	 * list(列表数据初始化)
	 * 
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(TSellerAgio sellerAgio) {
		Pageable<TSellerAgio> pageable = new Pageable<TSellerAgio>(sellerAgio);
		pageable.setContent(sellerAgioService.getList(sellerAgio));
		pageable.setTotal(sellerAgioService.count(sellerAgio));
		return pageable;
	}
	/**
	 * 
	 * addInit(添加初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/add/init")
	public ModelAndView addInit( @RequestParam("sellerid") Long sellerid) {
		ModelAndView modelAndView = new ModelAndView("businessman/editSellerAgios");
		if(null != sellerAgioService.getUsingCommonAgion(sellerid)){
			TSellerAgio sellerAgio = sellerAgioService.getUsingCommonAgion(sellerid);
			agioRecordService.sellerAgioInit(sellerAgio, modelAndView);
		}
		modelAndView.addObject("isType", "add");
		return modelAndView;
	}
	
	/**
	 * 
	 * add(添加)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="添加折扣")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(TAgioRecord sellerAgio) {
		Resultable resultable = null;
		try {
			resultable=agioRecordService.agioRecordAdd(sellerAgio);
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
			agioRecordService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("添加折扣", e, new Object[]{sellerAgio}).getMessage()),0);
		} 
		return resultable;
	}
	
	
	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "/update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("aid") Long aid) {
		ModelAndView modelAndView = new ModelAndView("businessman/editSellerAgios");
		modelAndView.addObject("isType", "update");
		try {
			TAgioRecord sellerAgio = agioRecordService.getObject(aid);
			sellerAgio.setBaseagio(new BigDecimal(sellerAgio.getBaseagio().toString()).multiply(new BigDecimal(100)).doubleValue());
			sellerAgio.setIncome(new BigDecimal(sellerAgio.getIncome().toString()).multiply(new BigDecimal(100)).doubleValue());
			sellerAgio.setSledger(new BigDecimal(sellerAgio.getSledger().toString()).multiply(new BigDecimal(100)).doubleValue());
			sellerAgio.setYledger(new BigDecimal(sellerAgio.getYledger().toString()).multiply(new BigDecimal(100)).doubleValue());
			sellerAgio.setPledger(new BigDecimal(sellerAgio.getPledger().toString()).multiply(new BigDecimal(100)).doubleValue());
			
			this.log.info(sellerAgio);
			modelAndView.addObject("sellerAgio", sellerAgio);
		} catch (NumberFormatException e) {
			this.log.error("修改初始异常", e);
		} 
		return modelAndView;
		
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="修改折扣")
	@RequestMapping(value = "/update")
	@ResponseBody
	public Object update(TAgioRecord sellerAgio) {
		Resultable resultable = null;
		try {
			sellerAgio.setSdate(new Date());
			sellerAgio.setBaseagio(new BigDecimal(sellerAgio.getBaseagio().toString()).divide(new BigDecimal(100)).doubleValue());
			sellerAgio.setIncome(new BigDecimal(sellerAgio.getIncome().toString()).divide(new BigDecimal(100)).doubleValue());
			sellerAgio.setSledger(new BigDecimal(sellerAgio.getSledger().toString()).divide(new BigDecimal(100)).doubleValue());
			sellerAgio.setYledger(new BigDecimal(sellerAgio.getYledger().toString()).divide(new BigDecimal(100)).doubleValue());
			sellerAgio.setPledger(new BigDecimal(sellerAgio.getPledger().toString()).divide(new BigDecimal(100)).doubleValue());
			agioRecordService.update(sellerAgio);	
			this.log.info("修改成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			return resultable;
		}
	}
	
	/**
	 * 
	 * delete(删除)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="折扣删除")
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object delete(HttpServletRequest request, @RequestParam("aid") String aid) {
		Resultable resultable = null;
		try {
			resultable=agioRecordService.AgioRecordDelete(aid);
		} catch (Exception e) {
			this.log.error("删除异常", e);
			resultable = new Resultable(false, "操作失败");
			agioRecordService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("删除折扣", e, new Object[]{request,aid}).getMessage()),0);
		} finally {
			return resultable;
		}
	}
}