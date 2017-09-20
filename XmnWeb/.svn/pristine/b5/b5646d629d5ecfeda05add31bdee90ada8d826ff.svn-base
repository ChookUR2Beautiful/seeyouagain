package com.xmniao.xmn.core.vstar.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.vstar.dao.TVstarContentAttachmentDao;
import com.xmniao.xmn.core.vstar.dao.TVstarContentDao;
import com.xmniao.xmn.core.vstar.dao.TVstarContentVideoDao;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.vstar.dao.TVstarContentDao;
import com.xmniao.xmn.core.vstar.entity.TVstarContent;
import com.xmniao.xmn.core.vstar.entity.TVstarContentAttachment;
import com.xmniao.xmn.core.vstar.entity.TVstarContentVideo;

/**
 * 
 * 
 * 项目名称：XmnWeb_vstar
 * 
 * 类名称：TVstarContentDao
 * 
 * 类描述： V客学堂内容资料Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-6-23 上午10:26:52 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
@Service
public class TVstarContentService extends BaseService<TVstarContent>{

	@Autowired
	private TVstarContentDao tVstarContentDao;
	
	@Autowired
	private TVstarContentAttachmentDao tVstarContentAttachmentDao;
	
	@Autowired
	private TVstarContentVideoDao tVstarContentVideoDao;
	
	/**
	 * 注入V客学堂资料DAO
	 */
	@Autowired
	private TVstarContentDao vstarContentDao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return tVstarContentDao;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日下午2:34:41 <br/>
	 * @param vstarContent
	 */	
	@Transactional(propagation = Propagation.REQUIRED)
	public void addMain(TVstarContent vstarContent) {
		vstarContent.setContentType(1);
		tVstarContentDao.add(vstarContent);
		tVstarContentAttachmentDao.activateContextId(vstarContent.getId(),Arrays.asList(vstarContent.getFileIds().split(",")));
		tVstarContentVideoDao.activateContextId(vstarContent.getId(),Arrays.asList(vstarContent.getVideoIds().split(",")));
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月27日下午5:55:27 <br/>
	 * @param id
	 * @return
	 */
	public Map<String, List> getContentEditMsg(Integer id) {
		List<TVstarContentAttachment> listByContentId = tVstarContentAttachmentDao.getListByContentId(id.longValue());
		List<TVstarContentVideo> videos=tVstarContentVideoDao.getListByContentId(id.longValue());
		Map<String,List> map= new HashMap<>();
		map.put("attachment", listByContentId);
		map.put("video", videos);
		return map;
	}

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年6月28日上午10:46:44 <br/>
	 * @param vstarContent
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void updateMain(TVstarContent vstarContent) {
		tVstarContentDao.update(vstarContent);
		tVstarContentAttachmentDao.activateContextId(vstarContent.getId(),Arrays.asList(vstarContent.getFileIds().split(",")));
		tVstarContentVideoDao.activateContextId(vstarContent.getId(),Arrays.asList(vstarContent.getVideoIds().split(",")));
	}


	/**
	 * 方法描述：批量更新上架状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-6-26上午10:43:08 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	public Resultable updateStatusBatch(String ids, String status) {
		Resultable result=new Resultable();
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			if(StringUtils.isNotBlank(ids)){
				map.put("ids", ids.split(","));
				map.put("status", status);
				vstarContentDao.updateBatch(map);
				result.setSuccess(true);
				result.setMsg("操作成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败");
			
		}finally{
			
		}
		
		return result;
	}

}