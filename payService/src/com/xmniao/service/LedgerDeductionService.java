/**
 * 
 */
package com.xmniao.service;

import java.util.List;
import java.util.Map;

import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.ResponseData;

/**
 * 
 * 
 * 项目名称：payService
 * 
 * 类名称：LedgerDeductionService
 * 
 * 类描述： 分账扣回Service
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年7月28日 下午6:24:12 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface LedgerDeductionService {

	/**
	 * 
	 * @Title: deductionsXmnOrderLedger 
	 * @Description:消费订单分账金额扣回
	 */
	public ResponseData deductionsXmnOrderLedger(Map<String,String> param) throws FailureException;

	/**
	 * 
	 * @Title: deductionsOfflineOrderLedger 
	 * @Description:线下积分订单分账金额扣回
	 */
	ResponseData deductionsOfflineOrderLedger(String orderId) throws FailureException;

	/**
	 * 
	 * @Title: deductionsOnlineOrderLedger 
	 * @Description:线上积分订单分账金额扣回
	 */
	ResponseData deductionsOnlineOrderLedger(String orderId) throws FailureException;
}
