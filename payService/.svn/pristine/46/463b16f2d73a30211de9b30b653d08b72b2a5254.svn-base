package com.xmniao.service;

import java.util.Map;

public interface LlPayService {
	/**
	 * 连连支付退款接口
	 * @param refundId
	 * @param orderId
	 * @param amount
	 * @param payDate
	 * @param refundDate
	 * @return
	 */
	public Map<String, String> llPayRefund(String refundId, String orderId,	double amount, String payDate, String refundDate,int serviceType);
	
	/**
	 * 验证签名
	 * @param map
	 * @return
	 */
	boolean verifySign(Map<String,String> map);
	
	/**
	 * 退款查询接口
	 * @param refundId	//商户退款流水号
	 * @param llRefundNo	//连连退款流水号
	 * @param payDate	//商户退款时间
	 * @return
	 */
	public Map<String, String> llPayRefundQuery(String refundId,String llRefundNo, String payDate);
	
	/**
	 * 连连 代付接口
	 */
	public Map<String, String> withdrawMoney(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 连连 代付查询
	 */
	public Map<String, String> withdrawMoneyQuery(String orderId) throws Exception;
}
