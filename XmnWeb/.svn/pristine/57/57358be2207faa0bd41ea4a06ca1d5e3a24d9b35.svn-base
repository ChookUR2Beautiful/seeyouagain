package com.xmniao.xmn.core.dataDictionary.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_cooperation.util.PartnerConstants;
import com.xmniao.xmn.core.dataDictionary.entity.TBankList;
import com.xmniao.xmn.core.dataDictionary.service.BankListService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：BankListController
 * 
 * @类描述：银行管理
 * 
 * @创建人：zhang'zhiwen
 * 
 * @创建时间 ：2015年8月12日 上午10:26:02
 * 
 */
@Controller
@RequestLogging(name = "数据字典管理 >> 银行管理")
public class BankListController extends BaseController {

	private String viewFolder = "dataDictionary/bankList/";
	private final String initUrl = "dataDictionary/bank/init";
	private final String initListUrl = "dataDictionary/bank/init/list";
	private final String addInitUrl = "dataDictionary/bank/add/init";
	private final String addUrl = "dataDictionary/bank/add";
	private final String updateInitUrl = "dataDictionary/bank/update/init";
	private final String updateUrl = "dataDictionary/bank/update";

	@Autowired
	private BankListService bankListService;

	/**
	 * 银行查询列表初始页面
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年8月12日 下午4:34:01
	 * @return
	 */
	@RequestMapping(value = initUrl)
	public String init() {
		return viewFolder + "init";
	}

	/**
	 * 银行查询列表
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年8月12日 下午4:34:37
	 * @param bankList
	 * @return
	 */
	@RequestMapping(value = initListUrl)
	@ResponseBody
	public Object initList(TBankList bankList) {
		Pageable<TBankList> pageable = new Pageable<TBankList>(bankList);
		bankListService.getListPage(bankList, pageable);
		return pageable;
	}

	/**
	 * 添加银行初始化页面
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年8月12日 下午4:34:51
	 * @return
	 */
	@RequestMapping(value = addInitUrl)
	public String addInit() {
		return viewFolder + "edit";
	}

	/**
	 * 添加银行
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年8月12日 下午4:35:21
	 * @param bankList
	 * @return
	 */
	@RequestMapping(value = addUrl)
	@RequestLogging(name = "添加银行")
	@ResponseBody
	public Resultable add(TBankList bankList) {
		Resultable resultable;
		bankList.setStatus(0);
		bankListService.add(bankList);
		resultable = new Resultable(true, "添加成功！");
		String[] couponInfo = { "银行名称为", bankList.getBankName(), "添加", "添加" };
		bankListService.fireLoginEvent(couponInfo,
				PartnerConstants.FIRELOGIN_NUMA);
		return resultable;
	}

	/**
	 * 修改银行初始化页面
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年8月12日 下午4:35:28
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = updateInitUrl)
	public String updateInit(Integer id, Model model) {
		model.addAttribute("bank", bankListService.getObject(id.longValue()));
		return viewFolder + "edit";
	}

	/**
	 * 修改银行
	 * 
	 * @author zhang'zhiwen
	 * @date 2015年8月12日 下午4:35:59
	 * @param bankList
	 * @return
	 */
	@RequestMapping(value = updateUrl)
	@RequestLogging(name = "修改银行")
	@ResponseBody
	public Resultable update(TBankList bankList) {
		Resultable resultable = new Resultable();
		bankList.setStatus(0);
		bankList.setSdate(new Date());
		String[] couponInfo = { "银行名称为", bankList.getBankName(), "修改", "修改" };
		if (bankListService.update(bankList) == 1) {
			resultable.setSuccess(true);
			resultable.setMsg("修改银行成功");

			bankListService.fireLoginEvent(couponInfo,
					PartnerConstants.FIRELOGIN_NUMA);
		} else {
			resultable.setSuccess(false);
			resultable.setMsg("修改银行失败");
			bankListService.fireLoginEvent(couponInfo,
					PartnerConstants.FIRELOGIN_NUMB);
		}
		return resultable;
	}

}
