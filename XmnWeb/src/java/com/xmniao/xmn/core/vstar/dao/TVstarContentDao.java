package com.xmniao.xmn.core.vstar.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.vstar.entity.TVstarContent;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarContentDao
 * 
 * 类描述： V客学堂内容资料DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-23 上午10:26:52 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TVstarContentDao extends BaseDao<TVstarContent>{
    int deleteByPrimaryKey(Long id);

    int insert(TVstarContent record);

    int insertSelective(TVstarContent record);

    TVstarContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TVstarContent record);

    int updateByPrimaryKeyWithBLOBs(TVstarContent record);

    int updateByPrimaryKey(TVstarContent record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日下午4:29:54 <br/>
	 * @param id
	 * @return
	 */
	TVstarContent getMainById(Integer id);
}