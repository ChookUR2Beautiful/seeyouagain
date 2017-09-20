package com.xmniao.quartz.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.PayConstants;
import com.xmniao.service.CommonService;
import com.xmniao.service.LlPayService;

public class AutoLianlUpdataImpl extends AutoUpdateWithrad{

	/** 连连服务  */
	@Autowired
	private LlPayService llPayService;
	/** 公共服务  */
	@Autowired
	private CommonService commonService;
	/** 提现方式*/
	private String withdrawType = PayConstants.WITHDRAW_TYPE_LL; 
    /** 支付平台号*/
	private String platform = PayConstants.WITHDRAW_PLATFORM_LL;
	
	@Override
	protected Map<String, String> getParameter() {
		Map<String,String> withradType = new HashMap<String,String>();
		withradType.put("withdrawType",withdrawType);
		withradType.put("platform", platform);
		return withradType;
	}

	@Override
	protected Map<String, String> queryOrder(Map<String, Object> m, int userType) {
		
		String usertype1="";
		int table = commonService.getTableByUsertype(userType);
		if (table==CommonService.WITHRAW_RECORD) {
			usertype1 = String.valueOf(m.get("cash"));
		}else{
			usertype1 = String.valueOf(PayConstants.USERTYPE_JOINT);
		}
		Map<String,String> map=null;
		try {
			String orderId=m.get("id")+"_"+usertype1;
			map = llPayService.withdrawMoneyQuery(orderId);
			map.put("usertype", usertype1);
			map.put("platform_code", orderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

}
