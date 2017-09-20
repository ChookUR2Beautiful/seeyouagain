package com.xmniao.xmn.core.billmanagerment.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
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
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.billmanagerment.service.AllBillService;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：NotPayBillController
 * 
 * 类描述： 查询所有订单(待支付)
 * 
 * 创建人： huangpingqiang
 * 
 * 创建时间：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Controller
@RequestMapping(value = "billmanagerment/notpaybill")
public class NotPayBillController extends BaseController {
	
	@Autowired
	private AllBillService allBillService;
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "billmanagerment/notPayBillList";
	}
	
	/**
	 * 获取订单列表
	 * 
	 * @param list
	 * @return Object
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(Bill bill) {
		this.log.info("AllBillController-->list bill=" + bill);
		Pageable<Bill> pageable = new Pageable<Bill>(bill);
		pageable = allBillService.getBillNotPayList(bill);
		return pageable;
	}
	
	/**
	 * 导出订单列表
	 * @param bill
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "export")
	public void export(Bill bill, HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException {
		bill.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", allBillService.getNotPayBillListForExport(bill));
		doExport(request, response, "billmanagerment/notPayallBill.xls", params);
	}
	
	
	/**
	 * 
	 * 查看页面初始化
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "/view/init")
	public ModelAndView view(HttpServletRequest request, @RequestParam("bid") String bid) {
		ModelAndView modelAndView = new ModelAndView("billmanagerment/billDetail");
		try {
			Bill bill = allBillService.getBillObject(new Long(bid));
			this.log.info(bill);
			modelAndView.addObject("bill", bill);
		} catch (NumberFormatException e) {
			this.log.error("页面初始化异常", e);
		} finally {
			return modelAndView;
		}
	}

	/**
	 * 
	 * 订单金额详情
	 * 
	 * @author：dongjietao
	 */
	@RequestMapping(value = "/init/orderCmount")
	public ModelAndView orderCmount(HttpServletRequest request, @RequestParam("bid") String bid) {
		ModelAndView modelAndView = new ModelAndView("billmanagerment/billDetail.jsp");
		try {
			Bill bill = allBillService.getOrderCmount(new Long(bid));
			this.log.info(bill);
			modelAndView.addObject("bill", bill);
		} catch (NumberFormatException e) {
			this.log.error("页面初始化异常", e);
		} finally {
			return modelAndView;
		}
	}

}
