package com.xmniao.service;

import java.util.Map;

import com.xmniao.thrift.ledger.FailureException;
import com.xmniao.thrift.ledger.ResponseData;

public interface ModifyService {
	/**
	 * 调单，修改对应钱包
	 */
	Map<String,String> modifyBalance(Map<String,String> map1,Map<String,String> map2) throws FailureException;
	
	/**
	 * 该单是否已经调单过
	 */
	boolean isModifyed(String orderId);
	
	/**
	 * 扣除分账金额
	 * @param deductionMap
	 * @return
	 * @throws FailureException
	 */
	Map<String, String> deductionsBalance(Map<String, String> deductionMap) throws FailureException;
	
	/**
	 * 已分账订单 分账金额扣回
	 * @Title: deductionsWallet 
	 * @Description:
	 */
	ResponseData deductionsWallet(String orderId) throws FailureException;
	
	
	/**
	 * 已分账订单 分账金额扣回(新)
	 * @param param
	 * @return
	 * @throws FailureException
	 */
	ResponseData deductionsWallet2(Map<String,String> param) throws FailureException;
}
