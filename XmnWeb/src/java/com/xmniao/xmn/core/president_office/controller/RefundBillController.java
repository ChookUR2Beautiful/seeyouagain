package com.xmniao.xmn.core.president_office.controller;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.billmanagerment.entity.Refund;
import com.xmniao.xmn.core.billmanagerment.service.AllBillService;
import com.xmniao.xmn.core.billmanagerment.service.RefundBillService;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.president_office.util.PresidentConstants;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.memberAccountService.MemberAccountService;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RefundBillController
 * 
 * 类描述： 商户申诉订单
 * 
 * 创建人： huangpingqiang
 * 
 * 创建时间：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name = "总裁办订单申述管理")
@Controller("presidentOfficeRefund")
@RequestMapping(value = "president_office/refund")
public class RefundBillController extends BaseController {

	@Autowired
	private RefundBillService refundBillService;

	// 接口
	@Resource(name = "memberAccountService")
	private ThriftClientProxy memberAccountService;

	@Autowired
	private AllBillService allBillService;// 所有订单service

	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "president_office/refundBillList";
	}

	/**
	 * 获取订单列表
	 * 
	 * @param list
	 * @return Object
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(Refund refund) {
		return refundBillService.getRefundBillPresidentList(refund);
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();
		Refund refund = refundBillService.getObject(id);
		this.log.info(refund);
		modelAndView.addObject("refund", refund);
		modelAndView.setViewName("president_office/dealWithRefundBill");
		return modelAndView;
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * update by wangzhimin 2015/09/08 15:33:24
	 * 
	 */	
	@RequestLogging(name = "订单申诉")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(Refund refund) {
		boolean isSuccess = true;
		try{
			isSuccess = refundBillService.updateRefund(refund);
		}catch(Exception e){
			isSuccess = false;
			refundBillService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(), new ApplicationException("订单申诉处理异常", e, new Object[]{refund}).getMessage()), 0);
		}
		return recordLog(isSuccess, refund); 
	}
	
	/**
	 * 记录日志信息和返回处理结果
	 * @param flag
	 * @param refund
	 * @return
	 */
	private Resultable recordLog(boolean flag, Refund refund){
		String  result="商家申诉";
		if(refund.getStatus() == PresidentConstants.REFUND_STATUS_REJECT){
			result="驳回退款申请";
		} else if (refund.getStatus() == PresidentConstants.REFUND_STATUS_FAILURE) {
			result="同意退款";
		}
		String[] s={"订单编号",String.valueOf(refund.getBid()),"商家申诉",result};
		if(flag){
			refundBillService.fireLoginEvent(s, flag ? 1 : 0);
			return new Resultable(true, "处理成功");
		}else{
			return new Resultable(false, "处理失败");
		}
	}
}