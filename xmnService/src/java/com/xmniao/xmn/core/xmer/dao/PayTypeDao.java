package com.xmniao.xmn.core.xmer.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：PayTypeDao   
* 类描述：   跳转支付页面Dao
* 创建人：yezhiyong   
* 创建时间：2016年6月7日 上午9:30:34   
* @version    
*
 */
@Repository
public interface PayTypeDao {

	/**
	 * 
	* @Title: querySoldOrederInfoByOrdersn
	* @Description: 查询订单表，是否有此订单号
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> querySoldOrederInfoByOrdersn(String orderid);
}
