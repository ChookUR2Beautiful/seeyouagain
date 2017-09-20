package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.FreshActivityCommon;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
* @projectName: xmnService 
* @ClassName: FreshActivityCommonDao    
* @Description:通过活动dao   
* @author: liuzhihao   
* @date: 2016年12月22日 下午5:39:08
 */
@Repository
public interface FreshActivityCommonDao extends BaseDao<FreshActivityCommon>{
	@DataSource("master")
    int deleteByPrimaryKey(Integer id);

	@DataSource("master")
    int insert(FreshActivityCommon record);

	@DataSource("slave")
	FreshActivityCommon selectByPrimaryKey(Integer id);

	@DataSource("master")
    int updateByPrimaryKey(FreshActivityCommon record);

	@DataSource("slave")
    List<FreshActivityCommon> selectHostSaleActivity();

	/**
	 * 方法描述：分页查询
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月27日上午11:48:21 <br/>
	 * @param freshActivityCommon
	 * @return
	 */
	@DataSource("slave")
	List<FreshActivityCommon> selectList(FreshActivityCommon freshActivityCommon);

	/**
	 * 方法描述：统计条数
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月27日下午1:49:32 <br/>
	 * @param freshActivityCommon
	 * @return
	 */
	@DataSource("slave")
	Long countPageable(FreshActivityCommon freshActivityCommon);

	/**
	 * 方法描述：活动是否在进行中
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午2:28:11 <br/>
	 * @param id
	 * @return
	 */
	@DataSource("slave")
	FreshActivityCommon hasBeingActivity(Integer id);

	/**
	 * 方法描述：修改活动信息
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午2:40:00 <br/>
	 * @param activity
	 */
	@DataSource("master")
	void updateActivity(FreshActivityCommon activity);

	/**
	 * 方法描述：查询所有活动
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月4日上午10:30:46 <br/>
	 * @return
	 */
	@DataSource("slave")
	List<FreshActivityCommon> selectAllActivity();

	/**
	 * 方法描述：根据秒杀活动终止活动
	 * 创建人： jianming <br/>
	 * 创建时间：2017年2月22日上午9:43:38 <br/>
	 * @param id
	 * @param activityCommonEndStatus
	 */
	@DataSource("master")
	void updateStatus(@Param("id")Long id, @Param("status")int activityCommonEndStatus);

	
}