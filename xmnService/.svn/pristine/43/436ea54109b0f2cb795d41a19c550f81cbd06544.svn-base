package com.xmniao.xmn.core.wealth.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface RefundDao {

	
	//添加退款记录
	@DataSource("joint")	
	void addRefund(Map<Object,Object> map);

	//查询是否有某个订单的退款记录
	@DataSource("joint")
	int queryRefundBybid(Long bid);


}
