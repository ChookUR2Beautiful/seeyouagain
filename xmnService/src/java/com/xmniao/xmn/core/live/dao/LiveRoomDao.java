package com.xmniao.xmn.core.live.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;

/**
 * 
*    
* 项目名称：xmnService   
* 类名称：LiveRoomDao   
* 类描述：   直播间处理dao
* 创建人：yezhiyong   
* 创建时间：2016年12月15日 下午2:09:33   
* @version    
*
 */
@Repository
public interface LiveRoomDao {

	/**
	 * 
	* @Title: queryLiveRecordById
	* @Description: 通过记录id查询直播记录信息
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryLiveRecordById(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: insertLiveRedpacket
	* @Description: 插入红包记录
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	void insertLiveRedpacket(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: queryLiveRedpacketById
	* @Description: 查询红包信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryLiveRedpacketById(Integer liveRedpacketId);

	/**
	 * 
	* @Title: queryLiveRedpacketRecord
	* @Description: 查询领取红包记录信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryLiveRedpacketRecord(Map<Object, Object> paramMap);

	/**
	 * 
	* @Title: updateLiveRedpacketById
	* @Description: 更新红包信息
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer updateLiveRedpacketById(Map<Object, Object> map);

	/**
	 * 
	* @Title: insertLiveRedpacketRecord
	* @Description: 插入抢到红包记录信息
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	void insertLiveRedpacketRecord(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryLiveRedpacketByUid
	* @Description: 查询是否存在未被领取完的红包
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryLiveRedpacketByUid(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryLiveRedpacketByLiveRecordId
	* @Description: 查询该场直播,发送的所有红包总鸟蛋数
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer queryLiveRedpacketByLiveRecordId(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryRefundLiveRedpacketAmount
	* @Description: 查询历史红包退还金额
	* @return Integer    返回类型
	* @throws
	 */
	@DataSource("joint")
	Integer queryRefundLiveRedpacketAmount(Map<Object, Object> map);

	/**
	 * 
	* @Title: queryLiveClassify
	* @Description: 查询直播标签的分类标签
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryLiveClassify(int classifyType);

	/**
	 * 
	* @Title: queryLiveClassifyTagByClassifyId
	* @Description: 查询直播标签分类下的所有标签
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryLiveClassifyTagByClassifyId(int classifyId);

	/**
	 * 
	* @Title: queryLiveClassifyTagById
	* @Description: 查询标签信息
	* @return Map<Object,Object>    返回类型
	* @throws
	 */
	@DataSource("joint")
	Map<Object, Object> queryLiveClassifyTagById(int tagId);

	/**
	 * 
	* @Title: insertLiveRecordTagConf
	* @Description: 批量插入通告标签
	* @return void    返回类型
	* @throws
	 */
	@DataSource("joint")
	void insertLiveRecordTagConf(@Param("tagList")List<Map<Object, Object>> tagList);

	/**
	 * 
	* @Title: queryLiveRecordTagConf
	* @Description: 批量查询直播内容标签信息
	* @return List<Map<Object,Object>>    返回类型
	* @throws
	 */
	@DataSource("joint")
	List<Map<Object, Object>> queryLiveRecordTagConf(@Param("liveRecordIds")List<Integer> liveRecordIds);

}
