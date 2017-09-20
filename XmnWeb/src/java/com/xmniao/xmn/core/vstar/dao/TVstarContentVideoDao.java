package com.xmniao.xmn.core.vstar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.vstar.entity.TVstarContentVideo;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarContentVideoDao
 * 
 * 类描述： V客学堂视频DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-23 上午10:29:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TVstarContentVideoDao extends BaseDao<TVstarContentVideo> {
    int deleteByPrimaryKey(Long id);

    int insert(TVstarContentVideo record);

    int insertSelective(TVstarContentVideo record);

    TVstarContentVideo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TVstarContentVideo record);

    int updateByPrimaryKey(TVstarContentVideo record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日上午10:50:24 <br/>
	 * @param asList
	 * @return
	 */
	List<TVstarContentVideo> getByIds(List<String> asList);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日下午2:48:03 <br/>
	 * @param id
	 * @param asList
	 */
	void activateContextId(@Param("id")Long id,@Param("list") List<String> asList);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日下午5:59:52 <br/>
	 * @param longValue
	 * @return
	 */
	List<TVstarContentVideo> getListByContentId(long longValue);
}