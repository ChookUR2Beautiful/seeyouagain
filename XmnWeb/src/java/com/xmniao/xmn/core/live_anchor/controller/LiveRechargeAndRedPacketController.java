/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.LiveRequestBean;
import com.xmniao.xmn.core.live_anchor.service.TLiveRechargeAndRedPacketService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveRechargeAndRedPacketController
 * 
 * 类描述： 直播充值与红包统计Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-1-20 上午10:30:20 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播充值与红包统计管理")
@Controller
@RequestMapping(value = "liveRechargeAndRedPacket/manage")
public class LiveRechargeAndRedPacketController extends BaseController {
	
	/**
	 * 注入直播充值与红包统计Service
	 */
	@Autowired
	private TLiveRechargeAndRedPacketService rechargeAndRedPacketService;
	
	@RequestMapping(value="init")
	public String init(){
		return "live_anchor/liveRechargeAndRedPacketManage";
	}
	
	@RequestMapping(value="count/list")
	@ResponseBody
	public Object countList(LiveRequestBean liveRequest){
		Pageable<LiveRequestBean> pageable=new Pageable<LiveRequestBean>(liveRequest);
		try {
			rechargeAndRedPacketService.setQueryTime(liveRequest);
			List<LiveRequestBean> list = rechargeAndRedPacketService.getRechargeGroupByPayment(liveRequest);
			Long count = rechargeAndRedPacketService.getRechargeGroupByPaymentCount(liveRequest);
			Map<String, Object> titleInfo=new HashMap<String,Object>();
			titleInfo.put("startTime", liveRequest.getStartTime());
			titleInfo.put("endTime", liveRequest.getEndTime());
			pageable.setTitleInfo(titleInfo);
			pageable.setContent(list);
			pageable.setTotal(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pageable;
	}
	
	
	/**
	 * 
	 * 方法描述：加载累计充值信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-20下午1:56:08 <br/>
	 * @return
	 */
	@RequestMapping(value="count/loadRechargeTotal")
	@ResponseBody
	public Object loadRechargeTotal(LiveRequestBean requestBean){
		Resultable result=new Resultable();
		try {
			result=rechargeAndRedPacketService.loadRechargeTotal(requestBean);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载累计充值信息失败!");
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：加载指定时间累计充值信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-20下午1:56:08 <br/>
	 * @return
	 */
	@RequestMapping(value="count/loadRechargeOfTime")
	@ResponseBody
	public Object loadRechargeOfTime(LiveRequestBean requestBean){
		Resultable result=new Resultable();
		try {
			result=rechargeAndRedPacketService.loadRechargeOfTime(requestBean);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载指定时间累计充值信息失败！");
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：加载累计红包总额 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-20下午1:56:08 <br/>
	 * @return
	 */
	@RequestMapping(value="count/loadRedPacketTotal")
	@ResponseBody
	public Object loadReadPacketTotal(LiveRequestBean requestBean){
		Resultable result=new Resultable();
		try {
			result=rechargeAndRedPacketService.loadRedPacketTotal(requestBean);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载累计红包总额信息失败！");
		}
		return result;
	}
	
	/**
	 * 
	 * 方法描述：加载指定时间区间累计红包总额 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-20下午1:56:08 <br/>
	 * @return
	 */
	@RequestMapping(value="count/loadRedPacketOfTime")
	@ResponseBody
	public Object loadRedPacketOfTime(LiveRequestBean requestBean){
		Resultable result=new Resultable();
		try {
			result=rechargeAndRedPacketService.loadRedPacketOfTime(requestBean);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("加载指定时间区间累计红包信息失败！");
		}
		return result;
	}
	

}
