package com.xmniao.xmn.core.service_management.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TAgioRecord;
import com.xmniao.xmn.core.service_management.entity.Member;
import com.xmniao.xmn.core.service_management.entity.Order;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface PhoneRecordDao extends BaseDao<TAgioRecord>{
	
	/**
	 * 获取订单信息列表
	 */
	@DataSource("slave")
	public List<Order> getOrderList(Order order);
	/**
	 * 获取订单信息列表条数（除待支付）
	 */
	@DataSource("slave")
	public Long orderCount(Order order);
	
	/**
	 * 获取会员信息
	 */
	@DataSource("slave")
	public List<Member> getMemberList(Member member);
	/**
	 * 获取会员条数
	 */
	@DataSource("slave")
	public Long memberCount(Member member);


}
