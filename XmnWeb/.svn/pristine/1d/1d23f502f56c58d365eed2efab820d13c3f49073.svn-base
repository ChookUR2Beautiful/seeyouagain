/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.VstarLiverContentAttachmentDao;
import com.xmniao.xmn.core.vstar.entity.TVstarContentAttachment;
import com.xmniao.xmn.core.vstar.entity.VstarLiverContentAttachment;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：VstarLiverContentAttachmentService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年7月18日 上午11:51:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class VstarLiverContentAttachmentService extends BaseService<VstarLiverContentAttachment>{

	@Autowired
	private VstarLiverContentAttachmentDao vstarLiverContentAttachmentDao;

	@Override
	protected BaseDao getBaseDao() {
		return vstarLiverContentAttachmentDao;
	}
	
	public List<VstarLiverContentAttachment> getListByContentId(Long id) {
		return vstarLiverContentAttachmentDao.getListByContentId(id);
	}

}
