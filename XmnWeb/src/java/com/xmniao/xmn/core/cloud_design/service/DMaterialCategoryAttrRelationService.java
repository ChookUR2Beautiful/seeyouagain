/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCategoryAttrRelationDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttrRelation;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryAttrRelationService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-17 下午5:32:31 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialCategoryAttrRelationService extends BaseService<DMaterialCategoryAttrRelation> {
	
	/**
	 * 注入物料分类、规格关联关系Dao
	 */
	@Autowired
	private DMaterialCategoryAttrRelationDao categoryAttrRelationDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return categoryAttrRelationDao;
	}
	
	/**
	 * 
	 * 方法描述：获取物料分类、规格关联关系 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午5:26:06 <br/>
	 * @param record
	 * @return
	 */
	public List<DMaterialCategoryAttrRelation>  getList(DMaterialCategoryAttrRelation record){
		return categoryAttrRelationDao.getList(record);
	}
	
	
	/**
	 * 
	 * 方法描述：删除指定分类下的关联规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午5:26:06 <br/>
	 * @param categoryId
	 * @return
	 */
	public Integer deleteByCategoryId(long categoryId){
		return categoryAttrRelationDao.deleteByCategoryId(categoryId);
	}
	
	/**
	 * 
	 * 方法描述：删除指定规格下的关联分类 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午5:26:06 <br/>
	 * @param categoryId
	 * @return
	 */
	public Integer deleteByMaterialAttrId(long materialAttrId){
		return categoryAttrRelationDao.deleteByMaterialAttrId(materialAttrId);
	}
	
	/**
	 * 
	 * 方法描述：批量新增物料分类、规格关联关系 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午5:26:06 <br/>
	 * @param categoryId
	 * @return
	 */
	public Integer addBatch(List<DMaterialCategoryAttrRelation> list){
		return categoryAttrRelationDao.addBatch(list);
	}
	

}
