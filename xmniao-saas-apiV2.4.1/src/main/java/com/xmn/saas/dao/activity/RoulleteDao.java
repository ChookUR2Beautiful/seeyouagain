package com.xmn.saas.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.activity.Roullete;


public interface RoulleteDao {
    int deleteByPrimaryKey(Long id);

    int insert(Roullete record);

    int insertSelective(Roullete record);

    Roullete selectByPrimaryKey(@Param("id")Integer activityId,@Param("sellerid")Integer sellerId);
    
    Roullete findByPrimaryKey(@Param("id")Integer activityId);

    int updateByPrimaryKeySelective(Roullete record);

    int updateByPrimaryKey(Roullete record);

	List<Roullete> list(Integer sellerId);
	/**
	 * 
	 * 方法描述：获取活动进行中列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 下午4:07:42   
	 * @param sellerId
	 * @return
	 */
	List<Roullete> listBeing(Integer sellerId);
	
	/**
	 * 
	 * 方法描述：获取活动已结束列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 下午4:12:41   
	 * @param sellerId
	 * @param pageIndex 
	 * @param pageSize 
	 * @return
	 */
	List<Roullete> listEnd(@Param("sellerId")Integer sellerId, @Param("pageSize")Integer pageSize, @Param("pageIndex")Integer pageIndex);
	
	/**
	 * 
	 * 方法描述：统计正在进行中的活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月21日 下午5:04:59   
	 * @param activityType
	 * @param sellerId
	 * @return
	 */
	Integer CountBeingActivity(@Param("sellerId") Integer sellerId);

	/**
	 * 
	 * 方法描述：终止活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月24日 下午2:44:18   
	 * @param id
	 * @param sellerId 
	 */
	void endActivity(@Param("id")Integer id,@Param("sellerId") Integer sellerId);
	
	/**
	 * 
	 * 方法描述：查询并统计获奖人数
	 * 创建人：jianming  
	 * 创建时间：2016年10月28日 上午10:21:25   
	 * @param activityId
	 * @param sellerId
	 * @return
	 */
	Roullete selectByPrimaryKeyAndGiveAwardCount(@Param("id")Integer activityId,@Param("sellerid")Integer sellerId);
}