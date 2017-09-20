package com.xmniao.xmn.core.billmanagerment.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.billmanagerment.entity.BillBargain;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 *@ClassName:AllBillBargainDao
 *@Description:爆品订单接口
 *@author hls
 *@date:2016年5月5日下午2:38:47
 */
public interface AllBillBargainDao extends BaseDao<BillBargain>{
	
	/**
	 * 获取爆品订单信息列表（除待支付）
	 */
	@DataSource("slave")
	public List<BillBargain> getBillBargainList(BillBargain billBargain);
		
	/**
	 * 获取爆品订单信息列表条数（除待支付）
	 */
	@DataSource("slave")
	public Long billBargainCount(BillBargain billBargain);
	
	/**
	 * 根据爆品订单表id查询爆品订单详情
	 * @param bid
	 * @return
	 */
	@DataSource("slave")
	public BillBargain getBillBargain(String bid);
    
	/**
	 * 导出爆品订单信息列表（除待支付）
	 */
	@DataSource("slave")
	public List<BillBargain> exportBillBargainList(BillBargain billBargain);
}
