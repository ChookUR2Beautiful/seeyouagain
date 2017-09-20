/**
 * 
 */
package com.xmniao.xmn.core.business_statistics.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.business_statistics.dao.TSaasTagCategoryDao;
import com.xmniao.xmn.core.business_statistics.dao.TSaasTagDao;
import com.xmniao.xmn.core.business_statistics.entity.TSaasTag;
import com.xmniao.xmn.core.business_statistics.entity.TSaasTagCategory;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TSaasTagCategoryService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-9 下午2:30:16 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TSaasTagCategoryService extends BaseService<TSaasTagCategory> {

	/**
	 * 注入SaaS商户端标签分类服务
	 */
	@Autowired
	private TSaasTagCategoryDao saasTagCategoryDao;
	
	/**
	 * 注入SaaS商户端标签选项服务
	 */
	@Autowired
	private TSaasTagDao saasTagDao;
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return saasTagCategoryDao;
	}

	/**
	 * 方法描述：保存SaaS标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-9下午4:28:03 <br/>
	 * @param saasCategory
	 * @return
	 */
	public Resultable saveInfo(TSaasTagCategory saasCategory) {
		Resultable result=new Resultable();
		try {
			saasTagCategoryDao.add(saasCategory);
			syncTagInfo(saasCategory);
			result.setSuccess(true);
			result.setMsg("保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("保存失败!");
			this.log.error("保存SaaS标签失败:"+e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 方法描述：同步标签选项(前端单个删除选项,同步只用做批量新增选项) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-10上午11:43:25 <br/>
	 * @param saasCategory
	 */
	private void syncTagInfo(TSaasTagCategory saasCategory) {
		List<TSaasTag> tagList=new ArrayList<TSaasTag>();
		Long tagCategoryId = saasCategory.getId();
		String tagVals = saasCategory.getTagVals();
		if(StringUtils.isNotBlank(tagVals)){
			String[] tagValArray = tagVals.split(",");
			for(String tagVal:tagValArray){
				String[] key_val = tagVal.split("_");
				String key = key_val[0];
				String val = key_val[1];
				if("undefined".equals(key)){
					TSaasTag tagBean=new TSaasTag();
					tagBean.setTagCategoryId(tagCategoryId);
					tagBean.setName(val);
					tagList.add(tagBean);
				}
			}
		}
		
		if(tagList!=null && tagList.size()>0){
			saasTagDao.addBatch(tagList);
		}
		
	}

	/**
	 * 方法描述：更新SaaS标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-9下午5:09:04 <br/>
	 * @param saasCategory
	 * @return
	 */
	public Resultable updateInfo(TSaasTagCategory saasCategory) {
		Resultable result= new Resultable();
		try {
			saasTagCategoryDao.update(saasCategory);
			syncTagInfo(saasCategory);
			result.setSuccess(true);
			result.setMsg("保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("保存失败!");
			this.log.error("更新SaaS标签失败:"+e.getMessage(), e);
		}
		return result;
	}

}
