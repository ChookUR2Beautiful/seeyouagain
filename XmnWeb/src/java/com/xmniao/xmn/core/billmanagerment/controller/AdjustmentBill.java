package com.xmniao.xmn.core.billmanagerment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xmniao.xmn.core.base.BaseController;
import com.xmniao.xmn.core.base.Pageable;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.billmanagerment.entity.AdjustApply;
import com.xmniao.xmn.core.billmanagerment.service.AdjustmentBillService;
import com.xmniao.xmn.core.util.handler.annotation.RequestLogging;

/**
 * 
 * @author dong'jietao 
 * @date 2015年7月3日 上午11:32:46
 * @TODO 调单
 */
@RequestLogging(name="调单处理")
@Controller
@RequestMapping(value = "billmanagerment/adjustmentbill")
public class AdjustmentBill extends BaseController {
	@Autowired
	private AdjustmentBillService adjustmentBillService;
	
	/**
	 * @author dong'jietao 
	 * @date 2015年7月3日 上午11:33:16
	 * @TODO 调单初始化页面
	 * @return
	 */
	@RequestMapping(value = "init")
	public String init() {
		return "billmanagerment/adjustmentbillList";
	}

    /**
     * @author dong'jietao 
     * @date 2015年7月3日 上午11:33:05
     * @TODO 调单List
     * @param tAdjustApply
     * @return
     */
	@RequestMapping(value = "init/list")
	@ResponseBody
	public Object list(AdjustApply adjustApply) {
		this.log.info("AllBillController-->list bill=" + adjustApply);
		Pageable<AdjustApply> pageable = new Pageable<AdjustApply>(adjustApply);
		adjustmentBillService.putListPage(adjustApply, pageable);
		return pageable;
	}

	/**
	 * @author dong'jietao 
	 * @date 2015年7月3日 下午4:03:27
	 * @TODO 调单审核
	 * @param adjustApply
	 * @return
	 */
	@RequestLogging(name="调单审核")
	@RequestMapping(value = "update")
	@ResponseBody
	public Object update(AdjustApply  adjustApply) {
		Resultable resultable =new Resultable();
		adjustmentBillService.toExamine(adjustApply, resultable);
		return resultable;
	}
}
