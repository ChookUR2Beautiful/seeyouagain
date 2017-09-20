package com.xmniao.xmn.core.billmanagerment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.billmanagerment.entity.BillRecord;
import com.xmniao.xmn.core.billmanagerment.entity.Refund;
import com.xmniao.xmn.core.billmanagerment.service.BillRecordService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RefundBillHistoryController
 * 
 * 类描述： 查询所有订单
 * 
 * 创建人： huangpingqiang
 * 
 * 创建时间：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value = "billmanagerment/refundhistory")
public class RefundBillHistoryController extends BaseController {

	@Autowired
	private BillRecordService billRecordService;

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "billmanagerment/refundBillHistoryList";
	}

	/**
	 * 获取订单列表
	 * 
	 * @param list
	 * @return Object
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(BillRecord billRecord) {
		this.log.info("refundBillHistoryController-->list refundbillhistory=" + billRecord);
		Pageable<BillRecord> pageable = new Pageable<BillRecord>(billRecord);
		pageable = billRecordService.getRefundBillList(billRecord);
		return pageable;
	}

}