package com.xmniao.xmn.core.vstar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.vstar.entity.VstarLiverContent;

public interface VstarLiverContentDao extends BaseDao<VstarLiverContent>{
    int deleteByPrimaryKey(Long id);

    int insert(VstarLiverContent record);

    int insertSelective(VstarLiverContent record);

    VstarLiverContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VstarLiverContent record);

    int updateByPrimaryKeyWithBLOBs(VstarLiverContent record);

    int updateByPrimaryKey(VstarLiverContent record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月21日下午2:54:50 <br/>
	 * @param asList
	 * @param status
	 */
	void updateStatusBatch(@Param("list") List<String> asList,@Param("status") String status);
}