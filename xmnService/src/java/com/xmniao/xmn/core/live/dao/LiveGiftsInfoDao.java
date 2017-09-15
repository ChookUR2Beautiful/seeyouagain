package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.live.entity.LiveGiftsInfo;
import com.xmniao.xmn.core.live.entity.LiveGivedGiftInfo;
import com.xmniao.xmn.core.live.entity.LiveSelfGiftInfo;
import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 项目描述：XmnService
 * API描述：直播界面获取礼物列表
 * @author yhl
 * 创建时间：2016年8月10日16:53:55
 * @version 
 * */
@Repository
public interface LiveGiftsInfoDao {
	
	/**
	 *	描述：直播界面获取礼物列表
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@DataSource("joint")
	public List<LiveGiftsInfo> getGiftsInfoList(Map<Object, Object> map);
	
	/**
	 *	描述：根据礼物Id获取礼物基本信息
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@DataSource("joint")
	public LiveGiftsInfo getGiftsInfoByGiftId(Integer id);
	
	/**
	 *	描述：插入礼物赠送记录
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@DataSource("joint")
	public int addLiveGivedGiftRecord(Map<Object, Object> map);
	
	/**
	 *	描述：根据主播/直播 记录 查询本次获取的礼物
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@DataSource("joint")
	public List<Map<Object, Object>> queryAnchorGiftNums(Map<Object, Object> map);
	
	/**
	 *	描述：
	 * @author yhl
	 * 创建时间：2016年8月10日16:53:55
	 * */
	@DataSource("joint")
	public List<Map<Object, Object>> queryLiverGiftNums(Map<Object, Object> map);
	
	
	/**
	* 描述: 根据id修改发送礼物记录状态
	* @return int
	 */
	@DataSource("joint")
	public int modifyLiveGivedGiftRecord(Map<Object, Object> map);
	
	/**
	 * 
	* 描述: 根据id修改发送礼物记录状态
	* @return int
	 */
	@DataSource("joint")
	public List<LiveGivedGiftInfo> queryLiveGivedGiftByAdvancedStatus(Map<Object, Object> map);
	
	/**
	* 描述: 根据送礼物记录ID 修改发送礼物记录状态
	* @return int
	 */
	@DataSource("joint")
	public int modifyBatchLiveGivedGiftById(List<Map<Object, Object>> list);
	
	/**
	 * 
	* @方法名称: insertExpericeRecord
	* @描述: 增加经验记录
	* @返回类型 int
	* @创建时间 2016年10月13日
	* @param map
	* @return
	 */
	@DataSource("joint")
	public int insertExpericeRecord(Map<Object, Object> map);
	
	/**
	* 描述: 根据送礼物记录ID 修改发送礼物记录状态
	* @return int
	 */
	@DataSource("joint")
	public LiveSelfGiftInfo queryLiveSelfGifts(Map<Object, Object> map);
	
	/**
	* 描述: 根据送礼物记录ID，观众用户ID  修改用户自由礼物数量
	* @param Map<Object, Object>
	* @return int
	 */
	@DataSource("joint")
	public int modifyLiveSelfGiftNum(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryFavoriteAnchorByLiverId
	* @Description: 查询送给最多礼物的一个主播
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public Map<Object, Object> queryFavoriteAnchorByLiverId(int liverId);

	/**
	 * 
	* @Title: queryCheckGivedGifts
	* @Description: 批量查询这些用户是否送过礼物给主播
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryCheckGivedGifts(Map<Object, Object> map);
	
	/**
	 * 
	* @Title: queryLiveMarketProducts
	* @Description: 查询直播间有无设置商城实体礼物商品
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	public List<Map<Object, Object>> queryLiveMarketProducts(Map<Object, Object> map);

	/**
	 * 查询主播收到实体礼物列表
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryLiveAnchorGifts(Map<Object, Object> map);


	/**
	 * 获取主播收到实体礼物总数
	 * @param
	 * @return
	 */
	@DataSource("joint")
	int getLiveAnchorGiftCounts(@Param("anchorId") Integer anchorId);


	/**
	 * 查询单个主播接收实体礼物列表（按数量排序）
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryLiveAnchorsGiftListByAnchorId(Map<Object, Object> map);


	/**
	 * 查询所有主播接收实体礼物列表（按数量排序）
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryLiveAnchorsGiftList(Map<Object, Object> map);


	/**
	 * 查询每个用户赠送实体礼物列表
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryLiverGiveGiftList(Map<Object, Object> map);
	
	/**
	 * 获取商品实体礼物配置基础信息
	 * @param map
	 * @return
	 */
	@DataSource("joint")
	Map<Object, Object> queryLiveGiftDetailInfo(Map<Object, Object> map);
	
	/**
	 * 新增V客获得分成记录
	  *@param map
	  *@return
	 * */
	int insertVkeRatioRecord(Map<Object, Object> map);


}
