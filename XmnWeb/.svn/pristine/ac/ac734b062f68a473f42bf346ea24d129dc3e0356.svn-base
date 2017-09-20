/**
 * 
 */
package com.xmniao.xmn.core.billmanagerment.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.billmanagerment.entity.PackageOrder;
import com.xmniao.xmn.core.billmanagerment.service.PackageOrderService;
import com.xmniao.xmn.core.billmanagerment.util.BillConstants;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：PackageOrderService
 * 
 * 类描述： 套餐订单
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2017年2月24日 上午10:14:46 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

@Controller
@RequestMapping("/packageOrder/manage")
@RequestLogging(name="套餐订单")
public class PackageOrderController extends BaseController{
	
	@Autowired
	private PackageOrderService pOrderService;
	
	@RequestMapping("/init")
	public String init(){
		return "/live_anchor/packageOrder/packageOrderList";
	}
	
	/**
	 * 
	 * 方法描述：套餐订单列表（支付成功）
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月24日上午10:54:01 <br/>
	 * @param pOrder
	 * @return
	 */
	@RequestMapping("/init/list")
	@ResponseBody
	public Object getList(PackageOrder pOrder){
		log.info("获取套餐订单列表:"+pOrder);
		Pageable<PackageOrder> pageable = new Pageable<>(pOrder);
		try {
			List<PackageOrder> list = pOrderService.getList(pOrder);
			pageable.setContent(list);
			pageable.setTotal(pOrderService.count(pOrder));
			pageable.setTitleInfo(pOrderService.getTitleInfo(pOrder));
		} catch (Exception e) {
			log.error("获取套餐订单列表失败",e);
		}
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：查看订单详情
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月24日下午2:43:35 <br/>
	 * @param pOrder
	 * @return
	 */
	@RequestMapping("/view")
	@ResponseBody
	public ModelAndView orderView(PackageOrder pOrder){
		ModelAndView mv = new ModelAndView("/live_anchor/packageOrder/packageOrderDetail");
		try {
			PackageOrder packageOrder = pOrderService.getList(pOrder).get(0);
			mv.addObject("packageOrder",packageOrder);
			mv.addObject("coupons",pOrderService.getCouponList(pOrder));
		} catch (Exception e) {
			log.error("查询订单详情异常",e);
		}
		return mv;
	}
	/**
	 * 
	 * 方法描述：套餐订单退款
	 * 1.修改订单状态  2.注销优惠券、抵用券 （支付金额不做操作，又财务直接退款---产品要求）
	 * 创建人： chenJie <br/>
	 * 创建时间：2017年2月24日下午2:43:35 <br/>
	 * @param pOrder
	 * @return
	 */
	@RequestMapping("/refund")
	@ResponseBody
	public Object orderRefund(@RequestParam(value="description") String description,@RequestParam(value="orderNo") String orderNo){
		log.info("套餐订单退款orderRefund："+orderNo);
		Resultable resultable;
		try {
			pOrderService.refund(orderNo, description);
			log.info("套餐订单退款成功");
			resultable = new Resultable(true,"套餐订单退款成功");
		} catch (Exception e) {
			log.error("套餐订单退款失败："+orderNo,e);
			resultable = new Resultable(false,e.getMessage());
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
	public void export(PackageOrder pOrder, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		pOrder.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", pOrderService.getList(pOrder));
		doExport(request, response, "live_anchor/packageOrder.xls", params);
	}
}
