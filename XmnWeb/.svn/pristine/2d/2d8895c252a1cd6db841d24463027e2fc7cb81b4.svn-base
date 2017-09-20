package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.fresh.entity.ActivityGroup;

public interface ActivityGroup1Dao {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityGroup1Dao record);

    int insertSelective(ActivityGroup1Dao record);

    ActivityGroup1Dao selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityGroup1Dao record);

    int updateByPrimaryKey(ActivityGroup1Dao record);

	/**
	 * 方法描述：查询已结束活动但库存不为0的商品规格
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月7日下午2:37:22 <br/>
	 * @return
	 */
	List<ActivityGroup> selectEndActvityGroup();

	/**
	 * 方法描述：还原规格库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月7日下午2:48:22 <br/>
	 * @param stock
	 * @param codeId
	 * @param pvIds
	 * @return 
	 */
	Integer updateActivityGroup(@Param("stock")Integer stock, @Param("codeId")Long codeId, @Param("pvIds")String pvIds);

	/**
	 * 方法描述：情况活动规格库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月7日下午2:51:24 <br/>
	 * @param id
	 */
	void clearStock(Integer id);

	/**
	 * 方法描述：还原商品库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月19日下午3:45:39 <br/>
	 * @param stock
	 * @param codeId
	 */
	void updateActivityProduct(@Param("store")Integer stock,@Param("codeId") Long codeId);

	/**
	 * 方法描述：清空活动商品库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月19日下午3:49:57 <br/>
	 * @param codeId
	 * @param activityId 
	 */
	void clearActivityProductStore(@Param("codeId")Long codeId, @Param("activityId")Integer activityId);
	
}