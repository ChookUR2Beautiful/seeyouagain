/**
 * 2016年8月19日 上午10:48:26
 */
package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：SendBarrageDao
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月19日 上午10:48:26
 * @version
 */
@Repository
public interface SendBarrageDao {

	/**
	 * 
	* @Title: addSendBarrage
	* @Description: 发送弹幕
	* @return Integer
	 */
	@DataSource("joint")
	public Integer addSendBarrage(Map<Object,Object> map);
	
	/**
	 * 
	* @方法名称: queryBarrageByTime
	* @描述: 获取弹幕消息列表
	* @返回类型 List<Map<Object,Object>>
	* @创建时间 2016年10月11日
	* @param live_record_id
	* @return
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryCommonMsgByRecordId(Map<Object,Object> map);
}
