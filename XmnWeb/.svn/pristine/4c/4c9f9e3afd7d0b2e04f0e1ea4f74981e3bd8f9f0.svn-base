package com.xmniao.xmn.core.billmanagerment.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.billmanagerment.entity.BillRecord;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：BillRecordDao
 * 
 * @类描述： 申诉订单
 * 
 * @创建人：huangpingqiang
 * 
 * @创建时间：2014年11月26日16时00分41秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface BillRecordDao extends BaseDao<BillRecord>{
	
	/**
	 * 获取订单信息列表
	 */
	@DataSource("slave")
	public List<BillRecord> getRefundBillHistoryList(BillRecord billRecord);
		
	/**
	 * 获取客户信息列表billCount
	 */
	@DataSource("slave")
	public Long refundBillHistoryCount(BillRecord refund);

}
