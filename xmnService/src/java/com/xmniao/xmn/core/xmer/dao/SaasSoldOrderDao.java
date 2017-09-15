package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.xmer.entity.SaaSoldOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**   
 * 项目名称：xmnService   
 * 类名称：SaasSoldOrderDao   
 * 类描述：   
 * 创建人：zhengyaowen
 * 创建时间：2016年5月23日 下午6:20:42   
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 * @version     
 */
@Repository
public interface SaasSoldOrderDao {
	
	/**
	* @Title: querySelleridsByUid
	* @Description: 根据用户ID查询签约商铺ID
	* @param uid 用户ID
	* @return List<Integer>
	* @author zhengyaowen
	* @Description 修改描述
	* @update 修改人
	* @date 修改日期
	* @throws
	*/
	@DataSource("joint")
	List<Integer> querySelleridsByUid(Integer uid);

	/**
	 * 
	* @Title: queryIncomeList
	* @Description: 查询寻蜜客软件销售收入明细列表
	* @return List<Map<Object,Object>>    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryIncomeList(Map<Object, Object> map);
	
	
	/**
	 * 
	* @Title: getXmerSellerNums
	* @Description: 查询签约成功的店铺数量
	* @return int
	 */
	@DataSource("joint")
	public int getXmerSellerNums(@Param("uids")List<String> list);

	/**
	 * 
	* @Title: querySaasAmount
	* @Description: 查询寻蜜客软件销售总金额
	* @return Double    返回类型
	* @author
	* @throws
	 */
	@DataSource("joint")
	Double querySaasAmount(Map<Object, Object> paramMap);


	@DataSource("joint")
	SaaSoldOrder getSellerOrder(Map<Object, Object> paramMap);

	/**
	 * 查询寻蜜客软件销售订单列表
	 * @param paramMap
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> querySaasOrderIds(Map<Object, Object> paramMap);


	// 批量查询订单信息
	@DataSource("joint")
	List<Map<Object, Object>> querySaasOrderInfoByIds(Map<Object, Object> paramMap);

}
