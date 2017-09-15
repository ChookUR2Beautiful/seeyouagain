package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.LiveManagerInfo;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 描述 主播管理员操作Dao
 * @author yhl
 * 2016年8月13日11:58:50
 * */
@Repository
public interface AnchorManagerDao {
	
	/**
	  * 描述 主播管理员  查询管理员列表
	 * @author yhl
	 * @param map
	 * @return List
	 * 2016年8月13日11:58:50
	 * */
	@DataSource("burs")
	public List<LiveManagerInfo> queryAnchorManagerList(Map<Object, Object> map);
	
	/**
	  * 描述 主播管理员操作  删除管理员
	 * @author yhl
	 * @param map
	 * @return List
	 * 2016年8月13日11:58:50
	 * */
	@DataSource("burs")
	public int deleteAnchorManager(Map<Object, Object> map);
	
	
	/**
	  * 描述 主播管理员操作 新增管理员
	 * @author yhl
	 * @param map
	 * @return List
	 * 2016年8月13日11:58:50
	 * */
	@DataSource("burs")
	public int addAnchorManagerInfo(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryAnchorManager
	* @Description: 查询主播是否已经存在指定的管理员
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("burs")
	public Map<Object, Object> queryAnchorManager(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryAnchorManagerSum
	* @Description: 查询主播的管理员的数量
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("burs")
	public Integer queryAnchorManagerSum(Integer anchorId);
	

}
