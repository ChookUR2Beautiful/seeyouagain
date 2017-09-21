package com.xmniao.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.order.BillFreshSub;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：BillFreshSubDao
 * 
 * 类描述： 线上积分商品(子)订单DAO
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月25日 上午10:03:24 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BillFreshSubDao {

	/*
	 * 获取已收货订单列表
	 */
	List<BillFreshSub> getLedgerBillList();
	
	/*
	 * 修改订单分账状态
	 */
	int modifyLedgerBill(BillFreshSub billFreshSub);
	
	/*
	 * 获取订单总采购价
	 */
	List<Map<String,Object>> getBillPurchasePrice(Map<String,Object> parMap);
	
	/*
	 * 更新订单的分账状态
	 */
	int modifySubBillInfo(BillFreshSub billFreshSub);
	
	/*
	 * 获取子订单的信息
	 */
	BillFreshSub getSubBillInfo(String subOrdersn);
	
	
	/**
	 * 更新超时未确认收货的订单，为已收货
	 */
	int updateOutTimeOrder(Map<String,Object> map);
	
	Map<String,Object> getCashCoupon(String ordersn);
}