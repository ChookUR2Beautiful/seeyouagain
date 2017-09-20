package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryTagRelation;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface DMaterialCategoryTagRelationDao extends BaseDao<DMaterialCategoryTagRelation>{
	
	
	/**
	 * 
	 * 方法描述：获取分类下标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-18下午3:19:51 <br/>
	 * @param tagRelation
	 * @return
	 */
	@DataSource(value="designer")
	List<DMaterialCategoryTagRelation> getList(DMaterialCategoryTagRelation tagRelation);
	
	/**
	 * 
	 * 方法描述：删除指定分类下关联标签 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-18下午3:19:51 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
    int deleteByCategoryId(Long categoryId);
	
	/**
	 * 
	 * 方法描述：批量新增物料分类、标签关联关系 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午5:26:06 <br/>
	 * @param categoryId
	 * @return
	 */
	@DataSource(value="designer")
	Integer addBatch(List<DMaterialCategoryTagRelation> list);

}