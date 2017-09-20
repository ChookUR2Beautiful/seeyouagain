package com.xmniao.xmn.core.vstar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.vstar.entity.VstarLiverContentVideo;

public interface VstarLiverContentVideoDao extends BaseDao<VstarLiverContentVideo>{
    int deleteByPrimaryKey(Long id);

    int insert(VstarLiverContentVideo record);

    int insertSelective(VstarLiverContentVideo record);

    VstarLiverContentVideo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(VstarLiverContentVideo record);

    int updateByPrimaryKey(VstarLiverContentVideo record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月18日下午6:23:32 <br/>
	 * @param asList
	 * @return
	 */
	List<VstarLiverContentVideo> getByIds(List<String> list);
	
	void activateContextId(@Param("id")Long id,@Param("list") List<String> asList);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月19日下午3:14:05 <br/>
	 * @param longValue
	 * @return
	 */
	List<VstarLiverContentVideo> getListByContentId(long longValue);
}