/**
 * 
 */
package com.xmniao.xmn.core.business_statistics.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_statistics.dao.TMicrographTemplateDao;
import com.xmniao.xmn.core.business_statistics.dao.TMicrographTemplateTagDao;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographTemplate;
import com.xmniao.xmn.core.business_statistics.entity.TMicrographTemplateTag;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TMicrographTemplateService
 * 
 * 类描述：  微图助力模板Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-14 下午4:30:52 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TMicrographTemplateService extends BaseService<TMicrographTemplate> {

	@Autowired
	private TMicrographTemplateDao micrographTemplateDao;
	
	/**
	 * 注入模板标签服务
	 */
	@Autowired
	private TMicrographTemplateTagDao templateTagDao;
	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return micrographTemplateDao;
	}


	/**
	 * 方法描述：批量更新模板状态 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-14下午4:55:27 <br/>
	 * @param ids
	 * @param status
	 * @return
	 */
	public Resultable updateBatch(String ids, String status) {
		Resultable result = new Resultable();
		Map<String, Object> map=new HashMap<String,Object>();
		try {
			map.put("ids", ids.split(","));
			map.put("status", status);
			micrographTemplateDao.updateBatch(map);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("批量更新模板状态成功:"+e.getMessage(), e);
		}
		return result;
	}


	/**
	 * 方法描述：保存模板基础信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-16上午10:25:34 <br/>
	 * @param template
	 */
	public void saveInfo(TMicrographTemplate template) {
		template.setCreateTime(new Date());
		template.setUpdateTime(new Date());
		micrographTemplateDao.add(template);
		syncTemplateTag(template);
		
	}


	/**
	 * 方法描述：同步模板关联标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-16上午10:26:44 <br/>
	 * @param template
	 */
	private void syncTemplateTag(TMicrographTemplate template) {
		List<TMicrographTemplateTag> templateTagList=new ArrayList<TMicrographTemplateTag>();
		Integer templateId = template.getId();
		templateTagDao.deleteByTemplateId(templateId);
		String templateTagVals = template.getTemplateTagVals();
		if(StringUtils.isNotBlank(templateTagVals)){
			String[] tagValArray = templateTagVals.split(",");
			for(String tagVal:tagValArray){
				TMicrographTemplateTag templateTag=new TMicrographTemplateTag();
				String tagId = tagVal.split("_")[0];
				templateTag.setTemplateId(templateId);
				templateTag.setTagId(Integer.valueOf(tagId));
				templateTagList.add(templateTag);
			}
		}
		
		if(templateTagList!=null && templateTagList.size()>0){
			templateTagDao.addBatch(templateTagList);
		}
	}


	/**
	 * 方法描述：更新模板基础信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-16上午10:38:15 <br/>
	 * @param template
	 */
	public void updateInfo(TMicrographTemplate template) {
		template.setUpdateTime(new Date());
		micrographTemplateDao.update(template);
		syncTemplateTag(template);
		
	}

}
