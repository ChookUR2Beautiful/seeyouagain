package com.xmniao.xmn.core.order.dao;

import java.util.List;

import com.xmniao.xmn.core.order.entity.OrderBackProduct;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 *  订单操作  取消订单回退商品库存
 *  @author yhl
 *  2016年6月20日19:06:45
 * */
public interface FreshOrderBackProdSumDao {

	
	/**
	 * 订单操作  取消订单回退商品库存
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * 2016年6月20日19:06:52
	 * */
	@DataSource("joint")
	public int orderbackProductSum(List<OrderBackProduct> prod_list);

	/**
	 * 
	* @Title: queryOrderByBid
	* @Description: 根据订单号去查询订单状态
	* @return Byte    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Byte queryOrderByBid(Long bid);

	/**
	 * 
	* @Title: mofifyOrderStatusByBid
	* @Description: 根据订单号修改订单号状态为确认收货
	* @return Integer    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	public Integer mofifyOrderStatusByBid(Long bid);
	
}
