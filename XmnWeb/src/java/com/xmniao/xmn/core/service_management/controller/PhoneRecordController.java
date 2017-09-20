package com.xmniao.xmn.core.service_management.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.entity.TJoint;
import com.xmniao.xmn.core.business_cooperation.service.JointService;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.businessman.service.SellerService;
import com.xmniao.xmn.core.http.entity.PUserRequestSelect;
import com.xmniao.xmn.core.http.service.PuserService;
import com.xmniao.xmn.core.president_office.service.DisputeOrderService;
import com.xmniao.xmn.core.service_management.entity.Member;
import com.xmniao.xmn.core.service_management.entity.Order;
import com.xmniao.xmn.core.service_management.entity.PhoneRecord;
import com.xmniao.xmn.core.service_management.service.PhoneRecordService;
import com.xmniao.xmn.core.system_settings.entity.TRole;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：PhoneRecordController
 * 
 * 类描述： 电话记录
 * 
 * 创建人： zhoujianhua
 * 
 * 创建时间：2015年3月02日14时22分
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Controller
@RequestMapping(value = "serviceManagement/phoneRecord")
public class PhoneRecordController extends BaseController {
	
	@Autowired
	private PhoneRecordService phoneRecordService;
	@Autowired
	private PuserService puserService;
	@Autowired
	private SellerService sellerService;
	@Autowired
	private JointService jointService;
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "service_management/phoneRecord";
	}
	
	/**
	 * 订单列表
	 * @param queryParmas
	 * @param request
	 * @return
	 * @throws TException
	 */
	@ResponseBody
	@RequestMapping(value = "order/list")
	public Object orderList(Order order) throws TException {
		return phoneRecordService.getOrderList(order);
	}
	/**
	 * 查找会员
	 * @param queryParmas
	 * @param request
	 * @return
	 * @throws TException
	 */
	@ResponseBody
	@RequestMapping(value = "member/list")
	public Object memberList(PUserRequestSelect puser) throws TException {
		puser.setPageSize("5");
		ModelAndView mv = new ModelAndView();
		puserService.getPuserInfo(mv,puser,true);
		mv.setViewName("service_management/memberTable");
		return mv;
	}
	
	/**
	 * 获取商家列表
	 * 
	 * @param seller
	 * @return
	 */
	@RequestMapping(value = "merchant/list")
	@ResponseBody
	public Object list(TSeller seller) {
		this.log.info("PhoneRecordController-->list merchant=" + seller);
		Pageable<TSeller> pageable = new Pageable<TSeller>(seller);
		pageable = sellerService.getSellerInfoList(seller);
		return pageable;
	}

	/**
	 * 
	 * list(合作商列表)
	 * 
	 * 
	 */
	@RequestMapping(value = "cooperation/list")
	@ResponseBody
	public Object list(TJoint joint) {
		Pageable<TJoint> pageable = new Pageable<TJoint>(joint);
		pageable.setContent(jointService.getList(joint));
		pageable.setTotal(jointService.count(joint));
		return pageable;
	}
	
	/**
	 * 
	 * add(添加)
	 * 
	 */
	@RequestLogging(name="添加记录")
	@RequestMapping(value = "/add")
	@ResponseBody
	public Object add(PhoneRecord phoneRecord) {
		Resultable resultable = null;
		try {
			phoneRecordService.add(phoneRecord);
			this.log.info("添加成功");
			resultable = new Resultable(true, "操作成功");
		} catch (Exception e) {
			this.log.error("添加异常", e);
			resultable = new Resultable(false, "操作失败");
		} finally {
			
		}
		return resultable;
	}
	
	
	/**
	 * 查找向蜜客
	 * @param queryParmas
	 * @param request
	 * @return
	 * @throws TException
	 *//*
	@ResponseBody
	@RequestMapping(value = "client/list")
	public Object clientList(@RequestBody Map<String, String> queryParmas, HttpServletRequest request) throws TException {
		return disputeOrderService.queryOrder(queryParmas);
	}
	*//**
	 * 查找商家
	 * @param queryParmas
	 * @param request
	 * @return
	 * @throws TException
	 *//*
	@ResponseBody
	@RequestMapping(value = "merchant/list")
	public Object merchantList(@RequestBody Map<String, String> queryParmas, HttpServletRequest request) throws TException {
		return disputeOrderService.queryOrder(queryParmas);
	}
	*//**
	 * 查找合作商
	 * @param queryParmas
	 * @param request
	 * @return
	 * @throws TException
	 *//*
	@ResponseBody
	@RequestMapping(value = "cooperation/list")
	public Object cooperationList(@RequestBody Map<String, String> queryParmas, HttpServletRequest request) throws TException {
		return disputeOrderService.queryOrder(queryParmas);
	}*/
	
	
}
