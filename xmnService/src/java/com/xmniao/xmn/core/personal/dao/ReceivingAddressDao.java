package com.xmniao.xmn.core.personal.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：ReceivingAddressDao   
* 类描述：   收货地址管理Dao
* 创建人：yezhiyong   
* 创建时间：2016年11月11日 下午5:31:43   
* @version    
*
 */
@Repository
public interface ReceivingAddressDao {

	/**
	 * 
	* @Title: queryReceivingAddressListByUid
	* @Description: 根据uid获取收货地址列表
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<ReceivingAddress> queryReceivingAddressListByUid(Map<Object, Object> map);

	/**
	 * 
	* @Title: updateReceivingAddressByUid
	* @Description: 取消用户的默认收货地址
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	void updateReceivingAddressByUid(int uid);

	/**
	 * 
	* @Title: insertReceivingAddress
	* @Description: 新添收货地址
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	void insertReceivingAddress(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryReceivingAddressById
	* @Description: 查询id,收货地址信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryReceivingAddressById(Integer receivingAddressId);

	/**
	 * 
	* @Title: updateReceivingAddressById
	* @Description: 根据id，更新收货地址信息
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	void updateReceivingAddressById(Map<Object, Object> receivingAddressParamMap);

	@DataSource("joint")
    ReceivingAddress selectUserDefaultAddress(@Param("uid") String uid);
}
