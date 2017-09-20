package com.xmniao.service;

import java.util.Map;

import com.xmniao.entity.PayRefund;

/**
 * 汇付退款Service
 * @author ChenBo
 *
 */
public interface PnrRefundService {
	
	/**
	 * 汇付退款业务
	 * @param refundId 退款ID
	 * @param payId  支付ID
	 * @param orderAmount 退款金额
	 * @param refundNote 订单详情
	 * @return 
	 */
	public Map<String, String> pnrPayRefund(String refundId, String payId,
			double orderAmount, String refundNote,int serviceType);
	
	/**
	 * 查询该汇付退款订单状态
	 * @param refundId
	 * @return
	 */
	public Map<String,String> pnrPayRefundQuery(String refundId);

	/**
	 * 根据退款ID，查询退款信息
	 * @param refundId
	 * @return
	 */
	public PayRefund getPayRefundByRefundId(String refundId);
	
}
