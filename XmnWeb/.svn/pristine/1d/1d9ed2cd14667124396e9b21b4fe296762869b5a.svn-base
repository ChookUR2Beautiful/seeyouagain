package com.xmniao.xmn.core.billmanagerment.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.billmanagerment.entity.Refund;
import com.xmniao.xmn.core.billmanagerment.service.RefundBillService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RefundBillPendingHistoryController
 * 
 * 类描述： 查询退款历史记录
 * 
 * 创建人： zhoudekun
 * 
 * 创建时间：2015年1月16日09时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="订单管理")
@Controller
@RequestMapping(value = "billmanagerment/refundBillPendingHistory")
public class RefundBillPendingHistoryController extends BaseController {
	
	@Autowired
	private RefundBillService refundBillService;
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "billmanagerment/refundBillPendingHistoryList";
	}

	/**
	 * 获取退款订单历史记录
	 * 
	 * @param list
	 * @return Object
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(Refund refund) {
		this.log.info("refundBillPendingController-->list refundbill=" + refund);
		return refundBillService.getRefundBillPendingHistoryList(refund);
	}
	/**
	 * 导出已退款订单列表
	 * @param bill
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(Refund refund, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		refund.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", refundBillService.getRefundBillPendingHistoryList(refund).getContent());
		doExport(request, response, "billmanagerment/refundedBill.xls", params);
	}

}
