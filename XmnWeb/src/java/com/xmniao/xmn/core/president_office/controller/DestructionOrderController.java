package com.xmniao.xmn.core.president_office.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.president_office.service.DisputeOrderService;

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

@Controller
@RequestMapping(value = "president_office/destructionOrder")
public class DestructionOrderController extends BaseController {
	
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
		return "president_office/destructionOrderList";
	}
	
	/**
	 * 
	 * list(列表数据初始化)
	 * @throws Exception 
	 * @author：zhou'sheng
	 */
	@ResponseBody
	@RequestMapping(value = "init/list")
	public Object list(@RequestBody Map<String, String> queryParmas, HttpServletRequest request) throws Exception {
		return disputeOrderService.queryOrder(queryParmas);
	}
	
}
