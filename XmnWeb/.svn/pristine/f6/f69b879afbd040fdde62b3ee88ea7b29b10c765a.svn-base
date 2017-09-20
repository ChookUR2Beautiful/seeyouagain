package com.xmniao.xmn.core.xmermanagerment.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.thrift.service.payRefundService.RefundRequest;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.xmermanagerment.entity.TSaasSoldOrder;
import com.xmniao.xmn.core.xmermanagerment.service.TSaasSoldOrderService;

/**
 * 项目名称： XmnWeb
 * 类名称： TSaasSoldOrderController.java
 * 类描述：商家签约订单
 * 创建人： lifeng
 * 创建时间： 2016年6月15日上午11:28:26
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@RequestLogging(name="商家签约订单")
@Controller
@RequestMapping(value = "/xmer/manage/saassoldorder")
public class TSaasSoldOrderController extends BaseController {
	
	@Autowired
	private TSaasSoldOrderService saasSoldOrderService;

	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "/init")
	public String init(){
		return "xmermanagerment/saasSoldOrderList";
	}
	
	/**
	 * @Description: list(列表数据初始化)
	 * @Param:saasSoldOrder
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月15日上午11:30:21
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object list(TSaasSoldOrder saasSoldOrder) {
		this.log.info("TSaasSoldOrderController-->list saasSoldOrder=" + saasSoldOrder);
		Pageable<TSaasSoldOrder> pageable = new Pageable<TSaasSoldOrder>(saasSoldOrder);
		List<TSaasSoldOrder> saasSoldOrderList = saasSoldOrderService.getSaasSoldOrderList(saasSoldOrder);
		pageable.setContent(saasSoldOrderList);
		pageable.setTotal(saasSoldOrderService.getCountByParam(saasSoldOrder));
		return pageable;
	}
	
	/**
	 * @Description: 导出商家签约订单
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @time:2016年6月15日上午11:30:30
	 */
	@RequestMapping(value = "export")
	public void export(TSaasSoldOrder saasSoldOrder, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		saasSoldOrder.setOrder(PageConstant.NOT_ORDER);
		saasSoldOrder.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		List<TSaasSoldOrder> saasSoldOrderList = saasSoldOrderService.getSaasSoldOrderList(saasSoldOrder);
		params.put("list", saasSoldOrderList);
		doExport(request, response, "xmermanagerment/saasSoldOrder.xls", params);
	}
	
	/**
	 * @Description: 商家签约订单退款
	 * @Param:refundRequest
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月24日下午8:58:45
	 */
    @SuppressWarnings("finally")
	@RequestMapping(value = "/refund")
    @ResponseBody
    public Object freshRefund(RefundRequest refundRequest,String id) {
    	Resultable resultable = null;
    	Map<String, String> resultMap = new HashMap<String, String>();
    	String msg = "";
    	String statecode = "";
        try {
        	resultMap = saasSoldOrderService.saasSoldOrderRefund(refundRequest);
        	msg = resultMap.get("Msg");
        	statecode = resultMap.get("code");
        	resultable = new Resultable(true, msg,statecode);
            this.log.info("订单退款成功");
        } catch (Exception e) {
        	resultable = new Resultable(false, "退款异常");
            this.log.error("订单退款异常", e);
        } finally{
      		return resultable;
        }
      
    }
	
}
