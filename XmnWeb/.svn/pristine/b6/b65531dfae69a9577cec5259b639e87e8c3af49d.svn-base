package com.xmniao.xmn.core.billmanagerment.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.billmanagerment.entity.Refund;
import com.xmniao.xmn.core.billmanagerment.service.AllBillService;
import com.xmniao.xmn.core.billmanagerment.service.RefundBillService;
import com.xmniao.xmn.core.billmanagerment.util.BillConstants;
import com.xmniao.xmn.core.thrift.client.proxy.ThriftClientProxy;
import com.xmniao.xmn.core.thrift.service.memberAccountService.MemberAccountService;
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
@RequestLogging(name="订单管理")
@Controller
@RequestMapping(value = "billmanagerment/refund")
public class RefundBillController extends BaseController {

	@Autowired
	private RefundBillService refundBillService;

	//接口
	@Resource(name = "memberAccountService")
	private ThriftClientProxy memberAccountService;
	
	@Autowired
	private AllBillService allBillService;//所有订单service
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "billmanagerment/refundBillList";
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
		this.log.info("refundBillController-->list refundbill=" + refund);
		Pageable<Refund> pageable = new Pageable<Refund>(refund);
		pageable = refundBillService.getRefundBillList(refund);
		return pageable;
	}
	/**
	 * 
	 * updateInit(修改初始化)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "update/init")
	public ModelAndView updateInit(HttpServletRequest request, @RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			Refund refund = refundBillService.getObject(new Long(id));
			this.log.info(refund);
			modelAndView.addObject("refund", refund);
			modelAndView.setViewName("billmanagerment/dealWithRefundBill");
		} catch (NumberFormatException e) {
			this.log.error("页面初始异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 
	 * update(修改)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestLogging(name="订单申诉")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(Refund  refund) {
		Resultable resultable = null;
		Map<String,String> map=new HashMap<String,String>();
		map.put("result", "商家申诉");
		try {
			if(refund.getStatus()==BillConstants.REFUND_STATUS_REJECT){//5 商家申诉成功			
				Long memberCancelRefundlong=getMemberCancelRefundlong(refund);//用户取消申请订单退款
				if(memberCancelRefundlong > BillConstants.ZERO){
					map=this.putParams(refund);
			    	this.updateRefund(refund, map.get("Pro"),BillConstants.KEFU);    
					this.updateBill(refund, BillConstants.BILL_STATUS_APPEAL_SUCCEED);
					this.log.info("8：驳回申请退款，商家申诉成功：状态更新为："+BillConstants.BILL_STATUS_APPEAL_SUCCEED);
				}				
		    }else if(refund.getStatus()==BillConstants.REFUND_STATUS_FAILURE){//7 同意退款(商家申诉失败)
			    map=this.putParams(refund);
	    	    this.updateRefund(refund,map.get("Pro"),BillConstants.KEFU);
				this.updateBill(refund, BillConstants.BILL_STATUS_APPEAL_FAILURE);
	       }else{//发给总裁办	
	    	    map=this.putParams(refund);
	    	    this.updateRefund(refund,map.get("Pro"),1);
			 }	
			resultable=this.logInfo();//日志记录
		 } catch (Exception e) {
			this.log.error("修改异常", e);
			resultable = new Resultable(false, "处理失败!");
		 } finally {
			memberAccountService.returnCon();
			String[] s={"订单编号",String.valueOf(refund.getBid()),"商家申诉",map.get("result")};
			refundBillService.fireLoginEvent(s, resultable.getSuccess()?1:0);
			return resultable;
		}
	}
	
	public Map<String,String> putParams(Refund  refund){
		Map<String,String> map =new HashMap<String,String>();
		if(refund.getStatus()==BillConstants.REFUND_STATUS_REJECT){
			String str="申诉处理结果【驳回退款申请】";
			if(!"".equals(refund.getProcessing())){
				str=str+"<br>驳回原因【"+refund.getProcessing()+"】";
			}
			map.put("Pro", str);
			map.put("result","驳回退款申请");
		}else if(refund.getStatus()==BillConstants.REFUND_STATUS_FAILURE){
			map.put("Pro", "申诉处理结果【同意退款】");
			map.put("result","驳回退款申请");
		}else{
			map.put("Pro", "申诉处理结果【传送给总裁办】");
			map.put("result","驳回退款申请");
		}
		return map;
	}
	public Long getMemberCancelRefundlong(Refund  refund) throws Exception{//用户取消申请订单退款
		String bids=refund.getBid().toString();
		MemberAccountService.Client client = (MemberAccountService.Client)(memberAccountService.getClient());
		return client.memberCancelRefund(bids);
	}
	public void updateRefund(Refund refund,String Pro,int isKF){//Refund更新
		if(isKF==BillConstants.KEFU){//客服处理标志
			refund.setIsPresidentHandle(String.valueOf(BillConstants.KEFU));//客服处理标志
		}
		refund.setQdate(new Date());
	    refund.setProcessing(Pro);
	    refundBillService.update(refund);
	}
	public void updateBill(Refund refund,int status){//Bill更新
		Bill bill=new Bill();
		bill.setBid(refund.getBid());
		bill.setStatus(status);
		allBillService.update(bill);
	}
	public Resultable logInfo(){//日志
		this.log.info("修改成功");
		return new Resultable(true, "处理成功!");
	}
}