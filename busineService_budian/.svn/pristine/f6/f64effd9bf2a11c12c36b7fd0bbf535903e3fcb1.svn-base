package com.xmniao.dao.sellerOrder;

import java.util.Map;

/**
 * 商户订单
 * @author chenJie
 *
 */
public interface SellerOrderDao {
	
	/**
	 * 根据订单id获取订单信息
	 * @param orderNo
	 * @return
	 */
	Map<String,Object> getRedPacket(String orderNo);
	
	/**
	 * 更新订单状态
	 * @param paraMap
	 * @return
	 */
	Integer updateRedPacketState(Map<String,String> paraMap);
	
	/**
	 * 更新领取红包记录状态
	 * @param paraMap
	 * @return
	 */
	Integer updateRedPacketRecord(Map<String,String> paraMap);

	
	/**
	 * 更新面对面付款订单
	 * @Title: updateMicroBill 
	 * @Description:
	 */
	int updateMicroBill(Map<String,Object> paraMap);
	
	/**
	 * 更新面对面付款订单
	 * @Title: updateMicroBill 
	 * @Description:
	 */
	Map<String,Object> getMicroBill(String orderNumber);
	
	
	/**
	 * 获取网红订单
	 * @param param
	 * @return
	 */
	Map<String,Object> getCelebrityOrder(Map<String,String> param);
	/**
	 * 更新网红订单
	 * @param param
	 * @return
	 */
	Integer updateCelebrityOrder(Map<String,String> param);
}
