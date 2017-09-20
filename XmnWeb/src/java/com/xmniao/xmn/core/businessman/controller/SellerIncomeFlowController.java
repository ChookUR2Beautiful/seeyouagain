package com.xmniao.xmn.core.businessman.controller;

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
import com.xmniao.xmn.core.businessman.entity.TSellerIncomeFlow;
import com.xmniao.xmn.core.businessman.service.SellerIncomeFlowService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称： XmnWeb
 * 类名称： SellerIncomeFlowController
 * 类描述：商家收入流水
 * 创建人： lifeng
 * 创建时间： 2016年5月25日下午7:30:02
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@RequestLogging(name="商家管理")
@Controller
@RequestMapping(value = "businessman/sellerIncomeFlow")
public class SellerIncomeFlowController extends BaseController {

	@Autowired
	private SellerIncomeFlowService sellerIncomeFlowService;
	
	/**
	 * init(初始化列表页面)
	 * @return
	 */
	@RequestMapping(value = "/init")
	public String init() {
		return "businessman/sellerIncomeFlowList";
	}
	
	/**
	 * list(列表数据初始化)
	 * @param advertising
	 * @return
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object list(TSellerIncomeFlow sellerIncomeFlow) {
		this.log.info("SellerIncomeFlowController-->list sellerIncomeFlow=" + sellerIncomeFlow);
		sellerIncomeFlow.setLimit(PageConstant.NEW_LIMIT);
		Pageable<TSellerIncomeFlow> pageable = new Pageable<TSellerIncomeFlow>(sellerIncomeFlow);
		List<TSellerIncomeFlow> sellerIncomeFlowList = sellerIncomeFlowService.getSellerIncomeFlowList(sellerIncomeFlow);
		pageable.setContent(sellerIncomeFlowList);
		pageable.setTotal(sellerIncomeFlowService.count(sellerIncomeFlow));
		return pageable;
	}
	
	/**
	 * @Description: 导出商家收入流水列表
	 * @param seller
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(TSellerIncomeFlow sellerIncomeFlow, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		sellerIncomeFlow.setOrder(PageConstant.NOT_ORDER);
		sellerIncomeFlow.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		List<TSellerIncomeFlow> sellerIncomeFlowList = sellerIncomeFlowService.getSellerIncomeFlowList(sellerIncomeFlow);
		params.put("list", sellerIncomeFlowList);
		doExport(request, response, "businessman/sellerIncomeFlow.xls", params);
	}
}
