package com.xmniao.xmn.core.cloud_design.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.entity.AfterSale;
import com.xmniao.xmn.core.cloud_design.entity.CommonMaterial;
import com.xmniao.xmn.core.cloud_design.entity.MaterialOrder;
import com.xmniao.xmn.core.cloud_design.entity.Supplier;
import com.xmniao.xmn.core.cloud_design.service.DMaterialService;
import com.xmniao.xmn.core.cloud_design.service.OrderManageService;
import com.xmniao.xmn.core.cloud_design.service.SupplierManageService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：MaterialOrderController
 * 
 * 类描述： 订单管理
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年9月28日 下午2:15:22 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@RequestLogging(name="订单管理")
@Controller
@RequestMapping("materialOrder/manage")
public class DesignOrderController extends BaseController{
	
	@Autowired
	private OrderManageService orderManageService;
	
	@Autowired
	private DMaterialService dMaterialService;
	
	@Autowired
	private SupplierManageService suManageService;
	/**
	 * 
	 * 方法描述：初始化页面
	 * 创建人： chenJie
	 * 创建时间：2016年9月28日下午2:24:23
	 * @return
	 */
	@RequestMapping("/init")
	public String init(){
		return "cloud_design/order/materialOrderList";
	}
	
	/**
	 * 
	 * 方法描述：获取订单列表
	 * 创建人： chenJie
	 * 创建时间：2016年9月28日下午2:59:04
	 * @return
	 */
	@RequestMapping("/init/list")
	@ResponseBody
	public Object getOrderList(MaterialOrder materialOrder){
		log.info("获取订单列表："+materialOrder);
		Pageable<MaterialOrder> pageable = new Pageable<>(materialOrder);
		pageable.setContent(orderManageService.getList(materialOrder));
		pageable.setTotal(orderManageService.count(materialOrder));
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：查看订单详情
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月21日下午3:20:07 <br/>
	 * @param materialOrder
	 * @return
	 */
	@RequestMapping("/init/view")
	public ModelAndView getOrderView(MaterialOrder materialOrder){
		log.info("查看订单详情："+materialOrder);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("cloud_design/order/orderDetails");
		MaterialOrder materialOrder1 = orderManageService.getList(materialOrder).get(0);
		modelAndView.addObject("materialOrder",materialOrder1);
		modelAndView.addObject("material",dMaterialService.selectById(materialOrder1.getMaterialId()));
		if(materialOrder1.getStatus()==2){//待确定订单
			Supplier supplier = new Supplier();
			supplier.setType(2);
		modelAndView.addObject("suppliers",suManageService.getList(supplier));//查询设计师
		}
		if(materialOrder1.getStatus()==3){//代发货订单
			Supplier supplier = new Supplier();
			supplier.setType(1);
		modelAndView.addObject("suppliers",suManageService.getList(supplier));//查询供应商
		}
		
		if(materialOrder1.getStatus()==7){//获取售后记录
			AfterSale afterSale = orderManageService.getAfterSale(materialOrder.getOrderNo());
			modelAndView.addObject("afterSale", afterSale);
		}
		return modelAndView;
	}
	
	/**
	 * 
	 * 方法描述：根据物料id查询物料信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月24日上午11:51:27 <br/>
	 * @param materialOrder
	 * @return
	 */
	@RequestMapping("/init/shopView")
	@ResponseBody
	public Object shopView(MaterialOrder materialOrder){
		 Resultable resultable = null;
		try {
			if("001".equals(materialOrder.getType())){//平面订单
				CommonMaterial commonMaterial = orderManageService.getCommonMaterial(materialOrder);
				commonMaterial.setType("001");
				resultable = new Resultable(true,"查询成功",commonMaterial);
				return resultable;
			}else if("002".equals(materialOrder.getType())){//物料订单
				CommonMaterial customizeMaterial = orderManageService.getCustomizeMaterial(materialOrder);
				resultable = new Resultable(true,"查询成功",customizeMaterial);
				return resultable;
			}
			log.error("查询物料信息失败,物料类型异常");
			resultable = new Resultable(false,"查询物料信息失败","");
			return resultable;
		} catch (Exception e) {
			log.error("查询物料信息失败",e);
			resultable = new Resultable(false,"查询物料信息失败");
			return resultable;
		}
		
	}
	/**
	 * 
	 * 方法描述：修改定制订单价格
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月23日下午9:33:02 <br/>
	 * @param materialOrder
	 * @return
	 */
	@RequestMapping("/update/changePrice")
	@ResponseBody
	public Object changePrice(MaterialOrder materialOrder){
		log.info("修改价格："+materialOrder);
		Resultable resultable = null;
		try {
			boolean update = orderManageService.updatePrice(materialOrder);
			if (update) {
				log.info("修改成功");
				resultable = new Resultable(true,"修改成功");
			}else {
				log.info("修改失败");
				resultable = new Resultable(false,"修改失败");
			}
		} catch (Exception e) {
			log.error("修改失败",e);
			resultable = new Resultable(false,"修改失败");
		}
		return resultable;
	}
	
	/**
	 * 
	 * 方法描述：删除已关闭订单
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月24日下午9:23:53 <br/>
	 * @param materialOrder
	 * @return
	 */
	@RequestMapping("/update/delete")
	@ResponseBody
	public Object delete(MaterialOrder materialOrder){
		log.info("删除已关闭订单："+materialOrder);
		Resultable resultable = null;
		try {
			Integer result = orderManageService.delete(materialOrder);
			if (result == 1) {
				log.info("删除成功："+materialOrder.getOrderNo());
				resultable = new Resultable(true,"删除成功");
			}else {
				log.error("删除失败："+materialOrder.getOrderNo());
				resultable = new Resultable(false,"删除失败");
			}
		} catch (Exception e) {
			log.error("删除失败："+materialOrder.getOrderNo(),e);
			resultable = new Resultable(false,"删除失败");
		}
		return resultable;
	}
	
	/**
	 * 
	 * 方法描述：发货
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月1日下午5:02:14 <br/>
	 * @param materialOrder
	 * @return
	 */
	@RequestMapping("/update/deliver")
	@ResponseBody
	public Object deliver(MaterialOrder materialOrder){
		log.info("更新订单发货状态"+materialOrder);
		Resultable resultable = null;
		try {
			Integer result = orderManageService.deliver(materialOrder);
			if (result == 1) {
				log.info("更新订单发货状态成功："+materialOrder.getOrderNo());
				resultable = new Resultable(true,"更新成功");
			}else {
				log.error("更新订单发货状态失败："+materialOrder.getOrderNo());
				resultable = new Resultable(false,"更新失败");
			}
		} catch (Exception e) {
			log.error("更新订单发货状态失败："+materialOrder.getOrderNo(),e);
			resultable = new Resultable(false,"更新订单发货状态失败");
		}
		return resultable;
	}
	
	/**
	 * 
	 * 方法描述：更新设计信息
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月1日下午5:02:14 <br/>
	 * @param materialOrder
	 * @return
	 */
	@RequestMapping("/update/saveMaterialPic")
	@ResponseBody
	public Object saveMaterialPic(MaterialOrder materialOrder){
		log.info("更新订单发货状态"+materialOrder);
		Resultable resultable = null;
		try {
			Integer result = orderManageService.saveMaterialPic(materialOrder);
			if (result == 1) {
				log.info("更新设计信息成功："+materialOrder.getOrderNo());
				resultable = new Resultable(true,"更新成功");
			}else {
				log.error("更新设计信息失败："+materialOrder.getOrderNo());
				resultable = new Resultable(false,"更新失败");
			}
		} catch (Exception e) {
			log.error("更新设计信息失败："+materialOrder.getOrderNo(),e);
			resultable = new Resultable(false,"更新失败");
		}
		return resultable;
	}
	
	/**
	 * 更新订单状态为代发货
	 */
	@RequestMapping("/update/updateSaleStatus")
	@ResponseBody
	public Object updateOrderState(MaterialOrder materialOrder){
		log.info("更新订单发货状态"+materialOrder);
		Resultable resultable = null;
		try {
			materialOrder.setStatus(3);
			Integer result = orderManageService.updateOrderStatus(materialOrder);
			if (result == 1) {
				log.info("更新订单状态成功："+materialOrder.getOrderNo());
				resultable = new Resultable(true,"更新订单状态成功");
			}else {
				log.error("更新订单状态失败："+materialOrder.getOrderNo());
				resultable = new Resultable(false,"更新订单状态失败");
			}
		} catch (Exception e) {
			log.error("更新订单状态失败："+materialOrder.getOrderNo(),e);
			resultable = new Resultable(false,"更新订单状态失败");
		}
		return resultable;
	}
	/**
	 * 
	 * 方法描述：操作售后
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月1日下午8:48:33 <br/>
	 * @param afterSale
	 * @return
	 */
	@RequestMapping("/update/addAfterSale")
	@ResponseBody
	public Object addAfterSale(AfterSale afterSale){
		log.info("操作售后addAfterSale"+afterSale);
		Resultable resultable = null;
		try {
			Boolean result = orderManageService.addAfterSale(afterSale);
			if (result) {
				log.info("操作售后成功：");
				resultable = new Resultable(true,"添加售后记录成功");
			}else {
				log.error("操作售后失败："+afterSale.getOrderNo());
				resultable = new Resultable(false,"更新失败");
			}
		} catch (Exception e) {
			log.error("操作售后异常：",e);
			resultable = new Resultable(false,"操作售后异常");
		}
		return resultable;
	}
	
	/**
	 * 
	 * 方法描述：更新售后记录
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月1日下午8:48:33 <br/>
	 * @param afterSale
	 * @return
	 */
	@RequestMapping("/update/updateAfterSale")
	@ResponseBody
	public Object updateAfterSale(AfterSale afterSale){
		log.info("操作售后"+afterSale);
		Resultable resultable = null;
		try {
			Integer result = orderManageService.updateAfterSale(afterSale);
			if (result==1) {
				log.info("更新售后成功");
				resultable = new Resultable(true,"更新售后成功");
			}else {
				log.error("更新售后失败："+afterSale.getOrderNo());
				resultable = new Resultable(false,"更新失败");
			}
		} catch (Exception e) {
			log.error("更新售后记录失败",e);
			resultable = new Resultable(false,"更新失败");
		}
		return resultable;
	}
	
	/**
	 * 
	 * 方法描述：订单导出
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月23日上午11:07:24 <br/>
	 * @param materialOrder
	 * @param request
	 * @param response
	 */
	@RequestMapping("/export")
	public void export(MaterialOrder materialOrder,HttpServletRequest request,HttpServletResponse response){
		Map<String,Object> params = new HashMap<>();
		materialOrder.setLimit(-1);
		List<MaterialOrder> orderList = orderManageService.getList(materialOrder);
		params.put("list",orderList);
		doExport(request, response,"cloud_design/MaterialOrder.xls", params);
	}
	
	/**
	 * 
	 * 方法描述：合成图片
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月13日下午3:57:02 <br/>
	 * @param orderNo
	 * @throws Exception
	 */
	@RequestMapping("/joinPic")
	@ResponseBody
	public Boolean drawIma(@RequestParam("orderNo") String orderNo){
		log.info("接受到合成订单："+orderNo+"图片的请求");
		joinPicThread(orderNo);
		return true;
	}
	
	/**
	 * 
	 * 方法描述：合成图片新线程
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年12月15日上午10:29:10 <br/>
	 * @param orderNo
	 */
	public void joinPicThread(String orderNo){
		final String no = orderNo;
		new Thread(){
			public void run(){
				try {
					log.info("开始合成订单："+no+" 图片");
					orderManageService.jointPic(no);
					log.info("合成图片成功："+no);
				} catch (Exception e) {
					log.error("合成图片失败："+no,e);
				}
			}
		}.start();
	}
}
