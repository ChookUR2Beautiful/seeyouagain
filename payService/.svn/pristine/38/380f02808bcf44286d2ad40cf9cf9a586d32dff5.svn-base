package com.xmniao.service;

import java.util.Map;

/**
 * 快钱支付退款服务接口
 * @author ChenBo
 *
 */
public interface KuaiqianPayService {

	/**
	 * 快钱退款
	 * @param payId
	 * @param refundId
	 * @param orderAmount
	 * @param payDate
	 * @return
	 */
	Map<String,String> kuaiqianPayRefund(String refundId,String payId,double orderAmount);

	/**
	 * 快钱代付
	 * @param paramMap
	 * @return
	 * @throws Exception 
	 */
	public Map<String, String>  kuaiqianPay(Map<String, Object> paramMap) throws Exception;
	
	/**
	 * 快钱批次号查询
	 * @param batchId
	 * @return
	 */
	public  Map<String,Object> queryByBatch(String batchId);
	
	/**
	 * 快钱订单号查询
	 * @param batchId
	 * @return
	 */
	public  Map<String, String> queryByOrder(String batchId);

	
	/**
	 * 快钱退款查询
	 * @param payId
	 * @param refundId
	 * @return
	 */
	Map<String,String> KuaiqianQueryRefund(String payId,String refundId);

}
