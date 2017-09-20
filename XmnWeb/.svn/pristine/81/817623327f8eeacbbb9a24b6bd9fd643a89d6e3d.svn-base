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
import com.xmniao.xmn.core.fresh.entity.Texpress;
import com.xmniao.xmn.core.fresh.service.BillFreshService;
import com.xmniao.xmn.core.thrift.service.payRefundService.RefundRequest;
import com.xmniao.xmn.core.util.JsonUtil;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
/**
 * 
 *@ClassName:BillFreshController
 *@Description:生鲜订单管理接口
 *@author hls
 *@date:2016年3月10日下午6:04:43
 */
@RequestLogging(name="生鲜订单管理")
@Controller
@RequestMapping(value = "/fresh/order")
public class BillFreshController extends BaseController {
	
	
	@Autowired
	private BillFreshService billFreshService;
	
	/*
	 * 初始化页面
	 */
	@RequestMapping(value = "init")
	public String init(){
		return "fresh/order/orserlist";
	}
	
	/**
	 * @Title:getFreshList
	 * @Description:查询生鲜订单列表
	 * @param request
	 * @param response
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object getFreshList(TBillFresh tbillFresh){
		this.log.info("BillFreshController-->list tbillFresh=" + tbillFresh);
		Pageable<TBillFresh> pageable = new Pageable<TBillFresh>(tbillFresh);
		pageable.setContent(billFreshService.selectTBillFreshInfoList(tbillFresh));
		pageable.setTotal(billFreshService.tbillFreshInfoCount(tbillFresh));
		return pageable;
	}

	/**
	 * @Title:updateWtatus
	 * @Description:发货操作，更新发货状态及发货时间等
	 * @param request
	 * @param response
	 * @return Object
	 * @throw
	 */
	@RequestLogging(name="修改活动")
	@RequestMapping(value = "init/shipments")
	@ResponseBody
	public Object update(TBillFresh tbillFresh, HttpServletRequest request) {
		//获取请求参数
		String bid = request.getParameter("ddnumber");
		String express = request.getParameter("express");
		String logistics = request.getParameter("wlnumber");
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("bid", bid);
		paramMap.put("express", express);
		paramMap.put("logistics", logistics);
		//调用service层
		Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
    		resultMap = (Map<String, Object>) billFreshService.updateWstatus(paramMap);
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
	 * @Title:freshRefund
	 * @Description:生鲜订单退款
	 * @param refundRequest
	 * @return Object
	 * @throw
	 */
    @SuppressWarnings("finally")
	@RequestMapping(value = "/refund")
    @ResponseBody
    public Object freshRefund(RefundRequest refundRequest) {
    	Resultable resultable = null;
    	Map<String, String> resultMap = new HashMap<String, String>();
    	String msg = "";
    	String response = "";
        try {
        	resultMap = billFreshService.freshRefund(refundRequest);
        	msg = resultMap.get("Msg");
        	response = resultMap.get("response");
        	resultable = new Resultable(true, msg,response);
            this.log.info("生鲜订单退款成功");
        } catch (Exception e) {
        	resultable = new Resultable(false, "退款异常");
            this.log.error("生鲜订单退款异常", e);
        } finally{
      		return resultable;
        }
      
    }
    
    /**
     * @Title:view
     * @Description:查看页面初始化
     * @param request
     * @param bid
     * @return ModelAndView
     * @throw
     */
	@SuppressWarnings("finally")
	@RequestMapping(value = "/check")
	public ModelAndView view(HttpServletRequest request, @RequestParam("bid") String bid) {
		ModelAndView modelAndView = new ModelAndView("fresh/order/checkbillfresh");
		try {
			TBillFresh tbillFresh = billFreshService.getBillFresh(new Long(bid));
			modelAndView.addObject("tbillFresh", tbillFresh);
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
	public void export(TBillFresh tbillFresh, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		tbillFresh.getEdate();
		Map<String, Object> params = new HashMap<String, Object>();
		List<TBillFresh> list = billFreshService.getBillFreshForExport(tbillFresh);
		String productDetail = "";
		StringBuilder productDetailStr = null;
		Long bid = null;
		StringBuilder bidstr = new StringBuilder();
		for(int i = 0; i < list.size(); i++){
			bid = list.get(i).getBid();
			bidstr = bidstr.append(bid+",");
		}

		List<TBillFresh> billFreshList = billFreshService.getBillFreshList(bidstr.toString().split(","));

		for(int j = 0; j < billFreshList.size(); j++){
			productDetailStr = new StringBuilder();
			for (int k = 0; k < billFreshList.get(j).getProductList().size(); k++) {
				if(null != billFreshList.get(j).getProductList().get(k).getGoodsName() && null != billFreshList.get(j).getProductList().get(k).getSuttle()){
					productDetail = "名称:"+billFreshList.get(j).getProductList().get(k).getGoodsName()+"/"+billFreshList.get(j).getProductList().get(k).getSuttle()+"  "+billFreshList.get(j).getProductList().get(k).getGoodsNum()+"份";
					productDetailStr = productDetailStr.append(productDetail + ";\n");
				}
				billFreshList.get(j).setProductDetail(productDetailStr.toString());
			}
		}
		
		params.put("list",billFreshList);
		doExport(request, response, "fresh/freshBill.xls", params);
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
	public Object checkdata(TBillFresh tbillFresh) {
		Resultable resultable = null;
		try {
			Boolean flag =  billFreshService.checkdata(tbillFresh);
			if(flag){
				resultable = new Resultable(true, "操作成功");
			}else{
				resultable = new Resultable(false, "该时间没有订单数据");
			}
		} catch (Exception e) {
			resultable = new Resultable(false, "查询导出订单数据记录异常");
		} finally {
			return resultable;
		}
	}
	
	/**
	 * @Title:getCourierType
	 * @Description:得到物流公司
	 * @return Object
	 * @throw
	 */
	@RequestMapping(value = "/getCourierType")
	@ResponseBody
	public Object getCourierType(){
		 List<Texpress> expressList = billFreshService.getExpressList();
         return JsonUtil.toJSONString(expressList);
	}
	
}
