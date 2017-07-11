package com.xmn.saas.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.activity.FreetryRecord;

public interface FreetryRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FreetryRecord record);

    int insertSelective(FreetryRecord record);

    FreetryRecord selectByPrimaryKey(@Param("id")Integer id, @Param("activityId")Integer activityId);

    int updateByPrimaryKeySelective(FreetryRecord record);

    int updateByPrimaryKey(FreetryRecord record);

	List<FreetryRecord> list(@Param("sellerId")Integer sellerId, @Param("activityId")Integer activityId,@Param("pageSize") Integer pageSize, @Param("pageIndex")Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：统计获奖人数
	 * 创建人：jianming  
	 * 创建时间：2016年10月9日 上午10:34:15   
	 * @param activityId
	 * @return
	 */
	Long countFreetryRecord(Integer activityId);
	
	/**
	 * 
	 * 方法描述：查询明细
	 * 创建人：jianming  
	 * 创建时间：2016年10月12日 下午5:46:07   
	 * @param id
	 * @param activityId
	 * @return
	 */
	FreetryRecord detail(@Param("id")Integer id, @Param("activityId")Integer activityId);
	
	/**
	 * 
	 * 方法描述：统计用户抽取次数
	 * 创建人：jianming  
	 * 创建时间：2016年10月15日 上午10:40:41   
	 * @param activityId
	 * @param userId
	 * @return
	 */
	Long lotteryCount(Integer activityId, Integer userId);
	
	/**
	 * 
	 * 方法描述：统计用户领取次数
	 * 创建人：jianming  
	 * 创建时间：2016年10月15日 上午10:48:51   
	 * @param activityId
	 * @param userId
	 * @return
	 */
	Long countWinningNumber(Integer activityId, Integer userId);
	
	/**
	 * 
	 * 方法描述：查询用户最后一条领取记录
	 * 创建人：jianming  
	 * 创建时间：2016年10月15日 上午11:06:00   
	 * @param activityId
	 * @param userId
	 * @return
	 */
	FreetryRecord selectLastIndexByGetTime(Integer activityId, Integer userId);
	
	/**
	 * 
	 * 方法描述：领取总数
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 上午11:56:44   
	 * @param id
	 * @return
	 */
	Integer countRecord(Integer activityId);
}