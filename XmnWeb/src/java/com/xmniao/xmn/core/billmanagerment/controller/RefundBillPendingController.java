package com.xmniao.xmn.core.billmanagerment.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
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
import com.xmniao.xmn.core.billmanagerment.util.BillConstants;
import com.xmniao.xmn.core.exception.ApplicationException;
import com.xmniao.xmn.core.thrift.service.payRefundService.RefundRequest;
import com.xmniao.xmn.core.util.StringUtils;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：RefundBillController
 * 
 * 类描述： 查询所有订单
 * 
 * 创建人： huangpingqiang
 * 
 * 创建时间：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name = "订单管理")
@Controller
@RequestMapping(value = "billmanagerment/refundBillPending")
public class RefundBillPendingController extends BaseController {

	@Autowired
	private RefundBillService refundBillService;

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
		return "billmanagerment/refundBillPendingList";
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
		this.log.info("refundBillPendingController-->list refundbill=" + refund);
		return refundBillService.getRefundBillPendingList(refund);
	}

	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "confirm/init")
	public ModelAndView confirmInit(HttpServletRequest request,
			@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Refund refund = refundBillService.getObject(new Long(id));
			this.log.info(refund);
			modelAndView.addObject("refund", refund);
			modelAndView.setViewName("billmanagerment/refundBillConfirm");
		} catch (NumberFormatException e) {
			this.log.error("页面初始异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 寻蜜鸟平台：收益支付 1000000 、优惠卷支付 1000011，其它的为第三方支付
	 * 
	 * update(修改)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestLogging(name = "订单退款")
	@SuppressWarnings("finally")
	@RequestMapping(value = "confirm")
	@ResponseBody
	public Object confirm(RefundRequest request, Refund refund) {
		Resultable resultable = null;
		request.setOrderNumber(refund.getBid().toString());// 订单号
		request.setMoney(refund.getMoney());// 退款总金额
		request.setRemark("");// 退款理由
		request.setBid(String.valueOf(refund.getId()));//退款订单id
		// 退款接口
		Map<String, String> res = new HashMap<String,String>();
		String msg="";
		String returnCode="";
		/*
		 * 支付宝测试代码，请不要删除！ String str=
		 * "{response=<html><head><meta charset='UTF-8'><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /></head><body><form id='alipaysubmit' name='alipaysubmit' action='https://mapi.alipay.com/gateway.do?_input_charset=utf-8' method='POST'><input type='hidden' name='sign' value='RR9y4K7xUmH2ZgD+LzH5zo2FGEApIu1BQQPZ+IPOB+blmgTHBq2P8qzTkQJC+EAt4jTg89xxHJPee36Q6lQo+Izddvdo2NS8xue7WnxB7l1H2/uvMyqO7bpp7MpMqV3DLuULEJkWeZVRWE5Nb9DRnwYLqUKKVr6/IViXRTXNahg='/><input type='hidden' name='sign_type' value='RSA'/><input type='hidden' name='detail_data' value='2015011403942470^1.00^'/><input type='hidden' name='service' value='refund_fastpay_by_platform_pwd'/><input type='hidden' name='notify_url' value='http://szdev.xmniao.com:8110/payService/aliRefoundNotify'/><input type='hidden' name='partner' value='2088511021380014'/><input type='hidden' name='seller_email' value='yangwh1214@hotmail.com'/><input type='hidden' name='batch_num' value='1'/><input type='hidden' name='batch_no' value='201501281852104715'/><input type='hidden' name='refund_date' value='2015-01-28 19:13:35'/></form><script>document.forms['alipaysubmit'].submit();</script></body></html>, Msg=, code=30010}"
		 * ; res=new HashMap(); res.put("Msg", "退款成功"); res.put("code",
		 * "30010"); res.put("response", str);
		 */
		//String response="<html><head><meta charset='UTF-8'><meta http-equiv='Content-Type' content='text/html; charset=UTF-8' /></head><body><form id='alipaysubmit' name='alipaysubmit' action='https://mapi.alipay.com/gateway.do?_input_charset=utf-8' method='POST'><input type='hidden' name='sign' value='RR9y4K7xUmH2ZgD+LzH5zo2FGEApIu1BQQPZ+IPOB+blmgTHBq2P8qzTkQJC+EAt4jTg89xxHJPee36Q6lQo+Izddvdo2NS8xue7WnxB7l1H2/uvMyqO7bpp7MpMqV3DLuULEJkWeZVRWE5Nb9DRnwYLqUKKVr6/IViXRTXNahg='/><input type='hidden' name='sign_type' value='RSA'/><input type='hidden' name='detail_data' value='2015011403942470^1.00^'/><input type='hidden' name='service' value='refund_fastpay_by_platform_pwd'/><input type='hidden' name='notify_url' value='http://szdev.xmniao.com:8110/payService/aliRefoundNotify'/><input type='hidden' name='partner' value='2088511021380014'/><input type='hidden' name='seller_email' value='yangwh1214@hotmail.com'/><input type='hidden' name='batch_num' value='1'/><input type='hidden' name='batch_no' value='201501281852104715'/><input type='hidden' name='refund_date' value='2015-01-28 19:13:35'/></form><script>document.forms['alipaysubmit'].submit();</script></body></html>"
		try {
			res = refundBillService.refundBillPendingConfirm(request);
			/*res.put("Msg", "属于重复退款");//代码模拟测试
			res.put("code", "30002");
			res.put("response", "测试");*/
			this.log.info(res.get("Msg"));
			this.log.info(res.get("code"));
			this.log.info(res.get("response"));
			returnCode=res.get("code");
			msg = res.get("Msg");
			if (BillConstants.return_code_30000.equals(returnCode)) {//钱包退款成功 （收益支付，融宝退款）
				updateRefundAli(refund);
				recordLog(getRecordLogArray(res,refund),1);
				resultable = new Resultable(true, msg,returnCode);
			} else if (BillConstants.return_code_30010.equals(returnCode)) {//构建第三方支付请求数据成功（ 支付 宝退款）
				update(refund,returnCode);
				recordLog(getRecordLogArray(res,refund),1);
				Map<String, Object> map = new HashMap<String, Object>(1);
				map.put("response", res.get("response"));
				resultable = new Resultable(true, msg, map);
			} else if (BillConstants.return_code_30019.equals(returnCode) || BillConstants.return_code_30011.equals(returnCode)) {//已提交退款请求，第三方平台还在退款处理中（除收益支付、融宝、支付宝外的其它方式）
				update(refund,returnCode);
				recordLog(getRecordLogArray(res,refund),1);
				resultable = new Resultable(true, msg, returnCode);
			} else if (BillConstants.return_code_30002.equals(returnCode)) {//第三方平台已退款成功，属于重复退款。直接更新状为退款成功
				update(refund,returnCode);
				recordLog(getRecordLogArray(res,refund),1);
				resultable = new Resultable(true, "退款成功！", returnCode);//msg应该为：“重复退款”
			} else {
				recordLog(getRecordLogArray(res,refund),0);
				resultable = new Resultable(true, msg,returnCode);
			}
		} catch (Exception e) {
			resultable = new Resultable(false, msg,returnCode);
			this.log.error("订单退款异常："+e);
			refundBillService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("订单退款", e, new Object[]{request,refund}).getMessage()),0);
		} finally {
			return resultable;
		}
	}
	private String[] getRecordLogArray(Map<String, String> res,Refund refund){
		String[] strArray=new String[4];
		String msg = res.get("Msg");
		String returnCode=res.get("code");
		String result = "退款";
		if (BillConstants.return_code_30000.equals(returnCode) || BillConstants.return_code_30002.equals(returnCode)) {
			if (msg.equals("")) {
				msg = "退款成功！";
			}
			result = "退款";
			strArray[0]="订单编号";
			strArray[1]=String.valueOf(refund.getBid());
			strArray[2]="退款";
			strArray[3]=result;
		} else if (BillConstants.return_code_30010.equals(returnCode)) {
			if (msg.equals("")) {
				msg = "退款处理中";
			}
			result = "退款处理中";
			strArray[0]="订单编号";
			strArray[1]=String.valueOf(refund.getBid());
			strArray[2]="退款";
			strArray[3]=result;
		} else if (BillConstants.return_code_30019.equals(returnCode) || BillConstants.return_code_30011.equals(returnCode)) {
			if (msg.equals("")) {
				msg = "第三方平台还在退款处理中";
			}
			result = "退款处理中";
			strArray[0]="订单编号";
			strArray[1]=String.valueOf(refund.getBid());
			strArray[2]="退款";
			strArray[3]=result;
		} else {
			strArray[0]="订单编号";
			strArray[1]=String.valueOf(refund.getBid());
			strArray[2]="退款";
			strArray[3]=result;
		}
		return strArray;
		
	}
	private void recordLog(String[] strArray,int flag){
		allBillService.fireLoginEvent(strArray, flag);
	}
	public void update(Refund refund,String returnCode){
		try {
			updateRefund(refund,returnCode);
			updateBill(refund,returnCode);
		} catch (Exception e) {
			this.log.error("退款数据更新：", e);
			throw new ApplicationException("退款数据更新",e,new Object[]{refund,returnCode},new String[]{"退款订单",refund.getBid().toString(),"数据更新","更新"});
		}
	}
	
	private void updateRefundAli(Refund refund){
		Refund refundN=new Refund();
		refundN.setId(refund.getId());
		try {
			refundN.setQuitDate(new Date());
			refundBillService.update(refundN);
		} catch (Exception e) {
			this.log.error("退款处理时间更新：", e);
			throw new ApplicationException("退款处理时间更新",e,new Object[]{refund});
		}
	}
	private void updateRefund(Refund refund,String returnCode){
		try {
			int status=BillConstants.REFUND_STATUS_INHAND;//平台退款处理中
			if (BillConstants.return_code_30002.equals(returnCode)){
				status=BillConstants.REFUND_STATUS_SUCCEED;//退款成功
			}
			refund.setQuitDate(new Date());
			refund.setStatus(status);
			refundBillService.update(refund);
		} catch (Exception e) {
			this.log.error("退款记录表数据更新：", e);
			throw new ApplicationException("退款记录表数据更新",e,new Object[]{refund,returnCode});
		}
	}
	private void updateBill(Refund refund,String returnCode){
		try {
			int status=BillConstants.BILL_STATUS_REFUND_DO;//平台退款处理中
			if (BillConstants.return_code_30002.equals(returnCode)){
				status=BillConstants.BILL_STATUS_REFUND;//退款成功
			}
			Bill bill = new Bill();
			bill.setBid(refund.getBid());
			bill.setStatus(status);
			allBillService.update(bill);
		} catch (Exception e) {
			this.log.error("订单表数据更新：", e);
			throw new ApplicationException("订单表数据更新",e,new Object[]{refund,returnCode});
		}
	}
	@RequestMapping(value = "export")
	// 待退款订单导出
	public void export(Refund refund, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		refund.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", refundBillService.getRefundBillPendingList(refund)
				.getContent());
		doExport(request, response, "billmanagerment/refundBill.xls", params);
	}
	/**
	 * @author dong'jt
	 * 创建时间：2015年9月7日 下午6:11:48
	 * 描述：订单恢复
	 * @param refund
	 * @return
	 */
	@RequestLogging(name = "订单恢复")
	@SuppressWarnings("finally")
	@RequestMapping(value = "recoveryOrder")
	@ResponseBody
	public Object recoveryOrder(Refund refund){
		Resultable resultable = null;
		try {
			resultable=refundBillService.recoveryOrderClient(refund);
		} catch (Exception e) {
			this.log.error("订单恢复异常："+e);
			refundBillService.fireLoginEvent(StringUtils.addStrToStrArray(((ApplicationException)e).getLogInfo(),new ApplicationException("订单恢复", e, new Object[]{refund}).getMessage()),0);
		}
		return resultable;
	}
}