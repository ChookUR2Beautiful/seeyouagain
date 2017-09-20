package com.xmniao.xmn.core.fresh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.FreshImage;

public interface FreshImageDao extends BaseDao<FreshImage>{
    int deleteByPrimaryKey(Long id);

    int insert(FreshImage record);

    int insertSelective(FreshImage record);

    FreshImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FreshImage record);

    int updateByPrimaryKey(FreshImage record);



	/**
	 * 方法描述：根据类型删除所有
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月5日上午11:44:48 <br/>
	 * @param typeId
	 * @param integer 
	 */
	void deleteAllByTypeId(@Param("typeId")Long typeId,@Param("type")Integer type);

	/**
	 * 方法描述：删除banner
	 * 创建人： jianming <br/>
	 * 创建时间：2017年1月6日下午4:50:11 <br/>
	 * @param typeId
	 * @param type 
	 */
	void deleteBanner(@Param("typeId")Integer typeId, @Param("type")Integer type);



}