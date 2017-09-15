package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;


/**
 * 项目描述：XmnService
 * API描述：用户系统消息dao
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */
@Repository
public interface MessageManageDao {
	
	/**
	 *	描述：获取系统消息列表
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@DataSource("joint")
	public List<Object> getMessageSystemList(Map<Object, Object> map);
	
	/**
	 *	描述：忽略所有消息
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@DataSource("joint")
	public int editBatchMessageStatus(Map<Object, Object> map);
	
	
	/**
	 *	描述：已读的消息修改为已读
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@DataSource("joint")
	public int editMessageStatus(Map<Object, Object> map);

	/**插入系统消息表
	 * @param paraMap
	 */
	@DataSource("joint")
	public void insertAppPush(Map<Object, Object> paraMap);

	/**
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	public Map<Object, Object> findMessagebyActivityId(Map<Object, Object> map);

	/**
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> finMessageByUid(Map<Object,Object> param);

	@DataSource("joint")
	public void updateISshow(Integer tid);
	
	
	
	
	
}
