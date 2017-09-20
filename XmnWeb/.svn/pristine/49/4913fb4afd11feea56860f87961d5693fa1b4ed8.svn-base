package com.xmniao.xmn.core.vstar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.TVstarContentAttachmentDao;
import com.xmniao.xmn.core.vstar.entity.TVstarContentAttachment;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarContentAttachmentDao
 * 
 * 类描述：V客学堂附件Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-23 上午10:24:02 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TVstarContentAttachmentService extends BaseService<TVstarContentAttachment>{

	@Autowired
	private TVstarContentAttachmentDao tVstarContentAttachmentDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return tVstarContentAttachmentDao;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日下午5:10:59 <br/>
	 * @param id
	 * @return
	 */
	public List<TVstarContentAttachment> getListByContentId(Long id) {
		return tVstarContentAttachmentDao.getListByContentId(id);
	}
}