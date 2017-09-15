/**
 * 2016年8月15日 下午4:47:53
 */
package com.xmniao.xmn.core.live.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：EggToBirdMoney
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月15日 下午4:47:53
 * @version
 */
@Repository
public interface EggToBirdMoneyDao {
	/**
	 * 
	* @Title: updateEggToBirdMoney
	* @Description: 鸟蛋转出鸟币余额
	* @return Integer
	 */
	@DataSource("xmnpay")
	public Integer updateEggToBirdMoney(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: queryBirdEggByUid
	* @Description: 查询当前鸟蛋数和钱包状态
	* @return Map<Object,Object>
	 */
	@DataSource("xmnpay")
	public Map<Object,Object> queryBirdEggByUid(int uid);
}
