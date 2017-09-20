package com.xmniao.service;

import java.util.Map;

import com.xmniao.tonglian.AipgRsp;

public interface TongPayService {

	/**
	 * 通联退款请求
	 * @param payId  
	 * @param payDate  支付订单提交时期时间格式："yyyyMMddHHmmss"
	 * @param orderAmount
	 * @return
	 */
	Map<String,String> tongPayRefund(String payId,String payDate,double orderAmount);
	
	/**
	 * 通联单笔订单查询
	 * @param payId
	 * @param payDate
	 * @return
	 */
	Map<String,String> tongPayRefundQuery(String payId,String refundId, String payDate);
	
	/**
	 * 通联代付
	 * @param paramMap
	 * @return
	 */
	public Map<String, String> tongPay(Map<String, Object> paramMap);
	/**
	 * 查询
	 * @param querySN
	 * @return
	 */
	public AipgRsp queryTradeNew(String querySN);
}
