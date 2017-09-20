package com.xmniao.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.common.AgentPayRequest;
import com.xmniao.dao.UpdateWithdrawalsRecordStateMapper;
import com.xmniao.service.CommonService;

/**
 * 代发接口统一入口工具类
 * 
 * @author DongJieTao
 * 
 */
@Service("WithdrawMoneyServiceTools")
public class WithdrawMoneyServiceTools {
    private final Logger log = Logger.getLogger(WithdrawMoneyServiceTools.class);

    @Autowired
    private UpdateWithdrawalsRecordStateMapper updateWithdrawalsRecordStateMapper;

    /** 公共服务 */
    @Autowired
    private CommonService commonService;

    @Resource(name = "IP_NUMBER")
    private String IP_NUMBER;

    @Resource(name = "PORT")
    private int PORT;

    /**
     * 组装汇付天下代发参数
     * 
     * @param mapParams
     * @param userType
     * @return
     */
    public static Map<String, Object> putTheDataH(Map<String, Object> mapParams, int userType) {

	Map<String, Object> map = new HashMap<String, Object>();
	map.put("SubAcctId", "null");
	map.put("OrdId", String.valueOf(mapParams.get("id")));
	map.put("OrdAmt", String.valueOf(mapParams.get("amount")));
	map.put("MerPriv", String.valueOf(userType));
	map.put("AcctName", mapParams.get("fullname"));
	map.put("BankId", mapParams.get("abbrev")); // 银行代码
	map.put("AcctId", String.valueOf(mapParams.get("account")));
	map.put("InSubAcctId", "null");
	map.put("CertId", "null");
	/*
	 * if(String.valueOf(mapParams.get("ispublic")).equals("0")){//0表示对公 1表示对私 map.put("PrType", "C");//对私
	 * 开发文档标示错误 }else{
	 */
	map.put("PrType", "P");// 对公对私统一为P
	// }

	return map;
    }

    /**
     * 融宝代发参数组装
     * 
     * @param mapParams
     * @param ReURL
     * @param userType
     * @return
     */
    public static AgentPayRequest putTheDataR(Map<String, Object> mapParams, String userType) {

	AgentPayRequest agentPayRequest = new AgentPayRequest();

	agentPayRequest.setBatch_amount(String.valueOf(mapParams.get("amount")));
	agentPayRequest.setBatch_count("1");
	agentPayRequest.setBatch_no(String.valueOf(mapParams.get("id"))+"_"+userType);
	agentPayRequest.setPay_type("1");

	StringBuffer sb = new StringBuffer();

	sb.append(userType + ",").append(String.valueOf(mapParams.get("account")) + ",")
	.append(String.valueOf(mapParams.get("fullname")) + ",").append(String.valueOf(mapParams.get("bank")) + ",")
	.append(mapParams.get("bankname") + ",").append(mapParams.get("bankname") + ",");
	if (String.valueOf(mapParams.get("ispublic")).equals("1")) {
	    sb.append("公,");
	} else {
	    sb.append("私,");
	}
	sb.append(String.valueOf(mapParams.get("amount")) + ",").append("CNY,")
	.append(mapParams.get("province") + ",").append(mapParams.get("cityname") + ",")
		.append(String.valueOf(mapParams.get("mobileid") == null ? "" : mapParams.get("mobileid")) + ",");

	String idtype = String.valueOf(mapParams.get("idtype"));
	switch (idtype) { // 证件级证件号码组合
	case "1": {
	    sb.append("身份证,").append(String.valueOf(mapParams.get("identity") == null ? "" : mapParams.get("identity")) + ",");
	}
	    break;
	case "2": {
	    sb.append("护照,").append(String.valueOf(mapParams.get("identity") == null ? "" : mapParams.get("identity")) + ",");
	}
	    break;
	case "3": {
	    sb.append("驾驶证,").append(String.valueOf(mapParams.get("identity") == null ? "" : mapParams.get("identity")) + ",");
	}
	    break;
	default: {
	    sb.append(",").append(",");
	}
	    break;
	}
	sb.append(",").append(String.valueOf(String.valueOf(mapParams.get("id"))+"_"+userType) + ",")// 订单号
		.append(String.valueOf(mapParams.get("purpose")));
	agentPayRequest.setContent(sb.toString());

	return agentPayRequest;
    }

    public static Map<String, Object> putTheDataU(Map<String, Object> mapParams, int userType) {// U付代发参数组装
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("orderId", mapParams.get("id"));
	map.put("orderAmount", mapParams.get("amount"));
	map.put("bankBrhname", mapParams.get("bankname"));
	map.put("recvUserName", mapParams.get("fullname"));
	map.put("recvAccount", mapParams.get("account"));
	map.put("userType", userType);
	map.put("ispublic", mapParams.get("ispublic"));
	String abbrev = (String) mapParams.get("abbrev");
	// u付 银行代码 不一致问题
	map.put("recvGateId", (abbrev != null && abbrev.equals("BOCOM") ? "COMM" : abbrev));
	return map;
    }

    /**
     * 根据订单号获取单笔记录
     * 
     * @param orderNumber
     * @param userType
     * @return
     */
    public Map<String, Object> getOrderData(String orderNumber, int userType) {
	log.info("方法-->获取数据体 getOrderData-->orderNumber:" + orderNumber);

	Map<String, Object> paramsMap = commonService.getWithdrawInfoByNumUsertype(orderNumber, userType);
	Double OrdAmt = Double.valueOf(String.valueOf((paramsMap.get("amount"))))
		+ Double.valueOf(String.valueOf((paramsMap.get("balance"))))
		+ Double.valueOf(String.valueOf((paramsMap.get("commision"))))
		+ Double.valueOf(String.valueOf((paramsMap.get("income"))));
	String ordAmt = String.format("%.2f", OrdAmt);
	paramsMap.put("amount", ordAmt);
	return paramsMap;
    }
}
