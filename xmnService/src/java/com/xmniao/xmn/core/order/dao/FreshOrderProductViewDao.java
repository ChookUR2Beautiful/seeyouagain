package com.xmniao.xmn.core.order.dao;


import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.order.entity.FreshOrderProduct;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 项目描述：XmnService
 * API描述：订单操作
 * @author yhl
 * 创建时间：2016年6月17日10:13:55
 * @version  
 * */
public interface FreshOrderProductViewDao {
	
	/**
	 * 订单操作VIEW 主订单商品
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public List<FreshOrderProduct> queryOrderProductList(Map<Object, Object> map);
	
	/**
	 * 订单操作VIEW
	 * @author yhl
	 * @param Object Object
	 * @return Object
	 * */
	@DataSource("joint")
	public List<FreshOrderProduct> queryOrderSubProductList(Map<Object, Object> map);
	
}
