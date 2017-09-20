package com.xmniao.quartz.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.PayConstants;
import com.xmniao.service.CommonService;
import com.xmniao.service.ShengPayService;
/**
 * 定时任务
 * 盛付通 更新提现状态
 * @author zenghm
 *
 */
public class AutoShengFTUpdataImpl extends AutoUpdateWithrad {

	/** 盛付通服务  */
	@Autowired
	private ShengPayService shengPayService;
	/** 公共服务  */
	@Autowired
	private CommonService commonService;
	/** 提现方式*/
	private String withdrawType = PayConstants.WITHDRAW_TYPE_SFT; 
    /** 支付平台号*/
	private String platform = PayConstants.WITHDRAW_PLATFORM_SFT;

	
	@Override
	protected Map<String, String> getParameter() {
		Map<String,String> withradType = new HashMap<String,String>();
		withradType.put("withdrawType",withdrawType);
		withradType.put("platform", platform);
		return withradType;
	}

	@Override
	protected Map<String, String> queryOrder(Map<String, Object> m,int userType) {
		
		String usertype1="";
		int table = commonService.getTableByUsertype(userType);
		if (table==CommonService.WITHRAW_RECORD) {
			usertype1 = String.valueOf(m.get("cash"));
		}else{
			usertype1 = "4";
		}
	
		return  shengPayService.shengPayQuery(m.get("id")+""+usertype1);
	}
}
