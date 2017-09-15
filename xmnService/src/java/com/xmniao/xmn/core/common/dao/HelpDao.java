package com.xmniao.xmn.core.common.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;


@Repository
public interface HelpDao {
	
	/**
	 * 查询帮助分类
	* @Title: queryItemList
	* @Description: 
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryItemList();
	
	/**
	 * 查询帮助条目列表
	* @Title: queryInfoList
	* @Description: 
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryInfoByItemid(Integer itemid);
	
	/**
	 * 根据Id查询具体帮助信息
	* @Title: queryInfoByid
	* @Description: 
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryInfoByid(Integer id);

}
