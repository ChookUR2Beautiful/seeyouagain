package com.xmniao.xmn.core.xmermanagerment.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
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
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmer;
import com.xmniao.xmn.core.xmermanagerment.entity.TSaasOrder;
import com.xmniao.xmn.core.xmermanagerment.service.BXmerService;
import com.xmniao.xmn.core.xmermanagerment.service.TSaasOrderService;

/**
 * 项目名称： XmnWeb
 * 类名称： TSaasOrderController.java
 * 类描述：寻蜜客套餐订单
 * 创建人： lifeng
 * 创建时间： 2016年6月15日上午11:29:04
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@RequestLogging(name="寻蜜客套餐订单")
@Controller
@RequestMapping(value = "/xmer/manage/saasorder")
public class TSaasOrderController extends BaseController {
	
	@Autowired
	private TSaasOrderService saasOrderService;
	
	@Autowired 
	private BXmerService bXmerService;

	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "/init")
	public String init(){
		return "xmermanagerment/saasOrderList";
	}
	
	/**
	 * @Description: list(列表数据初始化)
	 * @Param:saasOrder
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月15日上午11:32:21
	 */
	@RequestMapping(value = "/init/list")
	@ResponseBody
	public Object list(TSaasOrder saasOrder) {
		this.log.info("TSaasOrderController-->list saasOrder=" + saasOrder);
		Pageable<TSaasOrder> pageable = new Pageable<TSaasOrder>(saasOrder);
		//查询寻蜜客套餐订单列表
		List<TSaasOrder> saasOrderList_new = this.installSaasOrderList(saasOrder);
		
		pageable.setContent(saasOrderList_new);
		pageable.setTotal(saasOrderService.getCountByParam(saasOrder));
		return pageable;
	}
	
	/**
	 * @Description: 导出寻蜜客套餐订单
	 * @param saasOrder
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @time:2016年6月15日上午11:31:28
	 */
	@RequestMapping(value = "export")
	public void export(TSaasOrder saasOrder, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		saasOrder.setOrder(PageConstant.NOT_ORDER);
		saasOrder.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		//查询寻蜜客套餐订单列表 
		List<TSaasOrder> saasOrderList_new = this.installSaasOrderList(saasOrder);
		params.put("list", saasOrderList_new);
		
		String fileName = "xmermanagerment/saasOrder.xls";
		if(saasOrder.getSaasChannel()!=null){
			switch (saasOrder.getSaasChannel()) {
			case 1:
				break;
			case 2:
				fileName = "xmermanagerment/ecsaasOrder.xls";		
				break;
			case 3:
				fileName = "xmermanagerment/versaasOrder.xls";
				break;
			case 4:
				fileName = "xmermanagerment/versendsaasOrder.xls";
				break;
			default:
				break;
			}
		}
		doExport(request, response, fileName, params);
	}
	
	/**
	 * 套餐订单列表 及 导出数据列表查询组装实体
	 * @author yhl
	 * @param TSaasOrder saasOrder
	 * @return List<TsaasOrder> list
	 * */
	public List<TSaasOrder> installSaasOrderList(TSaasOrder saasOrder){
		//获取列表信息
		List<TSaasOrder> saasOrderList = saasOrderService.getSaasOrderList(saasOrder);
		//循环遍历获取订单用户ID 
		List<Integer> xmerList = new ArrayList<Integer>(); 
		List<TSaasOrder> saasOrderList_new = new ArrayList<TSaasOrder>();
		if (saasOrderList.size()>0) {
			for (int i = 0; i < saasOrderList.size(); i++) {
				xmerList.add(saasOrderList.get(i).getUid());
			}
		
		//根据订单列表信息循环的uid查询 寻蜜客信息
		boolean flag = false;
		List<BXmer> bXmerList =  bXmerService.getXmerBySaasOrderUid(xmerList);
		//匹配用户信息与订单信息
		for (int i = 0; i < saasOrderList.size(); i++) {
			for (int j = 0; j < bXmerList.size(); j++) {
				if (saasOrderList.get(i).getUid().toString().equals(bXmerList.get(j).getUid().toString())) {
					TSaasOrder tSaasOrder_ =  saasOrderList.get(i);
					tSaasOrder_.setXmerName(bXmerList.get(j).getName());
					tSaasOrder_.setXmerPhone(bXmerList.get(j).getPhoneid());
					saasOrderList_new.add(tSaasOrder_);
					flag = true;
				}
			}
			if (flag == false) {
				saasOrderList_new.add(saasOrderList.get(i));
			}
			flag = false;
		}
		}
		return saasOrderList_new;
	}
	
	
}
