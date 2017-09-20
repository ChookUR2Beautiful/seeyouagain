package com.xmniao.service;

import java.util.Map;

import com.xmniao.thrift.ledger.FailureException;

public interface FailOrderService {
	
	/**
	 * 取消报障订单
	 * @param orderId
	 * @return
	 */
	Map<String,String> recoveryFailOrder(String orderId)throws FailureException;
}
