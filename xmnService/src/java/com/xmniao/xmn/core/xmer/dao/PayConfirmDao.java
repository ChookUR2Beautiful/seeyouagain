package com.xmniao.xmn.core.xmer.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface PayConfirmDao {
	
	/**
	 * 
	* @Title: queryOrderAmount
	* @Description:查询订单金额
	* @return Double    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public Map<Object,Object> queryOrderAmount(String orderSn);
	
	/**
	 * 
	* @Title: queryOrderSellerInfo
	* @Description:查询订单商铺信息
	* @return Map<Object,Object>    返回类型
	* @author liuzhihao1
	* @throws
	 */
	@DataSource("joint")
	public Map<Object,Object> queryOrderSellerInfo(Integer sellerId);
	
	/**
	 * 
	* @Title: querySellerAreaName
	* @Description:查询商铺地址名称
	* @return String    返回类型
	* @author liuzhihao
	* @throws
	 */
	@DataSource("joint")
	public String querySellerAreaName(Map<Object,Object> map);

}
