/**
 * 2016年9月6日 下午1:56:52
 */
package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：LiveRobotDao
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年9月6日 下午1:56:52
 * @version
 */
@Repository
public interface LiveRobotDao {
	/**
	 * 
	* @Title: queryRebotListByids
	* @Description: 查看机器人观众列表
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryRebotListByids(List<String> list);
	
	/**
	 * 
	* @Title: queryRebotListByObj
	* @Description: 随机取出5条机器人
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryRebotListByObj(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryRebotListByObj
	* @Description: 随机取出5条机器人 观众 用于发礼物 和 弹幕
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryLiveRebotViewerList(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: addRebotRecord
	* @Description: 机器人观众进入直播间
	* @return Integer
	 */
	@DataSource("joint")
	public Integer addRebotRecord(List<Map<Object,Object>> list);
	/**
	 * 
	* @Title: queryRobetSet
	* @Description: 获取机器人配置信息
	* @return Map<Object,Object>
	 */
	@DataSource("joint")
	public Map<Object,Object> queryRobetSet();

	/**
	 * 
	* @Title: queryRebotById
	* @Description: 获取机器人信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryRebotById(Integer id);

	/**
	 * 
	* @Title: queryRobotAvatar
	* @Description: 批量查询机器人的头像信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryRobotAvatar(@Param("robotList")List<Map<Object, Object>> robotList);
	
	/**
	 * 描述：获取语言库记录
	 * @return map
	 * @param Map
	 * */
	@DataSource("joint")
	public Map<Object, Object> queryRobotInfomation();

	@DataSource("joint")
	public Map<Object, Object> queryLiveRecordRobetSet(Integer liveRecordId);

}
