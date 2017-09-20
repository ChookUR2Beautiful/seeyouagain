/**
 * 
 */
package com.xmniao.xmn.core.fresh.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.ActivityOrder;
import com.xmniao.xmn.core.fresh.service.ActivityOrderService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：ActivityOrderController
 * 
 * 类描述： 活动订单
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年3月1日 上午10:35:54 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="活动订单管理")
@Controller
@RequestMapping(value = "fresh/activityOrder")
public class ActivityOrderController extends BaseController{
	
	@Autowired
	private ActivityOrderService orderService;
	
	@RequestMapping("/init")
	public Object init(String orderNo,ActivityOrder aOrder){
		ModelAndView modelAndView = new ModelAndView("fresh/activityOrder/activityOrderList");
		if(StringUtils.isNotBlank(orderNo)){
			modelAndView.addObject("orderNo",orderNo);
		}
		Long activityId = aOrder.getActivityId();
		if(activityId!=null){
			modelAndView.addObject("activityId",activityId);
		}
		Integer activityType = aOrder.getActivityType();
		if(activityType!=null){
			modelAndView.addObject("activityType",activityType);
		}
		
		return modelAndView;
	}
	
	@RequestMapping("init/list")
	@ResponseBody
	public Object getList(ActivityOrder aOrder){
		log.info("活动订单列表getList："+aOrder);
		Pageable<ActivityOrder> pageable = new Pageable<>(aOrder);
		pageable.setContent(orderService.getList(aOrder));
		pageable.setTotal(orderService.count(aOrder));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：查看订单详情
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月3日下午2:19:32 <br/>
	 * @param aOrder
	 * @return
	 */
	@RequestMapping("/view")
	@ResponseBody
	public ModelAndView orderView(ActivityOrder aOrder){
		log.info("查看订单详情orderView："+aOrder);
		ModelAndView mv = new ModelAndView("/fresh/activityOrder/orderDetails");
		ActivityOrder activityOrder = orderService.getActivityOrder(aOrder);
		mv.addObject("aOrder", activityOrder);
		mv.addObject("express",orderService.getExpress());//物流公司列表
		return mv;
	}
	
	/**
	 * 
	 * 方法描述：更新活动订单
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年3月8日上午11:27:10 <br/>
	 * @param aOrder
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public Object updateOrder(ActivityOrder aOrder){
		log.info("更新活动订单updateOrder："+aOrder);
		Resultable resultable;
		try {
			aOrder.setUpdateTime(new Date());
			Integer result = orderService.update(aOrder);
			if(result != 1){
				log.error("更新活动订单失败，更新异常");
				resultable = new Resultable(false,"更新失败");
			}else {
				log.info("更新活动订单成功");
				resultable = new Resultable(true,"更新成功");
			}
		} catch (Exception e) {
			log.error("更新活动订单失败",e);
			resultable = new Resultable(false,"更新失败");
		}
		return resultable;
	}
	
	
	/**
	 * 导出订单列表
	 * @param bill
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "/export")
	public void export(ActivityOrder aOrder, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		log.info("活动订单列表导出getList："+aOrder);
		aOrder.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", orderService.getList(aOrder));
		doExport(request, response, "fresh/activityOrder.xls", params);
	}
	
	
	/**
	 * 
	 * 方法描述：活动夺宝中奖订单
	 * 创建人： jianming <br/>
	 * 创建时间：2017年3月6日下午5:55:29 <br/>
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getWinnerOrder")
	@ResponseBody
	public Object getWinnerOrder(@RequestParam(value="id",required=true)Integer id){
		ActivityOrder activityOrder=orderService.getWinnerOrder(id);
		return Resultable.success("成功", activityOrder);
		
	}
}
