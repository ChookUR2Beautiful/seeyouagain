package com.xmniao.xmn.core.vstar.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.vstar.entity.TVstarContentAttachment;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarContentAttachmentDao
 * 
 * 类描述：V客学堂附件DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-23 上午10:24:02 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TVstarContentAttachmentDao extends BaseDao<TVstarContentAttachment>{
    int deleteByPrimaryKey(Long id);

    int insert(TVstarContentAttachment record);

    int insertSelective(TVstarContentAttachment record);

    TVstarContentAttachment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TVstarContentAttachment record);

    int updateByPrimaryKey(TVstarContentAttachment record);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日下午2:43:16 <br/>
	 * @param id
	 * @param asList
	 */
	void activateContextId(@Param("id")Long id,@Param("list") List<String> asList);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日下午5:11:42 <br/>
	 * @param id
	 * @return
	 */
	List<TVstarContentAttachment> getListByContentId(Long id);
}