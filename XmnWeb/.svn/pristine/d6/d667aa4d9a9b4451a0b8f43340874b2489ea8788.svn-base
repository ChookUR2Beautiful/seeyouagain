package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.ActivityProduct;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface ActivityProductDao extends BaseDao<ActivityProduct>{
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityProduct record);

    int insertSelective(ActivityProduct record);

    ActivityProduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityProduct record);

    int updateByPrimaryKey(ActivityProduct record);

	/**
	 * 方法描述：根据活动id查询
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午1:54:23 <br/>
	 * @param id
	 * @return
	 */
    @DataSource("slave")
	List<ActivityProduct> getByActivityId(Integer id);

	/**
	 * 方法描述：根据codeid查询
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午2:49:48 <br/>
	 * @param long1
	 * @param integer 
	 * @return
	 */
    @DataSource("slave")
	ActivityProduct getByCodeId(@Param("codeId")Long codeId,@Param("activityId") Integer activityId);

	/**
	 * 方法描述：查询修改商品时被删除的商品
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午3:36:20 <br/>
	 * @param id
	 * @param listToString
	 * @return
	 */
	List<ActivityProduct> getByIds(@Param("activityId")Integer activityId, @Param("ids")String ids);

	/**
	 * 方法描述：删除商品,清空库存
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午3:58:12 <br/>
	 * @param id
	 */
	void clearStore(Integer id);

	/**
	 * 方法描述：加载预览数据
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午9:09:22 <br/>
	 * @param id
	 * @return
	 */
	List<ActivityProduct> getByPreview(Integer id);

	/**
	 * 方法描述：根据moduleId查找
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月6日上午9:59:52 <br/>
	 * @param id
	 * @return
	 */
	List<ActivityProduct> getByModuleId(Long id);

	/**
	 * 方法描述：删除模块商品
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月6日下午3:53:23 <br/>
	 * @param id
	 */
	void deleteByModuleUpdate(Long id);

	/**
	 * 方法描述：根据codeId查询进行中的活动商品
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月7日上午11:16:43 <br/>
	 * @param codeId
	 * @return
	 */
	List<ActivityProduct> getByBeginCodeId(Long codeId);

	/**
	 * 方法描述：查询有库存的没规格已结束的活动商品
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月19日下午3:56:41 <br/>
	 * @return
	 */
	List<ActivityProduct> selectEndActivityProduct();

	/**
	 * 方法描述：清除库存
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月19日下午4:27:39 <br/>
	 * @param id
	 */
	void clearStoreZero(Integer id);

	/**
	 * 方法描述：终止活动,改变商品状态
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月19日下午4:57:33 <br/>
	 * @param id
	 */
	void endActivity(Integer id);
}