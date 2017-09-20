package com.xmniao.service;

import java.util.Map;

import com.xmniao.thrift.ledger.FailureException;

public interface WPayService {
	
	/**
	 * 微信退款请求
	 * @param refundId
	 * @param orderId
	 * @param refundAmount
	 * @param orderAmount
	 * @return
	 * @throws FailureException
	 */
	public Map<String, String> wPayRefund(String refundId, String orderId,
			double refundAmount, double orderAmount) throws FailureException;
		
	/**
	 * 微信退款状态查询
	 * @param id - 查询的ID
	 * @param type
	 * type=1 微信支付交易号
	 * type=2微信退款交易号
	 * type=3支付时，商户平台提交到微信支付的商户订单号
	 * type=4退款时，商户平台提交到微信退款的退款流水号
	 * (个人测试时，发现type=3,4时，查询失败)
	 * @return
	 */
	public Map<String,String> QueryWPayRefund(String id,int type);
	
	/**
	 * 获取寻蜜鸟微信账号信息
	 */
	public String getWxPartner();
}
