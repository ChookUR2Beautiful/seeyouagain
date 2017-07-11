package com.xmn.saas.dao.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.activity.Freetry;

public interface FreetryDao {
	int deleteByPrimaryKey(Integer id);

	int insert(Freetry record);

	int insertSelective(Freetry record);

	Freetry selectByPrimaryKey(@Param("id") Integer id, @Param("sellerId") Integer sellerId);
	
	Freetry findByPrimaryKey(@Param("id") Integer id);

	int updateByPrimaryKeySelective(Freetry record);

	int updateByPrimaryKey(Freetry record);

	List<Freetry> list(Integer sellerId);

	/**
	 * 
	 * 方法描述：获取活动进行中列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 下午3:59:42   
	 * @param sellerId
	 * @return
	 */
	List<Freetry> listBeing(Integer sellerId);
	
	/**
	 * 
	 * 方法描述:获取活动已结束列表
	 * 创建人：jianming  
	 * 创建时间：2016年10月8日 下午4:00:16   
	 * @param sellerId
	 * @param pageIndex 
	 * @param pageSize 
	 * @return
	 */
	List<Freetry> listEnd(@Param("sellerId")Integer sellerId, @Param("pageSize")Integer pageSize, @Param("pageIndex")Integer pageIndex);
	
	
	/**
	 * 
	 * 方法描述：统计正在进行的活动
	 * 创建人：jianming  
	 * 创建时间：2016年10月21日 下午4:51:19   
	 * @param activityType
	 * @param sellerId
	 * @return
	 */
	Integer CountBeingActivity( @Param("sellerId")Integer sellerId);
	
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
	 * 创建时间：2016年10月28日 上午10:18:50   
	 * @return
	 */
	Freetry selectByPrimaryKeyAndGiveAwardCount(@Param("id") Integer id, @Param("sellerId") Integer sellerId);
}