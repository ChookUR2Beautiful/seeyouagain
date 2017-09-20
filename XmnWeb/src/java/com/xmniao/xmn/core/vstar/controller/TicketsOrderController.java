/**
 * 
 */
package com.xmniao.xmn.core.vstar.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.vstar.entity.TicketsDetail;
import com.xmniao.xmn.core.vstar.entity.TicketsOrder;
import com.xmniao.xmn.core.vstar.service.TicketsDetailService;
import com.xmniao.xmn.core.vstar.service.TicketsOrderService;
import com.xmniao.xmn.core.xmnburs.dao.BursDao;
import com.xmniao.xmn.core.xmnburs.entity.Burs;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TicketsOrderController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年6月8日 下午5:51:44 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Controller
@RequestMapping("/ticketsOrder")
public class TicketsOrderController extends BaseController{
	
	@Autowired
	private TicketsOrderService ticketsOrderService;
	
	@Autowired
	private TicketsDetailService ticketsDetailService;

	@Autowired
	private BursDao bursDao;
	
	@RequestMapping("/init")
	public Object init(){
		return "vstar/ticketsOrder/ticketsOrderList";
	}
	
	@RequestMapping("/init/list")
	@ResponseBody
	public Object list(TicketsOrder ticketsOrder){
		Pageable<TicketsOrder> pageable = new Pageable<>(ticketsOrder);
		pageable.setContent(ticketsOrderService.getList(ticketsOrder));
		pageable.setTotal(ticketsOrderService.count(ticketsOrder));
		return pageable;
	}
	
	
	@RequestMapping("/init/getDetail")
	@ResponseBody
	public Object getDetail(@RequestParam(required=true) Integer id){
		ModelAndView modelAndView = new ModelAndView("vstar/ticketsOrder/orderDetails");
		modelAndView.addObject("id", id);
		return modelAndView;
	}
	
	@RequestMapping("/init/getDetail/list")
	@ResponseBody
	public Object getDetailList(@RequestParam(required=true) Integer id){
		try {
			List<TicketsDetail> list= ticketsOrderService.geTicketDetailsByIds(id);
			Resultable success = Resultable.success();
			success.setData(list);
			return success;
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat();
		}
	}
	
	@RequestMapping("/init/give")
	public Object initGive(){
		return "vstar/ticketsOrder/orderGive";
	}
	
	@RequestMapping("/add/give/order")
	@ResponseBody
	public Object addGiveOrder(@RequestParam(required=true)Integer userId,@RequestParam(required=true)String detailIds){
		List<TicketsDetail> details = ticketsDetailService.checkSeatsIsSell(Arrays.asList(detailIds.split(",")));
		if(!details.isEmpty()){
			String msg="";
			for (TicketsDetail ticketsDetail : details) {
				msg+=ticketsDetail.getSeatName()+"桌"+ticketsDetail.getZoneNo()+"号"+ticketsDetail.getSeatNo()+"位已出售<br/>";
			}
			return Resultable.defeat(msg);
		}
		Burs burs = new Burs();
		burs.setUid(userId);
		Burs urs = bursDao.getUrs(burs);
		ticketsOrderService.addGiveOrder(urs,Arrays.asList(detailIds.split(",")));
		return Resultable.success();
	}
	
}
