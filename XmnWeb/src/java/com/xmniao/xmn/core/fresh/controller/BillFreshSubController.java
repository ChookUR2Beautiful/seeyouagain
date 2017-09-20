package com.xmniao.xmn.core.fresh.controller;

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

import com.alibaba.fastjson.JSON;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.fresh.entity.TBillFresh;
import com.xmniao.xmn.core.fresh.entity.TBillFreshSub;
import com.xmniao.xmn.core.fresh.entity.TProductBill;
import com.xmniao.xmn.core.fresh.service.BillFreshService;
import com.xmniao.xmn.core.fresh.service.BillFreshSubService;
import com.xmniao.xmn.core.system_settings.entity.TUser;
import com.xmniao.xmn.core.thrift.service.payRefundService.RefundRequest;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 项目名称： XmnWeb 
 * 类名称： BillFreshSubController.java 
 * 类描述：子订单Contoller 
 * 创建人： lifeng
 * 创建时间： 2016年6月24日下午1:57:23 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version
 */
@RequestLogging(name = "子订单管理")
@Controller
@RequestMapping(value = "/fresh/subOrder")
public class BillFreshSubController extends BaseController {

	@Autowired
	private BillFreshSubService billFreshSubService;
	
	@Autowired
	private BillFreshService billFreshService;
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "fresh/order/orserlist";
	}

	/**
	 * @Description: 查询子订单列表，已支付订单
	 * @Param:
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月24日下午1:53:10
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object getFreshSubList(TBillFreshSub tbillFreshSub) {
		this.log.info("BillFreshSubController-->list tbillFreshSub = "
				+ tbillFreshSub);
		Pageable<TBillFreshSub> pageable = new Pageable<TBillFreshSub>(
				tbillFreshSub);
		pageable.setContent(billFreshSubService .getTBillFreshSubList(tbillFreshSub));
		pageable.setTotal(billFreshSubService.tbillFreshSubCount(tbillFreshSub));
		return pageable;
	}

	/**
	 * @Description:查看订单详细 
	 * @Param:id
	 * @return:ModelAndView
	 * @author:lifeng
	 * @time:2016年6月24日下午5:10:39
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/check")
	public ModelAndView view(HttpServletRequest request,
			@RequestParam("id") String id) {
		ModelAndView modelAndView = new ModelAndView(
				"fresh/order/checkbillfreshsub");
		try {
			TBillFreshSub tbillFreshSub = billFreshSubService.getBillFreshSub(new Long(id));
			Map<Object, Object> map = new HashMap<Object, Object>();
			map.put("sub_order_sn", tbillFreshSub.getSubOrderSn());
			List<TProductBill> productList = billFreshSubService.getBillFreshSubProd(map);
			modelAndView.addObject("tbillFreshSub", tbillFreshSub);
			modelAndView.addObject("product", productList);
		} catch (NumberFormatException e) {
			this.log.error("页面初始化异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 导出订单数据
	 * @param appPush
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "/export")
	public void export(TBillFreshSub tbillFreshSub, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		tbillFreshSub.getEdate();
		//获取导出数据    包含订单商品  productList
		List<TBillFreshSub> subList = billFreshSubService.getBillFreshSubForExport(tbillFreshSub);
		
		String productDetail = "";
		StringBuilder productDetailStr = null;
//		String bid = null;
//		StringBuilder bidstr = new StringBuilder();
//		for(int i = 0; i < subList.size(); i++){
//			bid = subList.get(i).getSubOrderSn();
//			bidstr = bidstr.append(bid+",");
//		}
//		List<TBillFreshSub> billFreshSubList = billFreshSubService.getBillFreshSubList(bidstr.toString().split(","));
		
		//循环遍历订单 取出订单商品 组装StringBuffer 赋值ProductDetail字段
		for(int j = 0; j < subList.size(); j++){
			productDetailStr = new StringBuilder();
			for (int k = 0; k < subList.get(j).getProductList().size(); k++) {
				if(null != subList.get(j).getProductList().get(k).getPname() && null != subList.get(j).getProductList().get(k).getCodeId()){
					productDetail = "名称:"+subList.get(j).getProductList().get(k).getPname()
									+"/编码："+subList.get(j).getProductList().get(k).getCodeId()
									+"/数量："+subList.get(j).getProductList().get(k).getGoodsNum()+"份";
					
					productDetailStr = productDetailStr.append(productDetail + ";\n");
				}
				subList.get(j).setProductDetail(productDetailStr.toString());
			}
		}
		params.put("list", subList);
		doExport(request, response, "fresh/subFreshBill.xls", params);
	}
	/**
	 * 导出订单数据
	 * @param appPush
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "/export1")
	public void export1(TBillFresh tBillFresh, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		log.info("订单导出（只导父订单）"+tBillFresh.getExsdate()+tBillFresh.getExedate());
		Map<String, Object> params = new HashMap<String, Object>();
//		tbillFreshSub.getEdate();
		//获取导出数据    包含订单商品  productList
		
		List<TBillFresh> subList = billFreshService.getBillFreshForExport1(tBillFresh);
		
		String productDetail = "";
		StringBuilder productDetailStr = null;
		
		//循环遍历订单 取出订单商品 组装StringBuffer 赋值ProductDetail字段
		for(int j = 0; j < subList.size(); j++){
			productDetailStr = new StringBuilder();
			for (int k = 0; k < subList.get(j).getProductList().size(); k++) {
				if(null != subList.get(j).getProductList().get(k).getPname() && null != subList.get(j).getProductList().get(k).getCodeId()){
					productDetail = "名称:"+subList.get(j).getProductList().get(k).getPname()
							+"/编码："+subList.get(j).getProductList().get(k).getCodeId()
							+"/数量："+subList.get(j).getProductList().get(k).getGoodsNum()+"份";
					
					productDetailStr = productDetailStr.append(productDetail + ";\n");
				}
				subList.get(j).setProductDetail(productDetailStr.toString());
			}
		}
		params.put("list", subList);
		doExport(request, response, "fresh/freshBillExport.xls", params);
	}

	/**
	 * @Title:checkdata
	 * @Description:导出之前验证是否有数据
	 * @param tbillFresh
	 * @return Object
	 * @throw
	 */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/checkdata")
	@ResponseBody
	public Object checkdata(TBillFreshSub tbillFreshSub) {
		Resultable resultable = null;
		try {
			Boolean flag = billFreshSubService.checkdata(tbillFreshSub);
			if (flag) {
				resultable = new Resultable(true, "操作成功");
			} else {
				resultable = new Resultable(false, "该时间没有订单数据");
			}
		} catch (Exception e) {
			resultable = new Resultable(false, "查询导出订单数据记录异常");
		} finally {
			return resultable;
		}
	}
	
	/**
	 * @Description: 发货操作更新发货状态及发货时间等
	 * @Param:
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月24日下午7:46:41
	 */
	@RequestLogging(name="子订单发货操作")
	@RequestMapping(value = "/shipments")
	@ResponseBody
	public Object update(TBillFreshSub tbillFreshSub) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("subOrderId", tbillFreshSub.getId());
		paramMap.put("courierType", tbillFreshSub.getCourierType());
		paramMap.put("courierNumber", tbillFreshSub.getCourierNumber());
		//调用service层
		Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
    		resultMap = billFreshSubService.updateSubStatus(paramMap);
    		resultMap.put("isflag",true);
    		resultMap.put("info", "发货成功");
		} catch (Exception e) {
			resultMap.put("isflag",false);
			resultMap.put("info", "发货失败");
		}
        //返回json数据
      	return JSON.toJSON(resultMap);
	}
	
	
	/**
	 * @Description: 子订单退款
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
        	resultMap = billFreshSubService.freshSubRefund(refundRequest);
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
    
    /**
	 * @Description: 确认收货操作
	 * @Param:
	 * @return:Object
	 * @author:lifeng
	 * @time:2016年6月24日下午7:46:41
	 */
	@RequestLogging(name="子订单确认收货操作")
	@RequestMapping(value = "/confirmReceive")
	@ResponseBody
	public Object confirmReceive(HttpServletRequest request, TBillFreshSub tbillFreshSub, String id) {
		//获取登录的信息
		TUser user = (TUser)request.getSession(false).getAttribute("currentUs");//登录的用户名 
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("id", id);
		paramMap.put("operator", "(" + user.getUserId() + ")" + user.getUsername());
        try {
        	//调用service层
    		resultMap = billFreshSubService.confirmReceive(paramMap);
    		resultMap.put("success",true);
    		resultMap.put("msg", "订单完成");
		} catch (Exception e) {
			resultMap.put("success",false);
			resultMap.put("msg", "订单异常");
		}
        //返回json数据
      	return JSON.toJSON(resultMap);
	}

}
