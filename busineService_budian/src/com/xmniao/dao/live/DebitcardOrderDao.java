package com.xmniao.dao.live;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.xmniao.dao.base.BaseDao;
import com.xmniao.domain.live.DebitcardOrder;

public interface DebitcardOrderDao extends BaseDao<DebitcardOrder>{

	/**
	 * 
	 * 方法描述：初始化给商家预分账信息
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param order
	 * @return int
	 */
	int initPreLedgerInfo(DebitcardOrder order);

	/**
	 * 
	 * 方法描述：获取订单信息
	 * 创建人： ChenBo
	 * 创建时间：2017年3月29日
	 * @param orderNo
	 * @return DebitcardOrder
	 */
	DebitcardOrder getByOrderNo(String orderNo);
	
	/**
	 * 
	 * 方法描述：统计商家还需抵扣的分账总额
	 * 创建人： ChenBo
	 * 创建时间：2017年3月7日
	 * @param sellerid
	 * @return BigDecimal
	 */
	BigDecimal countSellerOrder(DebitcardOrder order);
	
	/**
	 * 
	 * 方法描述：统计商家还需抵扣的分账列表
	 * 创建人： ChenBo
	 * 创建时间：2017年3月7日
	 * @param sellerid
	 * @return List<DebitcardOrder>
	 */
	List<DebitcardOrder> getSellerRechargeList(DebitcardOrder order);
	
	/**
	 * 
	 * 方法描述：更新订单分账差额
	 * 创建人： ChenBo
	 * 创建时间：2017年3月7日
	 * @param paraMap
	 * @return int
	 */
	int updateSellerOrderOffset(Map<String,Object> paraMap);
}