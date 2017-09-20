package com.xmniao.xmn.core.president_office.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.ehcache.concurrent.Sync;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.president_office.service.DisputeOrderService;
import com.xmniao.xmn.core.president_office.util.PresidentConstants;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DisputeOrderController
 * 
 * 类描述： 争议订单
 * 
 * 创建人： zhengbo
 * 
 * 创建时间：2014年12月05日14时42分
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="总裁办")
@Controller
@RequestMapping(value = "president_office/disputeOrder")
public class DisputeOrderController extends BaseController {
	
	@Autowired
	private DisputeOrderService disputeOrderService;
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：zhou'sheng
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "president_office/disputeOrderList";
	}
	
	/**
	 * 
	 * list(列表数据初始化)
	 * @throws Exception 
	 * @author：zhou'sheng
	 */
	@ResponseBody
	@RequestMapping(value = "init/list")
	public Object list(@RequestBody Map<String, String> queryParmas) throws Exception {
			   return disputeOrderService.queryOrder(queryParmas);
	}
	
	/**
	 * 初始化提交状态页面
	 * @param queryParmas
	 * @return
	 * @throws TException
	 */
	@RequestMapping(value = "updateStatus/init")
	public String updateStatusInit() throws TException{
		return "president_office/disputeOrderUpdateStatus";
	}
	
	/**
	 * 
	 * 提交状态
	 * @throws Exception 
	 * @author：zhou'sheng
	 */
	@RequestLogging(name="争议订单处理")
	@ResponseBody
	@RequestMapping(value = "updateStatus")
	public Object updateStatus(@RequestBody Map<String, String> queryParams){
		Resultable result = null;
		String res =null;
		try {
			res=disputeOrderService.updateOrderState(queryParams);//调用接口
			Map<String,String> map = disputeOrderService.getMsg(res);//返回结果判断，数据拼装
			result = new Resultable(Integer.parseInt(map.get("flag")) == PresidentConstants.FLAG_1, map.get("msg"));//返回结果
			disputeOrderService.fireLoginEvent(new String[]{"争议订单", queryParams.get("id"), "更新状态", map.get("msg")},Integer.parseInt(map.get("flag")));//日志记录
		} catch (Exception e) {
			this.log.error("争议订单处理："+e);
			disputeOrderService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("争议订单处理", e, new Object[]{queryParams}).getMessage()),0);
		}
		return result;
	}

	
}
