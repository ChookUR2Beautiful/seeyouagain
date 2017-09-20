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
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.billmanagerment.service.AllBillService;
import com.xmniao.xmn.core.billmanagerment.service.ClickFarmingBillService;
import com.xmniao.xmn.core.billmanagerment.util.BillConstants;
import com.xmniao.xmn.core.util.PageConstant;
@Controller
@RequestMapping(value = "billmanagerment/clickfarmingbill")
public class ClickFarmingBillController extends BaseController {

	
	@Autowired
	private AllBillService allBillService;
	
	@Autowired
	private ClickFarmingBillService clickFarmingBillService;
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "billmanagerment/clickfarmingbillList";
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
		Integer status=bill.getStatus();
		if(null != status && 0!=status){ //单个状态查询
			int s[]={bill.getStatus()};
			bill.setStrStatus(s);
			bill.setQueryFlag(1);
		}else if(null != status && 0==status){//非退款其它状态
			int s[]=BillConstants.REFUND_OTHER_STATUS;//1,2,3,6,8,9
			bill.setStrStatus(s);
			bill.setQueryFlag(2);
		}else{//查询除待支付所有状态
			int s[]=BillConstants.REFUND_ALL_STATUS;//1,2,3,4,5,6,7,8,9,9,10,11,12,13
			bill.setStrStatus(s);
			bill.setQueryFlag(3);
		}
		bill.setThirdUidSwitch("open");
		pageable = clickFarmingBillService.getClickFarmingBillList(bill);
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
		bill.setOrder(PageConstant.NOT_ORDER);
		bill.setLimit(-1);
		Map<String, Object> params = new HashMap<String, Object>();
		Integer status=bill.getStatus();
		if(null != status && 0!=status){ //单个状态查询
			int s[]={bill.getStatus()};
			bill.setStrStatus(s);
			bill.setQueryFlag(1);
		}else if(null != status && 0==status){//非退款其它状态
			int s[]=BillConstants.REFUND_OTHER_STATUS;//1,2,3,6,8,9
			bill.setStrStatus(s);
			bill.setQueryFlag(2);
		}else{//查询除待支付所有状态
			int s[]=BillConstants.REFUND_ALL_STATUS;//1,2,3,4,5,6,7,8,9,9,10,11,12,13
			bill.setStrStatus(s);
			bill.setQueryFlag(3);
		}
		
		//doThreadExport(request, response, "billmanagerment/allBill.xls", allBillService.getBillCount(bill), callback, allBillService, bill);
		bill.setThirdUidSwitch("open");
		params.put("list", clickFarmingBillService.getBList(bill));
		doExport(request, response, "billmanagerment/clickfarmingbill.xls", params);
	}
}
