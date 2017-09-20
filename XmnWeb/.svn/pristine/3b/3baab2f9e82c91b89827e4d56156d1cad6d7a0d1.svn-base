/**
 * 
 */
package com.xmniao.xmn.core.xmermanagerment.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.billmanagerment.entity.Bill;
import com.xmniao.xmn.core.businessman.util.SellerConstants;
import com.xmniao.xmn.core.util.PageConstant;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;
import com.xmniao.xmn.core.xmermanagerment.entity.BWalletRecord;
import com.xmniao.xmn.core.xmermanagerment.entity.BXmerWalletRecord;
import com.xmniao.xmn.core.xmermanagerment.service.BXmerWalletRecordService;
import com.xmniao.xmn.core.xmermanagerment.service.BXmerWalletService;
import com.xmniao.xmn.core.xmermanagerment.service.FinanceService;
import com.xmniao.xmn.core.xmnpay.entity.Bwallet;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSystemDictTypeController
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-5-25上午11:19:14
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@RequestLogging(name="个人财务管理")
@Controller
@RequestMapping(value = "xmer/finance")
public class FinancialManagementController extends BaseController {
	
	@Autowired
	private FinanceService financeService;
	
	/**
	 * 导入寻蜜客钱包服务
	 */
	@Autowired
	private BXmerWalletRecordService xmerWalletRecordService;
	
	/**
	 * 初始化列表页面
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init(){
		return "xmermanagerment/finance/financeList";
	}
	
	/**
	 * 钱包列表分页查询
	 * @param xmerList
	 * @return
	 */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object initList(Bwallet wallet) {
		Pageable<Bwallet> pageable = new Pageable<Bwallet>(wallet);
		financeService.getWalletListPage(wallet, pageable);
		return pageable;
	}
	
	/**
	 * 钱包记录(押金，佣金)页面初始化
	 * @param xmerList
	 * @return
	 */
	@RequestMapping(value = "walletRecord/{moneyType}/init")
	public ModelAndView walletRecordInit(ModelAndView model,BXmerWalletRecord walletRecord,@PathVariable("moneyType") String moneyType) {
		model.setViewName("xmermanagerment/finance/walletRecordList");
		model.addObject("xid",walletRecord.getXid());
		
		/**
		 * 0 寻蜜鸟分账 1 转出  2 saas分账 3 寻蜜鸟分账返回 4 saas分账返回
		 */
		int rtype = -1;
		
		switch (moneyType) {
		case "commision"://佣金
			rtype=2;
			break;
		case "balance"://流水
			rtype=0;
			break;
		default:
			break;
		}
		model.addObject("rtype",rtype);
		model.addObject("order","1");//排序
		return model;
	}
	
	/**
	 * 
	 * 方法描述：寻蜜鸟会员钱包记录
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-9下午3:43:47
	 * @param walletRecord
	 * @return
	 */
	@RequestMapping(value="walletRecord/list")
	@ResponseBody
	public Object walletRecordList(BWalletRecord walletRecord){
		Pageable<BWalletRecord> pageable=new Pageable<BWalletRecord>(walletRecord);
		financeService.getWalletRecordListPage(walletRecord, pageable);
		return pageable;
	}
	
	/**
	 * 
	 * 方法描述：寻蜜客钱包记录
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-9下午3:44:17
	 * @param walletRecord
	 * @return
	 */
	@RequestMapping(value="xmerWalletRecord/list")
	@ResponseBody
	public Object xmerWalletRecordList(BXmerWalletRecord xmerWalletRecord){
		Pageable<BXmerWalletRecord> pageable=new Pageable<BXmerWalletRecord>(xmerWalletRecord);
		financeService.getXmerWalletRecordListPage(xmerWalletRecord, pageable);
		return pageable;
	}
	
	/**
	 * 消费金额明细页面初始化
	 * @param xmerList
	 * @return
	 */
	@RequestMapping(value = "sumMoney/init")
	public ModelAndView sumMoneyInit(ModelAndView model,Bwallet wallet) {
		model.setViewName("xmermanagerment/finance/sumMoneyList");
		model.addObject("uid",wallet.getUid());
		model.addObject("order","1");//排序
		return model;
	}
	
	/**
	 * 消费金额明细列表分页查询
	 * @param xmerList
	 * @return
	 */
	@RequestMapping(value = "sumMoney/list")
	@ResponseBody
	public Object sumMoneyinitList(Bill bill) {
		Pageable<Bill> pageable = new Pageable<Bill>(bill);
		financeService.getBillListPage(bill, pageable);
		return pageable;
	}
	
	/**
	 * 导出消费金额列表
	 * 
	 * @param seller
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "sumMoney/export")
	public void export(Bill bill, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		bill.setOrder(PageConstant.NOT_ORDER);
		bill.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("list", financeService.getBillListByUid(bill));
		doExport(request, response, "xmermanagerment/bill.xls", params);
	}
	
	/**
	 * 导出walletRecord列表
	 * 
	 * @param seller
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "walletRecord/{moneyType}/export")
	public void walletRecordExport(BWalletRecord walletRecord,@PathVariable("moneyType") String moneyType, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		walletRecord.setOrder(PageConstant.NOT_ORDER);
		walletRecord.setLimit(SellerConstants.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		//TODO 根据钱包id,recordType 导出记录
		params.put("list", financeService.getWalletRecordList(walletRecord));
		String fileName=moneyType+".xls";
		doExport(request, response, "xmermanagerment/"+fileName, params);
	}
	
	/**
	 * 
	 * 方法描述：导出寻蜜客钱包记录
	 * 创建人：  huang'tao
	 * 创建时间：2016-9-10下午2:36:56
	 * @param walletRecord
	 * @param request
	 * @param response
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@RequestMapping(value = "xmerWalletRecord/export")
	public void walletRecordExport(BXmerWalletRecord xmerWalletRecord, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException,
			IOException {
		xmerWalletRecord.setOrder(PageConstant.NOT_ORDER);
		xmerWalletRecord.setLimit(PageConstant.PAGE_LIMIT_NO);
		Map<String, Object> params = new HashMap<String, Object>();
		String fileName="";
		int rtype = xmerWalletRecord.getRtype();
		if(0==rtype){
			fileName="balance.xls";
		}else if(2==rtype){
			fileName="commision.xls";
		}
		//TODO
		params.put("list", xmerWalletRecordService.getList(xmerWalletRecord));
		doExport(request, response, "xmermanagerment/"+fileName, params);
	}
	
	
}
