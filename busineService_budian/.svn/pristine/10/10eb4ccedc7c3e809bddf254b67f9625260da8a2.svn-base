package com.xmniao.dao.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.order.BillBargain;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：BillBargainDao
 * 
 * 类描述： 线下积分商品订单DAO
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年6月25日 上午10:04:30 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface BillBargainDao {

	/**
	 * 获取指定线下积分商品订单的信息
	 */
	BillBargain getBillBargain(BillBargain billBargain);
	
	/**
	 * 获取线下积分商品订单的信息列表
	 * @Title: getBillBargainList 
	 * @Description:
	 */
	List<BillBargain> getBillBargainList(BillBargain billBargain);
	
	/**
	 * 修改线下积分商品订单的信息信息
	 * @Title: modifyBillBargainInfo 
	 * @Description:
	 */
	int modifyBillBargainInfo(BillBargain billBargain);
	
	/**
	 * 批量修改线下积分商品订单的信息信息
	 * @Title: modifyBillBargainInfo 
	 * @Description:
	 */
	int modifyBillBargainInfoByBatch(BillBargain billBargain);
	
	/**
	 * 获取线下积分商品的采购价
	 * @Title: getBargainProduct 
	 * @Description:
	 */
	Map<String,Object> getBargainProduct(String bpId);
	
	/**
	 * 获取线下积分订单信息
	 * @Title: getByBid 
	 * @Description:
	 */
	BillBargain getByBid(@Param("bid")Long bid);
	
	/**
	 * 
	 * @Title: updateForPayComplete 
	 * @Description:
	 */
	int updateForPayComplete(Map<String,Object> map);
	
	/**
	 * 添加线下积分订单退款记录
	 * @Title: insertOrderRefundRecord 
	 * @Description:
	 */
	int insertOrderRefundRecord(Map<String,Object> map);
	
	/**
	 * 修改线下积分商品订单的信息信息
	 * @Title: modifyBillBargainInfo 
	 * @Description:
	 */
	int modifyBillBargainStatus(BillBargain billBargain);
}