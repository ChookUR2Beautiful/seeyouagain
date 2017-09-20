package com.xmniao.xmn.core.business_statistics.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographTemplateTag;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographTemplateTagDao
 * 
 * 类描述： 模板标签关系Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-14 下午4:08:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TMicrographTemplateTagDao extends BaseDao<TMicrographTemplateTag>{
	
	/**
	 * 
	 * 方法描述：根据模板ID删除关联标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-16上午10:29:58 <br/>
	 * @param templateId
	 * @return
	 */
	@DataSource(value="slave")
	int deleteByTemplateId(Integer templateId);
	
}