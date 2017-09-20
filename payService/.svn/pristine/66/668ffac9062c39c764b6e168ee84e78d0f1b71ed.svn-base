package com.xmniao.quartz.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.PayConstants;
import com.xmniao.service.ChinaPnRPay;
import com.xmniao.service.CommonService;
/**
 * 定时任务
 * 汇付 更新提现状态
 * @author zenghm
 *
 */
public class AutoHuiFUpdataImpl extends AutoUpdateWithrad {

	/** 汇付服务  */
	@Autowired
	private ChinaPnRPay chinaPnRPay;
	/** 公共服务  */
	@Autowired
	private CommonService commonService;
	/** 提现方式*/
	private String withdrawType = PayConstants.WITHDRAW_TYPE_HF; 
    /** 支付平台号*/
	private String platform = PayConstants.WITHDRAW_PLATFORM_HF;


	public static void main(String[] args) {

	}

	
	@Override
	protected Map<String, String> getParameter() {
		Map<String,String> withradType = new HashMap<String,String>();
		withradType.put("withdrawType",withdrawType);
		withradType.put("platform", platform);
		return withradType;
	}

	@Override
	protected Map<String, String> queryOrder(Map<String, Object> m,int userType) {
		
		Double OrdAmt = Double.valueOf(String.valueOf((m.get("amount"))))
				+ Double.valueOf(String.valueOf((m.get("balance"))))
				+ Double.valueOf(String.valueOf((m.get("commision"))))
				+ Double.valueOf(String.valueOf((m.get("income"))));
			String ordAmt = String.format("%.2f", OrdAmt);
		return chinaPnRPay.queryOneByOrder(m.get("id").toString(),ordAmt);
	}
}
