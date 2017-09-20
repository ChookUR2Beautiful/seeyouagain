/**
 * 
 */
package com.xmniao.service;

import java.util.List;
import java.util.Map;

import org.apache.thrift.TException;

import com.xmniao.thrift.ledger.FailureException;

/**
 * 
 * 分账Service
 * 
 * @author YangJing
 * 
 */
public interface LedgerService {

	/**
	 * 分账处理
	 * 
	 * @param param
	 * @throws FailureException
	 */
	Map<String, String> doLedger(Map<String, String> param) throws FailureException;
	
	/**
	 * 分账逻辑处理
	 * 
	 * @param paramMap
	 * @return
	 * @throws FailureException
	 */
	Map<String, String> ledger(List<Map<String, String>> paramMap) throws FailureException;
	
	/**
	 * saas订单分账处理
	 * 
	 * @param param
	 * @throws FailureException
	 */
	Map<String, String> doSaasLedger(Map<String, String> param) throws FailureException;
	
	/**
	 * Xmn消费分账处理
	 * 
	 * @param param
	 * @throws FailureException
	 */
	Map<String, String> doXmnLedger(Map<String, String> param) throws FailureException;
	
	/**
	 * 积分商城消费分账处理
	 * 
	 * @param param
	 * @throws FailureException
	 */
	Map<String, String> doMallLedger(Map<String, String> param) throws FailureException;
	
	/**
	 * 
	 * 方法描述：积分商城购买套餐分账
	 * 创建人：jianming  
	 * 创建时间：2017年8月11日 下午2:35:55   
	 * @param paramMap
	 * @return
	 * @throws FailureException 
	 * @throws TException 
	 */
	Map<String, String> doPackageLeger(Map<String, String> paramMap) throws FailureException, TException;
}
