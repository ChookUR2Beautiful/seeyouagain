package com.xmniao.dao;

import java.util.Map;

public interface FreshOrderMapper {

	//根据生鲜订单号，查询相关支付订单表数据
    Map<String,Object> getPayOrderByorderNumber(String order_number);
    
    //根据支付号,查询订单号
    Map<String,Object> getPayOrderBypayId(String payId);
    
    /**
     * 根据支付号，查询相关支付金额
     * @param payId
     * @return
     */
    Map<String,Object> getOrderAmount(String order_number);
}