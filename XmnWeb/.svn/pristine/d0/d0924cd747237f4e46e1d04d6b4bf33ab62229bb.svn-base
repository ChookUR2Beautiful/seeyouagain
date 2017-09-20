/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.base.Resultable;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCategoryAttrRelationDao;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCategoryDao;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCategoryTagRelationDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategory;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttrRelation;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryTagRelation;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryService
 * 
 * 类描述： 物料分类Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-17 上午10:34:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialCategoryService extends BaseService<DMaterialCategory> {
	
	/**
	 * 注入物料分类Dao
	 */
	@Autowired
	private DMaterialCategoryDao materialCategoryDao;
	
	/**
	 * 注入物料分类、规格关联关系Dao
	 */
	@Autowired
	private DMaterialCategoryAttrRelationDao categoryAttrRelationDao;
	
	/**
	 * 注入物料分类、标签关联关系Dao
	 */
	@Autowired
	private DMaterialCategoryTagRelationDao categoryTagRelationDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return materialCategoryDao;
	}
	
	/**
	 * 
	 * 方法描述：根据主键删除分类 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-16下午6:32:54 <br/>
	 * @param id
	 * @return
	 */
    public int deleteById(Long id){
		return materialCategoryDao.deleteById(id);
	}


    /**
     * 
     * 方法描述：添加物料分类 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-16下午6:24:14 <br/>
     * @param id
     * @return
     */
	public void add(DMaterialCategory record){
    	materialCategoryDao.add(record);
    }

    /**
     * 
     * 方法描述：根据主键查询分类 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-16下午6:24:14 <br/>
     * @param id
     * @return
     */
    public DMaterialCategory selectById(Long id){
    	return materialCategoryDao.selectById(id);
    }

    /**
     * 
     * 方法描述：更新物料分类 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-16下午6:24:14 <br/>
     * @param id
     * @return
     */
    public  Integer update(DMaterialCategory record){
    	return materialCategoryDao.update(record);
    }
	

    /**
     * 
     * 方法描述：获取物料分类列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-16下午6:24:14 <br/>
     * @param id
     * @return
     */
    public List<DMaterialCategory> getList(DMaterialCategory record){
    	return materialCategoryDao.getList(record);
    }
    
    /**
     * 
     * 方法描述：获取物料分类总数 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-16下午6:24:14 <br/>
     * @param id
     * @return
     */
    public Long count(DMaterialCategory record){
    	return materialCategoryDao.count(record);
    }

	/**
	 * 方法描述：保存物料分类信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-18上午11:02:42 <br/>
	 * @param categoryRequest
	 * @return
	 */
	public Resultable saveCategoryInfo(DMaterialCategory categoryRequest) {
		Resultable result=new Resultable();
		try {
			materialCategoryDao.add(categoryRequest);
			syncCategoryAttrRelation(categoryRequest);
			syncCategoryTagRelation(categoryRequest);
			result.setSuccess(true);
			result.setMsg("保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("保存失败!");
			this.log.error("新增物料分类失败："+e.getMessage(), e);
		}
		return result;
	}

	/**
	 * 方法描述：同步物料分类、标签关联关系 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-18上午11:26:43 <br/>
	 * @param categoryRequest
	 */
	private void syncCategoryTagRelation(DMaterialCategory categoryRequest) {
		String tags = categoryRequest.getTags();//关联标签 ( key_value,key_value	eg:1_西餐,2_中餐)
		List<DMaterialCategoryTagRelation> categoryTagRelationList = new ArrayList<DMaterialCategoryTagRelation>();
		Long categoryId = categoryRequest.getId();
		
		if(StringUtils.isNotBlank(tags)){
			String[] tagArray = tags.split(",");
			for(String key_value : tagArray){
				DMaterialCategoryTagRelation relation = new DMaterialCategoryTagRelation();
				
				String[] tagInfo = key_value.split("_");
				String tagId = tagInfo[0];
				String tagVal = tagInfo[1];
				
				relation.setCategoryId(categoryId);
				relation.setMaterialTagId(Long.valueOf(tagId));
				relation.setMaterialTagVal(tagVal);
				categoryTagRelationList.add(relation);
			}
		}
		
		if(categoryTagRelationList!=null && categoryTagRelationList.size()>0){
			categoryTagRelationDao.addBatch(categoryTagRelationList);
		}
	}

	/**
	 * 方法描述：同步物料分类、规格关联关系 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-18上午11:26:39 <br/>
	 * @param categoryRequest
	 */
	private void syncCategoryAttrRelation(DMaterialCategory categoryRequest) {
		String attrs = categoryRequest.getAttrs();//关联规格( key_value,key_value	eg:1_主色调,2_副色调)
		List<DMaterialCategoryAttrRelation> categoryAttrRelationList =new ArrayList<DMaterialCategoryAttrRelation>();
		Long categoryId = categoryRequest.getId();
		String categoryName = categoryRequest.getName();
		
		if(StringUtils.isNotBlank(attrs)){
			String[] attrArray = attrs.split(",");
			for(String key_value:attrArray){
				DMaterialCategoryAttrRelation relation =new DMaterialCategoryAttrRelation();
				
				String[] attrInfo = key_value.split("_");
				String attrId = attrInfo[0];
				String attrName = attrInfo[1];
				
				relation.setCategoryId(categoryId);
				relation.setCategoryVal(categoryName);
				relation.setMaterialAttrId(Long.valueOf(attrId));
				relation.setMaterialAttrVal(attrName);
				
				categoryAttrRelationList.add(relation);
			}
		}
		//批量新增物料分类、规格关联关系 
		if(categoryAttrRelationList!=null && categoryAttrRelationList.size()>0){
			categoryAttrRelationDao.addBatch(categoryAttrRelationList);
		}
		
	}

	/**
	 * 方法描述：更新物料分类信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-18下午4:12:11 <br/>
	 * @param categoryRequest
	 * @return
	 */
	public Resultable updateCategoryInfo(DMaterialCategory categoryRequest) {
		// TODO Auto-generated method stub
		Resultable result = new Resultable();
		Long categoryId = categoryRequest.getId();
		try {
			materialCategoryDao.update(categoryRequest);
			categoryAttrRelationDao.deleteByCategoryId(categoryId);
			categoryTagRelationDao.deleteByCategoryId(categoryId);
			syncCategoryAttrRelation(categoryRequest);
			syncCategoryTagRelation(categoryRequest);
			result.setSuccess(true);
			result.setMsg("操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
			result.setMsg("操作失败!");
			this.log.error("更新物料分类信息失败："+e.getMessage(), e);
		}
		
		return result;
	}
}
