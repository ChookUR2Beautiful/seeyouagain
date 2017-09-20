package com.xmniao.xmn.core.businessman.dao;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.CatehomeActivity;

public interface CatehomeActivityDao extends BaseDao<CatehomeActivity>{
    int deleteByPrimaryKey(Long id);

    int insert(CatehomeActivity record);

    int insertSelective(CatehomeActivity record);

    CatehomeActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CatehomeActivity record);

    int updateByPrimaryKey(CatehomeActivity record);

	/**
	 * 方法描述：修改排序
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月16日下午7:04:43 <br/>
	 * @param id
	 * @param sort
	 * @return
	 */
	int updateActivitySort(@Param("id")Long id,@Param("sort") Integer sort);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月16日下午7:08:03 <br/>
	 * @param id
	 * @return
	 */
	int deleteActivity(Long id);
}