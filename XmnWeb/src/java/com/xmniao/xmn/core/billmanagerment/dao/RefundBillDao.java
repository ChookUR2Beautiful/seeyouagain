package com.xmniao.xmn.core.billmanagerment.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.billmanagerment.entity.Refund;
import com.xmniao.xmn.core.businessman.entity.TComment;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：refundBillDao
 * 
 * @类描述： 申诉订单
 * 
 * @创建人：huangpingqiang
 * 
 * @创建时间：2014年11月26日16时00分41秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface RefundBillDao extends BaseDao<TComment>{
	
	/**
	 * 获取订单信息列表
	 */
	@DataSource("slave")
	public List<Refund> getRefundBillList(Refund refund);
		
	/**
	 * 获取客户信息列表billCount
	 */
	@DataSource("slave")
	public Long refundBillCount(Refund refund);
	
	/**
	 * 获取退款订单信息列表
	 */
	@DataSource("slave")
	public List<Refund> getRefundBillPendingList(Refund refund);
		
	/**
	 * 获取退款客户信息列表billCount
	 */
	@DataSource("slave")
	public Long refundBillPendingCount(Refund refund);
	
	/**
	 * 退款记录查询
	 * @param refund
	 * @return
	 */
	@DataSource("slave")
	public List<Refund> getRefundBillPendingHistoryList(Refund refund);
	
	/**
	 * 退款记录查询统计
	 * @param refund
	 * @return
	 */
	@DataSource("slave")
	public Long refundBillPendingHistoryCount(Refund refund);

	/**
	 * 总裁办订单查询
	 * @param refund
	 * @return
	 */
	@DataSource("slave")
	public List<Refund> getRefundBillPresidentList(Refund refund);
	
	/**
	 * 总裁办订单数量
	 * @param refund
	 * @return
	 */
	@DataSource("slave")
	public Long refundBillPresidentCount(Refund refund);

	

}
