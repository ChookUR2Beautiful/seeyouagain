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

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.live_anchor.entity.TCouponOrder;
import com.xmniao.xmn.core.live_anchor.service.TCouponOrderService;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.xmnburs.service.BursService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：LiveCouponOrderController
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-3 下午5:34:17 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="直播券订单管理")
@Controller
@RequestMapping(value = "liveCouponOrder/manage")
public class LiveCouponOrderController extends BaseController {

	/**
	 * 注入直播支付
	 */
	@Autowired
	TCouponOrderService couponOrderService;
	
	/**
	 * 注入寻蜜鸟用户服务
	 */
	@Autowired
	BursService bursService;
	
	
	/**
	 * 
	 * 方法描述：跳转到直播粉丝券订单管理页面
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-1下午4:55:00
	 * @return
	 */
	@RequestMapping(value="init")
	public String init(TCouponOrder liveCouponOrder,Model model){
		model.addAttribute("eUid", liveCouponOrder.getUid());
		return "live_anchor/liveCouponOrderManage";
	}
	
	@RequestMapping(value="init/list")
	@ResponseBody
	public Object initList(TCouponOrder liveCouponOrder){
		Pageable<TCouponOrder> pageable=new Pageable<TCouponOrder>(liveCouponOrder);
		Object json=null;
		try {
			List<TCouponOrder> list = couponOrderService.getDetailInfoList(liveCouponOrder);
			Long count = couponOrderService.count(liveCouponOrder);
			Map<String, Object> titleInfo = couponOrderService.getTitleInfo(liveCouponOrder);
			pageable.setContent(list);
			pageable.setTotal(count);
			pageable.setTitleInfo(titleInfo);
			json = JSON.toJSON(pageable);
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error("加载直播粉丝券订单列表失败："+e.getMessage(), e);
		}
		return json;
	}
	
	/**
	 * 
	 * 方法描述：跳转到编辑粉丝券订单页面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-6下午2:57:27 <br/>
	 * @param liveCouponOrder
	 * @param model
	 * @return
	 */
	@RequestMapping(value="update/init")
	public String updateInit(TCouponOrder liveCouponOrder,Model model){
		Long id = liveCouponOrder.getId();
		TCouponOrder couponOrder = couponOrderService.getObject(id);
		TCouponOrder couponOrderDetail = couponOrderService.getDetailInfoById(liveCouponOrder);
		Long sellerid = couponOrder.getSellerid();
		if(sellerid!=null){
			TSeller sellerInfo = couponOrderService.getSellerInfoById(sellerid);
			if(sellerInfo!=null){
				couponOrder.setSellername(sellerInfo.getSellername());
			}
		}
		model.addAttribute("couponOrder", couponOrder);
		model.addAttribute("couponOrderDetail", couponOrderDetail);
		return "live_anchor/couponOrderEdit";
	}
	

	/**
	 * 
	 * 方法描述：导出直播粉丝券订单
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-2下午2:00:47
	 * @param liveCouponOrder
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="export")
	public void export(TCouponOrder liveCouponOrder,HttpServletRequest request,HttpServletResponse response){
		liveCouponOrder.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("list", couponOrderService.getDetailInfoList(liveCouponOrder));
		doExport(request,response,"live_anchor/liveCouponOrder.xls",params);
	}
}
