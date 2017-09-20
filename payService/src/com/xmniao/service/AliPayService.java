package com.xmniao.service;

import java.util.Map;

import com.xmniao.entity.PayRefund;
import com.xmniao.thrift.ledger.FailureException;

/**
 * 支付宝Service
 * @author ChenBo
 *
 */
public interface AliPayService {
	/**
	 * 构建支付宝退款HTML请求
	 * @param refundId  退款流水号
	 * @param orderId	支付宝支付号
	 * @param orderAmount	退款总金额
	 * @param refundNote	退款理由
	 * @return
	 * @throws FailureException
	 */
	public Map<String, String> aliPayRefund(String refundId, String orderId,
			double orderAmount, String refundNote,int serviceType);
	
	/**
	 * 验证支付宝的签名合法性
	 * @param params
	 * @return
	 */
	public boolean verify(Map<String, String> params);
	
	/**
	 * 支付宝退款异步回调处理
	 * @param params
	 * @param payRefund
	 * @return
	 */
	public int RefoundNotity(Map<String, String> params,PayRefund payRefund);
	
	/**
	 * 查询退款记录
	 * @param refundId
	 * @return
	 */
	public PayRefund GetRefund(String refundId);


	/**
	 * 支付宝退款批次号转成对应的退款流水号
	 * @param aliBatchNo 支付宝的退款批次号
	 * @return
	 */
	public String batchNoTorefundId(String aliBatchNo);
	
	/**
	 * 退款流水号转成对应的支付宝退款批次号
	 * @param refundId 退款记录表中的退款流水号
	 * @return
	 */
	public String refundIdTobatchNo(String refundId);
}
