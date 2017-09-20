package com.xmniao.service;

import java.util.Map;

import com.xmniao.entity.PayRefund;

/**
 * 盛付通支付服务接口
 * @author ChenBo
 *
 */
public interface ShengPayService {
	/**
	 * 盛付通退款
	 * @param refundId
	 * @param orderNumber
	 * @param money
	 * @param remarks
	 * @return
	 */
	Map<String,String> shengPayRefund(String refundId,String orderNumber,double money,String remarks,int serviceType);

	/**
	 * 盛付通查询
	 * @return
	 */
	Map<String,String> queryPayRefund();
	
	/**
	 * 生成加密后的字条串
	 * @param params
	 * @param key
	 * @return
	 */
	String shengSign(Map<String,String> params,String key);
	
	/**
	 * 根据refundId拿到退款记录信息
	 * @param refundId
	 * @return
	 */
	PayRefund getPayRefundByRefundId(String refundId);
	
	
	/**
     * 盛付通支付提现接口(盛付通代发接口)   
     * @param map
     * @return
     */
	Map<String, String> shengPay(Map<String,Object> map);
	
	/**
	 * 处理返回盛付通返回数据
	 * @param params
	 * @return
	 */
	Map<String,String> dealRefundData(Map<String,String> params);
	
	boolean verifyRefundSign(Map<String,String> paramsMap);

	Map<String, String> shengPayQuery(String batchNo);
}
