package com.xmniao.quartz.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.PayConstants;
import com.xmniao.service.CommonService;
import com.xmniao.service.impl.RongTWithdrawMoneyImpl;

public class AutoRongBQUpdataImpl extends AutoUpdateWithrad {
	
	@Autowired
	private RongTWithdrawMoneyImpl RongTWithdrawMoneyImpl;

	@Autowired
	private CommonService commonService;
	
	
	private String withdrawType = PayConstants.WITHDRAW_TYPE_RB; 

	private String platform = PayConstants.WITHDRAW_PLATFORM_RB;

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
		Map<String, Object> mapx = new HashMap<String, Object>();
		
		Map<String, String> resultMap = new HashMap<String, String>();
		resultMap.put("status", "0");
		resultMap.put("Message", "");
		resultMap.put("platform_code", "");
		
		
		int table = commonService.getTableByUsertype(userType);
		if (table==CommonService.WITHRAW_RECORD) {
			usertype1 = String.valueOf(m.get("cash"));
		}else{
			usertype1 = String.valueOf(PayConstants.USERTYPE_JOINT);
		}
		resultMap.put("usertype", usertype1);
		m.put("userType", usertype1);
		mapx = putTheData(m);
		try {
		    List<Map<String, Object>> ls = RongTWithdrawMoneyImpl.WithdrawMoneyQuery(mapx);// 提现查询
			mapx = (Map<String, Object>) ls.get(0);
			log.info("参数：" + mapx);
		} catch (Exception e1) {
			
			resultMap.put("Message", "融宝查询返回参数错误,获取错误");
			log.error("融宝查询返回参数错误,获取错误" ,e1);
			return resultMap;
		}
		String st = (String) mapx.get("status");
		String reason = (String) mapx.get("reason");
		if (st != null && st.equals("fail")) {
			resultMap.put("Message", "融宝查询失败！" + reason);
			log.info("查询失败！" + reason);
			
		} else {
			String tradeFeedbackcode = String.valueOf(mapx.get("tradeFeedbackcode"));
			String tradeNo = m.get("id")+"_"+usertype1;
			
			String message;
			if ("成功".equals(tradeFeedbackcode)) {
				
				message = PayConstants.WITHDRAW_MSG_SUCCESS;
				resultMap.put("status", PayConstants.WITHDRAW_STATUS_SUCCESS);
				resultMap.put("Message", message);
				resultMap.put("platform_code", tradeNo);
			} else if ("失败".equals(tradeFeedbackcode)) {
				
				message = PayConstants.WITHDRAW_MSG_FAIL+","+String.valueOf(mapx.get("tradeReason"));
				resultMap.put("status", PayConstants.WITHDRAW_STATUS_FAIL);
				resultMap.put("Message", message);
				resultMap.put("platform_code", tradeNo);
			} else {
				resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
				resultMap.put("Message", PayConstants.WITHDRAW_MSG_PROCESS);
			}
		}
		
		return resultMap;
	}
	
	
		public Map<String, Object> putTheData(Map<String, Object> paramsMap) {// 融宝提现查询条件
			Map<String, Object> map = new HashMap<String, Object>();
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			SimpleDateFormat sdx = new SimpleDateFormat("yyyyMMdd");
			String str = null;
			if (paramsMap.containsKey("date")) {

				try {
					str = sd.format(sd.parse(String.valueOf(paramsMap.get("date").toString())));
				} catch (ParseException e) {
					log.error("融宝自动更新异常" + e);
				}
				map.put("date", str);
			}
			if (paramsMap.containsKey("sdate")) {
				try {
					str = sd.format(sd.parse(String.valueOf(paramsMap.get("sdate").toString())));
				} catch (ParseException e) {
					log.error("融宝自动更新异常" + e);
				}
				map.put("date", str);
			}

			if (paramsMap.containsKey("id")) {
				map.put("batchCurrnum",paramsMap.get("id")+"_"+paramsMap.get("userType"));
			}
			return map;
		}
		
		public static void main(String[] args) {
			AutoRongBQUpdataImpl a = new AutoRongBQUpdataImpl();
			a.queryOrder(null, 0);
		}

}
