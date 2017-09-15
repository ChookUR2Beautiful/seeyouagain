package com.xmniao.xmn.core.integral.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;


@Repository
public interface PropertyDao {

	/**
	 * 
	* @Title: queryPropertyList
	* @Description: 查询
	* @return List<Map<String,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<String, Object>> queryPropertyList(BigInteger codeId);

	/**
	 * 查询属性值
	* @Title: queryPropertyValueList
	* @Description: 
	* @return List<Map<String,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<String, Object>> queryPropertyValueList(String id);

}
