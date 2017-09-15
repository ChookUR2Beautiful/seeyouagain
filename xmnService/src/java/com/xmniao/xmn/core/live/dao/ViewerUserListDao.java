/**
 * 2016年8月15日 上午11:37:42
 */
package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.LiveViewRecordInfo;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：ViewerUserDao
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月15日 上午11:37:42
 * @version
 */
@Repository
public interface ViewerUserListDao {

	/**
	 * 
	* @Title: queryViewerUserListByAhId
	* @Description: 查看主播房间观众列表
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryViewerUserListByAhId(Map<Object,Object> map);
	
	/**
	* queryViewerUserListByAhId
	* 查询直播间内所有正在观看直播的用户
	* @return List<Map<Object,Object>>
	* @param map
	 */
	@DataSource("joint")
	public List<LiveViewRecordInfo> queryLiveRoomViewerAll(Map<Object,Object> map);
	
}
