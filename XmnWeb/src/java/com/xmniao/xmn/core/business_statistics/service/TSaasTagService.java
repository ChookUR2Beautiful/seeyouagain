/**
 * 
 */
package com.xmniao.xmn.core.business_statistics.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.business_statistics.dao.TSaasTagDao;
import com.xmniao.xmn.core.business_statistics.entity.TSaasTag;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSaasTagService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-9 下午6:29:07 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TSaasTagService extends BaseService<TSaasTag> {
	
	/**
	 * 注入SaaS标签选项服务
	 */
	@Autowired
	private TSaasTagDao saasTagDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return saasTagDao;
	}

	/**
	 * 方法描述：获取模板标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-15上午11:07:20 <br/>
	 * @param saasTag
	 * @return
	 */
	public List<TSaasTag> getTagsByType(TSaasTag saasTag) {
		return saasTagDao.getTagsByType(saasTag);
	}

}
