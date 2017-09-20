/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.cloud_design.dao.DMaterialCategoryTagRelationDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryTagRelation;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryTagRelationService
 * 
 * 类描述： 物料分类、标签关联关系Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-18 下午3:29:43 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class DMaterialCategoryTagRelationService extends BaseService<DMaterialCategoryTagRelation> {
	
	/**
	 * 注入物料分类、标签关联关系Dao
	 */
	@Autowired
	private DMaterialCategoryTagRelationDao categoryTagRelationDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return categoryTagRelationDao;
	}
	
	/**
	 * 
	 * 方法描述：获取分类下关联标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-18下午3:19:51 <br/>
	 * @param tagRelation
	 * @return
	 */
	public List<DMaterialCategoryTagRelation> getList(DMaterialCategoryTagRelation tagRelation){
		return categoryTagRelationDao.getList(tagRelation);
	}
	
	/**
	 * 
	 * 方法描述：删除指定分类下关联标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-18下午3:19:51 <br/>
	 * @param id
	 * @return
	 */
    public int deleteByCategoryId(Long categoryId){
		return categoryTagRelationDao.deleteByCategoryId(categoryId);
	}
	
	/**
	 * 
	 * 方法描述：批量新增物料分类、标签关联关系 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午5:26:06 <br/>
	 * @param categoryId
	 * @return
	 */
	public Integer addBatch(List<DMaterialCategoryTagRelation> list){
		return categoryTagRelationDao.addBatch(list);
	}
	
	

}
