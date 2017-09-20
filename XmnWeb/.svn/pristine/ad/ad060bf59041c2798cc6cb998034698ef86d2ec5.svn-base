package com.xmniao.xmn.core.marketingmanagement.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.fresh.entity.Texpress;
import com.xmniao.xmn.core.fresh.service.BillFreshService;
import com.xmniao.xmn.core.marketingmanagement.entity.TMaterial;
import com.xmniao.xmn.core.marketingmanagement.entity.TMaterialOrder;
import com.xmniao.xmn.core.marketingmanagement.service.MaterialOrderService;
import com.xmniao.xmn.core.util.DateUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 类名称：MaterialController
 * 类描述： 物料信息控制器
 * 创建人： yhl
 * 创建时间：2016年7月15日14:14:20
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="物料管理")
@Controller
@RequestMapping(value = "billmanagerment/material/order")
public class MaterialOrderController extends BaseController{
	
	protected final Logger log = Logger.getLogger(getClass());
	
	@Autowired
	private MaterialOrderService materialOrderService;
	
	@Autowired
	private BillFreshService billFreshService;
	
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public ModelAndView init(){
		ModelAndView view = new ModelAndView("billmanagerment/materialOrderList");
		List<Texpress> expressList = billFreshService.getExpressList();
		view.addObject("express", expressList);
		return view;
	}
	
	/*
	 * 物料订单列表
	 */
	@ResponseBody
	@RequestMapping(value = "init/list")
	public Object getMaterialList(TMaterialOrder tMaterial){
		Pageable<TMaterialOrder> pageable = new Pageable<TMaterialOrder>(tMaterial);
		pageable.setContent(materialOrderService.getMaterialOrderList(tMaterial));
		pageable.setTotal(materialOrderService.getMaterialOrderCount(tMaterial));
		return pageable;
	}
	
	/*
	 * 获取物料订单详细信息- 到编辑页
	 */
	@RequestMapping(value = "init/edit")
	public ModelAndView editMaterial(HttpServletRequest request ,@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView("billmanagerment/materialOrderDetail");
		try {
			TMaterialOrder material = materialOrderService.getMaterialOrderInfo(new Long(id));
			List<TMaterial> meList = materialOrderService.getMaterialOrderForProd(material.getOrder_sn());
			List<TSeller> sellerList = materialOrderService.getMaterialOrderForSeller(material.getMid());
			this.log.info(material+" \n "+meList);
			modelAndView.addObject("material",material);
			modelAndView.addObject("product",meList);
			modelAndView.addObject("seller",sellerList);
		} catch (Exception e) {
			this.log.error("处理异常",e);
		}
		return modelAndView;
	}
	
	/*
	 * 修改配送地址
	 */
	@ResponseBody
	@RequestMapping(value = "address/update")
	public Object updateAddressById(TMaterialOrder materialOrder) {
		Resultable resultable = null;
		try {
			if (null != materialOrder) {
				materialOrderService.updateAddress(materialOrder);
				resultable = new Resultable(true, "修改成功!");
			}
		} catch (Exception e) {
			this.log.error("处理异常",e);
			resultable = new Resultable(true, "操作失败!");
		}
		return resultable;
	}
	
	/*
	 * 保存物流信息
	 */
	@ResponseBody
	@RequestMapping(value = "shipment/save")
	public Object shipment(HttpServletRequest request) {
		Resultable resultable = null;
		try {
			String id = request.getParameter("id");
			String courier_type = request.getParameter("courier_type");
			String courier_number = request.getParameter("courier_number");
			if (null!=id && ""!=id) {
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("id", id);
				map.put("courier_type", courier_type);
				map.put("courier_number", courier_number);
				map.put("delivery_time", DateUtil.smartFormat(new Date()));
				map.put("modify_time", DateUtil.smartFormat(new Date()));
				materialOrderService.updateShipment(map);
				resultable = new Resultable(true, "操作成功!");
				this.log.info("物流信息保存成功");
			}
		} catch (Exception e) {
			this.log.error("处理异常",e);
			resultable = new Resultable(true, "操作失败!");
		}
		return resultable;
	}
	
	
	/*
	 *确认收货
	 */
	@ResponseBody
	@RequestMapping(value = "getship")
	public Object getshipProd(@RequestParam("id") String id) {
		Resultable resultable = null;
		try {
			if (null!= id) {
				Map<Object, Object> map = new HashMap<Object, Object>();
				map.put("id", id);
				map.put("modify_time", DateUtil.smartFormat(new Date()));
				materialOrderService.updateGetship(map);
				resultable = new Resultable(true, "修改成功!");
			}
		} catch (Exception e) {
			this.log.error("处理异常",e);
			resultable = new Resultable(true, "操作失败!");
		}
		return resultable;
	}
	
	/*
	 *导出订单
	 */
	@RequestMapping(value = "/init/export")
	public void export(HttpServletRequest request,HttpServletResponse response,TMaterialOrder materialOrder) {
		try {
			Map<Object, Object> param = new HashMap<Object, Object>();
			param.put("startDate", materialOrder.getSubexsdate());
			param.put("endDate", materialOrder.getSubexedate());
			//获取要导出的数据
			List<TMaterialOrder> orderList = materialOrderService.getExportMaterialOrderList(param);
			
			//循环获取订单数据 且为订单列表新增所购买的商品列
			StringBuffer itemDetail = new StringBuffer();
			for (int i = 0; i < orderList.size(); i++) {
				for (int j = 0; j < orderList.get(i).getOrderItemList().size(); j++) {
					if (null != orderList.get(i).getOrderItemList().get(j)) {
						itemDetail.append("名称:"+orderList.get(i).getOrderItemList().get(j).getMaterial_name()
									           +",单价:"+orderList.get(i).getOrderItemList().get(j).getPrice()
									           +",购买量:"+orderList.get(i).getOrderItemList().get(j).getQuantity()+"\n");
					}
					orderList.get(i).setOrderItemDetail(itemDetail.toString());
				}
				itemDetail.setLength(0);
			}
			Map<String, Object> exportParams = new HashMap<String, Object>();
			exportParams.put("list", orderList);
			doExport(request, response, "billmanagerment/materailOrderBill.xls", exportParams);
			
		} catch (Exception e) {
			this.log.error("导出异常", e);
		}
		
		
	}
}
