package com.xmniao.quartz.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.common.PayConstants;
import com.xmniao.service.CommonService;
import com.xmniao.service.TongPayService;
import com.xmniao.tonglian.AipgRsp;
import com.xmniao.tonglian.QTDetail;
import com.xmniao.tonglian.QTransRsp;
/**
 * 定时任务
 * 通联  更新提现状态
 * @author zenghm
 *
 */
public class AutoTongLUpdataImpl extends AutoUpdateWithrad{

	

	/** U付服务  */
	@Autowired
	private TongPayService tongPayService;
	/** 公共服务  */
	@Autowired
	private CommonService commonService;
	
	private String withdrawType = PayConstants.WITHDRAW_TYPE_TL; 

	private String platform = PayConstants.WITHDRAW_PLATFORM_TL;

	@Override
	protected Map<String, String> getParameter() {
		Map<String,String> withradType = new HashMap<String,String>();
		withradType.put("withdrawType",withdrawType);
		withradType.put("platform", platform);
		return withradType;
	}

	@Override
	protected Map<String, String> queryOrder(Map<String, Object> m,int userType) {
		
		Map<String,String> resultMap = new HashMap<String,String>();
		String usertype1="";
		int table = commonService.getTableByUsertype(userType);
		if (table==CommonService.WITHRAW_RECORD) {
			usertype1 = String.valueOf(m.get("cash"));
		}else{
			usertype1 = "4";
		}
		AipgRsp aipgrsp = tongPayService.queryTradeNew(m.get("id")+"_"+usertype1);
		resultMap.put("platform_code", m.get("id")+"_"+usertype1);
		resultMap.put("usertype", usertype1);
		
		
		if ("0000".equals(aipgrsp.getINFO().getRET_CODE())) {
			QTransRsp qrsq = (QTransRsp) aipgrsp.getTrxData().get(0);
			QTDetail detail = (QTDetail) qrsq.getDetails().get(0);
            String retCode = detail.getRET_CODE();
				if ("0000".equals(retCode)) {
					resultMap.put("status", PayConstants.WITHDRAW_STATUS_SUCCESS);
	    			resultMap.put("Message",PayConstants.WITHDRAW_MSG_SUCCESS);
					log.info("提现成功  -------返回说明:交易成功  ");
				} else if("4000".equals(retCode)){
					resultMap.put("status", PayConstants.WITHDRAW_STATUS_PROCESS);
	    			resultMap.put("Message", PayConstants.WITHDRAW_MSG_PROCESS);
				}else{	
					resultMap.put("status",PayConstants.WITHDRAW_STATUS_FAIL);
					resultMap.put("Message", PayConstants.WITHDRAW_MSG_FAIL+","+detail.getERR_MSG());
					log.info("提现失败 ------返回说明:" + detail.getERR_MSG() + "  ");
					
				}
	}else{
		
		resultMap.put("status","0");
		resultMap.put("Message", aipgrsp.getINFO().getERR_MSG());
		log.info("提现失败 ------返回说明:" + aipgrsp.getINFO().getERR_MSG());
		
	}
		
    return resultMap;
		
  }

}
