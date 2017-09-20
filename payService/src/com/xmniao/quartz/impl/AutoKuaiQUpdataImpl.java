package com.xmniao.quartz.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.PayConstants;
import com.xmniao.service.CommonService;
import com.xmniao.service.KuaiqianPayService;
/**
 * 定时任务
 * 快钱 更新提现状态
 * @author zenghm
 *
 */
public class AutoKuaiQUpdataImpl extends AutoUpdateWithrad {

	/** 快钱服务  */
	@Autowired
	private KuaiqianPayService kuaiqianPayService;
	/** 公共服务  */
	@Autowired
	private CommonService commonService;
	/** 提现方式*/
	private String withdrawType = PayConstants.WITHDRAW_TYPE_KQ; 
    /** 支付平台号*/
	private String platform = PayConstants.WITHDRAW_PLATFORM_KQ;


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
		
		return  kuaiqianPayService.queryByOrder("" + m.get("id"));
	}
}
