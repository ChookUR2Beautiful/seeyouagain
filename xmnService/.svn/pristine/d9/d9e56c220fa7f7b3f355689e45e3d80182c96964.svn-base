/**
 * 2016年8月23日 下午2:18:21
 */
package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：SelfGiftDao
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月23日 下午2:18:21
 * @version
 */
@Repository
public interface SelfGiftDao {

	/**
	 * 
	* @Title: querySelfGiftList
	* @Description: 获取个人礼物列表
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> querySelfGiftList(Integer uid);
	
	/**
	 * 
	* @Title: querySelfGiftToObj
	* @Description: 获取某个礼物信息
	* @return Map<Object,Object>
	 */
	@DataSource("joint")
	public Map<Object,Object> querySelfGiftToObj(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: insertSelfGift
	* @Description: 新增礼物信息
	* @return Integer
	 */
	@DataSource("joint")
	public Integer insertSelfGift(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: modifySelfGift
	* @Description: 修改礼物信息
	* @return Integer
	 */
	@DataSource("joint")
	public Integer updateSelfGift(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryDrawList
	* @Description: 获取抽奖大礼包礼物
	* @return Map<Object,Object>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryDrawList(Integer gift_bag_id);
}
