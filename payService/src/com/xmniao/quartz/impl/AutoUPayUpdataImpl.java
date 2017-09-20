package com.xmniao.quartz.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.PayConstants;
import com.xmniao.service.CommonService;
import com.xmniao.service.UPayService;
/**
 * 定时任务
 * U付 更新提现状态
 * @author zenghm
 *
 */
public class AutoUPayUpdataImpl extends AutoUpdateWithrad{

	

	/** U付服务  */
	@Autowired
	private UPayService uPayService;
	/** 公共服务  */
	@Autowired
	private CommonService commonService;
	
	private String withdrawType = PayConstants.WITHDRAW_TYPE_UF; 

	private String platform = PayConstants.WITHDRAW_PLATFORM_UF;

	@Override
	protected Map<String, String> getParameter() {
		Map<String,String> withradType = new HashMap<String,String>();
		withradType.put("withdrawType",withdrawType);
		withradType.put("platform", platform);
		return withradType;
	}

	@Override
	protected Map<String, String> queryOrder(Map<String, Object> m,int userType) {
		
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		String usertype1="";
		Date date = (Date) m.get("date");
		int table = commonService.getTableByUsertype(userType);
		if (table==CommonService.WITHRAW_RECORD) {
			usertype1 = String.valueOf(m.get("cash"));
		}else{
			usertype1 = "4";
		}
		Map<String, String> resultMap = uPayService.queryUPayResult(m.get("id")+"-"+usertype1,df.format(date));
		resultMap.put("usertype", usertype1);
		return resultMap;
	}

}
