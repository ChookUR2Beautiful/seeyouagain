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
import com.xmniao.xmn.core.base.export.QueryCallback;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.billmanagerment.service.AllBillService;
import com.xmniao.xmn.core.billmanagerment.util.BillConstants;
import com.xmniao.xmn.core.business_cooperation.service.JointService;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.common.service.AreaService;
import com.xmniao.xmn.core.marketingmanagement.dao.BillBargainDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TBillBargain;
import com.xmniao.xmn.core.util.PageConstant;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：AllBillController
 * 
 * 类描述： 查询所有订单
 * 
 * 创建人： huangpingqiang
 * 
 * 创建时间：2014年11月25日17时39分38秒
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */

@Controller
@RequestMapping(value = "billmanagerment/allbill")
public class AllBillController extends BaseController {

	@Autowired
	private AllBillService allBillService;
	@Autowired
	private JointService jointService;
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private BillBargainDao billBargainDao;
	
	
	private QueryCallback<Bill> callback = new QueryCallback<Bill>() {
		@Override
		public List<Bill> query(Object service,Bill entity, int page, int limit) {
			if(service instanceof AllBillService){
				try {
					entity.setOrder(PageConstant.NOT_ORDER);
					entity.setPage(page);
					entity.setLimit(limit);
					return ((AllBillService)service).getBList(entity);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}
		
	};
	
	/**
	 * 
	 * init(初始化列表页面)
	 * 
	 * @author：huangpingqiang
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "billmanagerment/allBillList";
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
		pageable = allBillService.getBillList(bill);
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
		params.put("list", allBillService.getBList(bill));
		doExport(request, response, "billmanagerment/allBill.xls", params);
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
		ModelAndView modelAndView = new ModelAndView("billmanagerment/orderAmount");
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
	
	/**
	 *  根据订单ID查询爆品订单
	 */
	@RequestMapping(value = "billBargain/billBargainInit")
	public ModelAndView bargainProductByBidInit(@RequestParam("bid")Long bid){
		ModelAndView modelAndView = new ModelAndView("marketingManagement/billBargainList");
		modelAndView.addObject("bid", bid);
		modelAndView.addObject("requestInit", "billBargain/billBargainInit/list");
		return modelAndView;
	}
	
	@RequestMapping(value = "billBargain/billBargainInit/list")
	@ResponseBody
	public Object bargainProductByBidList(TBillBargain tBillBargain){
		Pageable<TBillBargain> pageable = new Pageable<TBillBargain>(tBillBargain);
		pageable.setContent(billBargainDao.getList(tBillBargain));
		pageable.setTotal(billBargainDao.count(tBillBargain));
		return pageable;
	}
	
	/**
	 * 查看钱包
	 */
	@RequestMapping(value = "viewWalletInit")
	public ModelAndView viewWallet(ModelAndView model,
			@RequestParam("sellerid") Integer sellerid) {
			model.setViewName("businessman/viewWallet");
			model.addObject("wallet", jointService.getWallet(sellerid.toString(), String.valueOf(SellerConstants.USER_TYPE_B)));
		return model;
	}
	
/*	@RequestMapping(value = "init/ld" ,method = RequestMethod.POST)
	public void getLd(HttpServletRequest request, HttpServletResponse response) throws IOException {
			Long rid  =  ResultUtil.getCurrentUser(request).getRoleId();
			Long auid = AuthorityAreaHandler.getInstance().getUrlByAuthorityId("billmanagerment/allbill/init/list");
			response.getWriter().print(areaService.getLd(rid, auid));
	}*/
	
	/**
	 * 直播分账
	 */
	@RequestMapping(value = "liveLedger")
	@ResponseBody
	public Object liveLedger(ModelAndView model,@RequestParam("bids") String bids) {
		Resultable resultable = new Resultable();
		
		Map<String,String> paraMap = new HashMap<String,String>();
		StringBuffer failMsg = new StringBuffer();
		String[] bidArray = bids.split(",");
		paraMap.put("source", "0");
		for(String bid:bidArray){
			paraMap.put("bid",bid);
			Map<String,String> resultMap=null;
			try{
				resultMap = allBillService.liveLedger(paraMap);
			}catch(Exception e){
				log.error("手动分账失败",e);
			}
			if(resultMap!=null && resultMap.get("recode").equals("100")){
				
			}else{
				failMsg.append(bid+" ");
			}
		}
		
		if(failMsg.length()==0){
			failMsg.append("订单手动分账成功");
			resultable.setSuccess(true);
			resultable.setMsg(failMsg.toString());
		}else{
			failMsg.append("订单手动分账失败");
			resultable.setSuccess(false);
			resultable.setMsg(failMsg.toString());
		}
		
		return resultable;
	}
}