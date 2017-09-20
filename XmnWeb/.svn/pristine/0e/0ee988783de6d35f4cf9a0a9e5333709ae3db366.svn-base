package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.ActivityGroup;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface ActivityGroupDao extends BaseDao<ActivityGroup>{
	@DataSource("master")
	int deleteByPrimaryKey(Integer id);
    @DataSource("master")
    int insert(ActivityGroup record);
    @DataSource("slave")
    ActivityGroup selectByPrimaryKey(Integer id);
    @DataSource("master")
    int updateByPrimaryKeySelective(ActivityGroup record);
    @DataSource("master")
    int updateByPrimaryKey(ActivityGroup record);
	/**
	 * 方法描述：根据编号和活动id查询
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午1:58:57 <br/>
	 * @param codeId
	 * @param id
	 * @return
	 */
    @DataSource("slave")
	List<ActivityGroup> getByCodeId(@Param("codeId")Long codeId, @Param("activityId")Integer id);
	/**
	 * 方法描述：根据商品编号和属性值查询
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午2:56:38 <br/>
	 * @param codeId
	 * @param pvIds
	 * @param activityId 
	 * @return
	 */
    @DataSource("slave")
	ActivityGroup getByCodeIdAndPvid(@Param("codeId")Long codeId, @Param("pvIds")String pvIds,@Param("activityId") Integer activityId);
	/**
	 * 方法描述：修改group
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午3:13:57 <br/>
	 * @param group
	 */
    @DataSource("master")
	void updateByKey(ActivityGroup group);
	/**
	 * 方法描述：修改库存
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午3:53:02 <br/>
	 * @param i
	 * @param id
	 */
	void updateStockById(@Param("stock")int stock,@Param("id")Integer id);
}