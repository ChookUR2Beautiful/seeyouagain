package com.xmniao.service;

import java.util.Map;

public interface ReaPalService {

	/**
	 * 融宝支付退款服务接口
	 * @param refundId   退款流水号
	 * @param orderId    订单号
	 * @param orderAmount  退款金额
	 * @param refundNote   退款说明
	 * @return
	 */
	Map<String,String> reaPalRefund(String refundId, String orderId,
			double orderAmount, String refundNote);
}
