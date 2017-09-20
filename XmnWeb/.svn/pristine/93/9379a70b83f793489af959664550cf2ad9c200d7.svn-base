/**
 * 
 */
package com.xmniao.xmn.core.vstar.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.vstar.dao.VstarLiverContentAttachmentDao;
import com.xmniao.xmn.core.vstar.dao.VstarLiverContentDao;
import com.xmniao.xmn.core.vstar.dao.VstarLiverContentVideoDao;
import com.xmniao.xmn.core.vstar.entity.VstarLiverContent;
import com.xmniao.xmn.core.vstar.entity.VstarLiverContentAttachment;
import com.xmniao.xmn.core.vstar.entity.VstarLiverContentVideo;

/**
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：VstarLiverContentService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： jianming
 * 
 * 创建时间：2017年7月18日 上午11:48:20 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class VstarLiverContentService extends BaseService<VstarLiverContent>{

	@Autowired
	private VstarLiverContentDao vstarLiverContentDao;
	
	@Autowired
	private VstarLiverContentAttachmentDao  vstarLiverContentAttachmentDao;
	
	@Autowired
	private VstarLiverContentVideoDao vstarLiverContentVideoDao;
	
	@Override
	protected BaseDao getBaseDao() {
		return vstarLiverContentDao;
	}

	/**
	 * 方法描述：
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月19日下午2:11:51 <br/>
	 * @param content
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateMain(VstarLiverContent content) {
		vstarLiverContentDao.update(content);
		vstarLiverContentAttachmentDao.activateContextId(content.getId(),Arrays.asList(content.getFileIds().split(",")));
		vstarLiverContentVideoDao.activateContextId(content.getId(),Arrays.asList(content.getVideoIds().split(",")));
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月19日下午2:12:30 <br/>
	 * @param content
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addMain(VstarLiverContent content) {
		vstarLiverContentDao.add(content);
		vstarLiverContentAttachmentDao.activateContextId(content.getId(),Arrays.asList(content.getFileIds().split(",")));
		vstarLiverContentVideoDao.activateContextId(content.getId(),Arrays.asList(content.getVideoIds().split(",")));
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月19日下午3:12:14 <br/>
	 * @param id
	 * @return
	 */
	public Map<String, List> getContentEditMsg(Integer id) {
		List<VstarLiverContentAttachment> listByContentId = vstarLiverContentAttachmentDao.getListByContentId(id.longValue());
		List<VstarLiverContentVideo> videos=vstarLiverContentVideoDao.getListByContentId(id.longValue());
		Map<String,List> map= new HashMap<>();
		map.put("attachment", listByContentId);
		map.put("video", videos);
		return map;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月20日下午3:34:45 <br/>
	 * @param content
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateRange(VstarLiverContent content) {
		vstarLiverContentDao.update(content);
		vstarLiverContentAttachmentDao.activateContextId(content.getId(),Arrays.asList(content.getFileIds().split(",")));
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月20日下午3:34:47 <br/>
	 * @param content
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void addRange(VstarLiverContent content) {
		vstarLiverContentDao.add(content);
		vstarLiverContentAttachmentDao.activateContextId(content.getId(),Arrays.asList(content.getFileIds().split(",")));
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年7月21日下午2:52:55 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	public Resultable updateStatusBatch(String ids, String status) {
		try {
			vstarLiverContentDao.updateStatusBatch(Arrays.asList(ids.split(",")),status);
			return Resultable.success();
		} catch (Exception e) {
			log.error(e);
			return Resultable.defeat();
		}
	}

}
