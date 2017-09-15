package com.xmniao.xmn.core.xmer.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.common.request.AddabnormalRequest;
import com.xmniao.xmn.core.util.dataSource.DataSource;


@Repository
public interface AbnormalDao {
	
	/**
	 * 
	* @Title: queryAbnormalByUidAndSellerid
	* @Description: 查询改用户报错中的数量
	* @return int    返回类型
	* @throws
	 */
	@DataSource("joint")
	int queryAbnormalByUidAndSellerid(AddabnormalRequest request);

	
		/**
		 * 
		* @Title: addabnormal
		* @Description: 添加报错或投诉信息
		* @return void    返回类型
		* @throws
		 */
	@DataSource("joint")
	void addabnormal(Map<String, Object> paramMap);

}
