/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.live_anchor.entity.TLivePayOrder;
import com.xmniao.xmn.core.live_anchor.service.TLivePayOrderService;
import com.xmniao.xmn.core.thrift.service.liveService.ResponseData;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.util.handler.annotation.RequestToken;
import com.xmniao.xmn.core.xmnburs.service.BursService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LivePayOrderController
 * 
 * 类描述： 直播支付订单Controller
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-1 下午4:53:25 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播支付订单管理")
@Controller
@RequestMapping(value = "livePayOrder/manage")
public class LivePayOrderController extends BaseController {
	
	/**
	 * 注入直播支付
	 */
	@Autowired
	TLivePayOrderService livePayOrderService;
	
	/**
	 * 注入寻蜜鸟用户服务
	 */
	@Autowired
	BursService bursService;
	
	/**
	 * 
	 * 方法描述：跳转到直播支付订单管理页面
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-1下午4:55:00
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(TLivePayOrder livePayOrder,Model model){
		model.addAttribute("eUid", livePayOrder.getUid());
		return "live_anchor/livePayOrderManage";
	}
	
	/**
	 * 
	 * 方法描述：加载直播支付订单信息列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-15下午3:24:22 <br/>
	 * @param livePayOrder
	 * @return
	 */
	@RequestMapping(value="init/list")
	@ResponseBody
	public Object initList(TLivePayOrder livePayOrder){
		Pageable<TLivePayOrder> pageable=new Pageable<TLivePayOrder>(livePayOrder);
		livePayOrderService.setLivePayOrderRequest(livePayOrder);
		List<TLivePayOrder> list = livePayOrderService.getListContainUrsInfo(livePayOrder);
		Long count = livePayOrderService.count(livePayOrder);
		pageable.setContent(list);
		pageable.setTotal(count);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：跳转到添加充值订单页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add/init")
	@RequestToken(createToken=true,tokenName="payOrderToken")
	public String addInit(){
		return "live_anchor/livePayOrderEdit";
	}
	
	/**
	 * 
	 * 方法描述：添加充值订单 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="add")
	@RequestToken(removeToken=true,tokenName="payOrderToken")
	@ResponseBody
	public Resultable add(TLivePayOrder livePayOrder){
		Resultable result = new Resultable();
		try {
			ResponseData responseData = livePayOrderService.saveInfo(livePayOrder);
			int state = responseData.getState();
			if(state==0){
				result.setSuccess(true);
				result.setMsg("添加成功!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("添加失败!");
			this.log.error("添加充值订单失败:"+e.getMessage(), e);
		}
		return result;
	}
	

	/**
	 * 
	 * 方法描述：导出直播支付订单
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-2下午2:00:47
	 * @param livePayOrder
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="export")
	public void export(TLivePayOrder livePayOrder,HttpServletRequest request,HttpServletResponse response){
		livePayOrder.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("list", livePayOrderService.getListContainUrsInfo(livePayOrder));
		doExport(request,response,"live_anchor/livePayOrder.xls",params);
	}
	
	
	@RequestMapping(value = "list/viewDetail")
	@ResponseBody
	public Object searchLivePayOrderInfo(TLivePayOrder livePayOrder) {
		Resultable resultable = null;
		try {
			TLivePayOrder livePayOrderInfo = new TLivePayOrder();
			if (livePayOrder.getId() != null){
				 livePayOrderInfo = livePayOrderService.searchLivePayOrderInfo(livePayOrder.getId().longValue());
			     resultable = new Resultable(true, "查询直播鸟币充值成功", livePayOrderInfo);
			}else{
				resultable = new Resultable(false, "查询直播鸟币充值信息失败");
			}
			
			return resultable;
			
		} catch (Exception e) {
			log.error("查询直播鸟币充值信息失败", e);
			resultable = new Resultable(false, "查询直播鸟币充值信息失败");
			return resultable;
		}
	}
	
	/**
	 * 
	 * 方法描述：配置V客充值渠道奖励方案<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-8下午8:55:16 <br/>
	 * @return
	 */
	@RequestMapping(value="updateExcitationProject")
	@ResponseBody
	public Resultable updateExcitationProject(TLivePayOrder livePayOrder){
		Resultable result = new Resultable();
		try {
			int state = 1;
			if (livePayOrder.getOrderNo() != null){
				ResponseData responseData = livePayOrderService.updateLivePayOrderExcitationProject(livePayOrder);
				state = responseData.getState();
			}
			if (state == 0) {
				result.setSuccess(true);
				result.setMsg("配置V客充值渠道奖励方案成功!");
			} else {
				result.setSuccess(false);
				result.setMsg("配置V客充值渠道奖励方案失败!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("配置V客充值渠道奖励方案失败!");
			this.log.error("配置V客充值渠道奖励方案失败:"+e.getMessage(), e);
		}
		return result;
	}

}
