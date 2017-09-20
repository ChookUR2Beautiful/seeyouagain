/**
 * 
 */
package com.xmniao.xmn.core.business_statistics.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_statistics.dao.TMicrographTemplateTagDao;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographTemplateTag;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographTemplateTagService
 * 
 * 类描述：模板标签关系Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-14 下午4:33:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TMicrographTemplateTagService extends BaseService<TMicrographTemplateTag> {

	@Autowired
	private TMicrographTemplateTagDao micrographTemplateTagDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return micrographTemplateTagDao;
	}

}
