package com.xmniao.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.entity.PayRefund;


/**
 * 退款Mapper
 * 
 * @author YangJing
 * 
 */
public interface FreshRefundMapper {

//	int insertPayRefund(Map<String, Object> paramMap);
	
	//根据退款流水号 查询该 退款记录
	PayRefund getPayRefundByRefundId(String refundid);
	
	//更新退款记录状态
	void updatePayOrderByRefundId(Map<String,Object> map);
	
	//根据支付号 查询该 退款记录
	PayRefund getPayRefundBypayId(long payid);

	Integer insertPayRefundByObject(PayRefund payRefund);
	
	//查询退款记录列表
	List<Map<String,String>> getPayRefundList(Map<String,Object> map);
	
	//根据支付类型查询退款记录单号列表
	List<Map<String,Object>> getPayRefundNoList(Map<String,Object> map);
	
	//更新退款记录的退款流水信息
	void updatePayOrderInfo(Map<String,Object> map);
}
