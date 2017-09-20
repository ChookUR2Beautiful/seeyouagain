package com.xmniao.xmn.core.businessman.dao;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.CatehomeComment;

public interface CatehomeCommentDao extends BaseDao<CatehomeComment>{
    int deleteByPrimaryKey(Long id);

    int insert(CatehomeComment record);

    int insertSelective(CatehomeComment record);

    CatehomeComment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CatehomeComment record);

    int updateByPrimaryKey(CatehomeComment record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月18日下午3:39:22 <br/>
	 * @param id
	 * @param sort
	 * @return
	 */
	int updateCommentSort(@Param("id")Integer id,@Param("sort") Integer sort);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月18日下午3:42:45 <br/>
	 * @param id
	 * @return
	 */
	int deleteComment(Integer id);
}