/**
 * 2016年8月16日 上午10:48:18
 */
package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * @项目名称：xmnService_3.1.2_zb
 * @类名称：PersonalListDao
 * @类描述：
 * @创建人： yeyu
 * @创建时间 2016年8月16日 上午10:48:18
 * @version
 */
@Repository
public interface PersonalDetailDao {

	/**
	 * 
	* @Title: queryRechargeList
	* @Description: 查询充值套餐
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryRechargeList(Map<Object,Object> map);
	
	/**
	 * 
	* 描述: 查看送出鸟蛋数
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryGiveEggList(Map<Object,Object> map);
	
	/**
	 * 
	* 描述: 查询关注列表 
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryAttentionFocusList(Map<Object,Object> map);

	/**
	 * 
	* 描述: 查看fans列表
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryAttentionFansList(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryAttentionFansListAll
	* @Description: 查看所有fans列表
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryAttentionFansListAll(Map<Object,Object> map);
	
	
	/**
	 * 
	* @Title: queryFansShowListAll
	* @Description: 查看所有想看列表
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryFansShowListAll(int liveRecordId);
	
	/**
	 * 
	* @Title: queryLiveRecordList
	* @Description: 查看直播记录
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryLiveRecordList(Map<Object,Object> map);
	/**
	 * 
	* @Title: queryLiveRecordDetailList
	* @Description: 直播时长详情
	* @return List<Map<Object,Object>>
	 */
	@DataSource("joint")
	public List<Map<Object,Object>> queryLiveRecordDetailList(Map<Object,Object> map);
	
	/**
	 * 
	* @Title: queryLiveRecordNum
	* @Description: 查询直播总时长
	* @return Map<Object,Object>
	 */
	@DataSource("joint")
	public Map<Object,Object> queryLiveRecordNum(Map<Object,Object> map);
	
	
	/**
	 * 
	* @Title: queryBarrageMessageNum
	* @Description: 查询弹幕数及私信数
	* @return Map<Object,Object>
	 */
	@DataSource("joint")
	public Map<Object,Object> queryBarrageMessageNum(int liver_id);

	/**
	 * 
	* @Title: queryGiftDiffSum
	* @Description: 计算本周，本月，共送的鸟豆值
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryGiftSendDiffSum(Integer liver_id);

	/**
	 * 
	* @Title: queryEachFocusAndFansList
	* @Description: 查询主播主页的粉丝列表中的互相关注和粉丝列表
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryEachFocusAndFansList(Map<Object, Object> paramMap);
	
	/**
	 * 
	* @Title: queryEachFocusAndFansList
	* @Description: 查询主播主页的粉丝列表中的互相关注和粉丝列表
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public int queryEachFocusAndFansCount(Map<Object, Object> paramMap);
	
	/**
	 * 粉丝数
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	Integer selectFansCount(@Param("uid") Integer uid);

	/**
	 * 关注数
	 * @param uid
	 * @return
	 */
	@DataSource("joint")
	Integer selectFocusCount(@Param("uid") Integer uid);
	
}
