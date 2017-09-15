/**
 * 2016年8月18日 下午1:48:20
 */
package com.xmniao.xmn.core.live.dao;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PrivateMessage
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月18日 下午1:48:20
 * @version
 */
@Repository
public interface PrivateMessageDao {

	/**
	 * 
	* @Title: queryPrivateMessageBysendId
	* @Description: 检验用户是否发过私信
	* @return Map<Object,Object>
	 */
	@DataSource("joint")
	public Map<Object,Object> queryPrivateMessageBysendId(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: addPrivateMessage
	* @Description: 新增私信信息
	* @return Object
	 */
	@DataSource("joint")
	public Integer addPrivateMessage(Map<Object,Object> map);

	/**
	 * 
	* @Title: queryPrivateMessage
	* @Description: 查询私信信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryPrivateMessage(Map<Object, Object> paramMap);
}
