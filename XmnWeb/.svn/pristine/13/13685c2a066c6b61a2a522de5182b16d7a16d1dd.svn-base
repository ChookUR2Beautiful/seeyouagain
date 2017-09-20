package com.xmniao.xmn.core.fresh.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.HotBrand;

public interface HotBrandDao extends BaseDao<HotBrand>{
    int deleteByPrimaryKey(Long id);

    int insert(HotBrand record);

    int insertSelective(HotBrand record);

    HotBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(HotBrand record);

    int updateByPrimaryKey(HotBrand record);

	/**
	 * 方法描述：删除所有此分类热门品牌
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月5日下午4:56:54 <br/>
	 * @param typeId
	 */
	void deleteByType(Long typeId);

	/**
	 * 方法描述：删除品牌模块
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月6日下午5:03:28 <br/>
	 * @param typeId
	 */
	void deleteBrand(Integer typeId);
}