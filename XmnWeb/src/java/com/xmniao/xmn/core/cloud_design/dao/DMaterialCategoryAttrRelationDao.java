package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttrRelation;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface DMaterialCategoryAttrRelationDao  extends BaseDao<DMaterialCategoryAttrRelation>{
	
	/**
	 * 
	 * 方法描述：获取物料分类、规格关联关系 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午5:26:06 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="designer")
	List<DMaterialCategoryAttrRelation>  getList(DMaterialCategoryAttrRelation record);
	
	
	/**
	 * 
	 * 方法描述：删除指定分类下的关联规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午5:26:06 <br/>
	 * @param categoryId
	 * @return
	 */
	@DataSource(value="designer")
	Integer deleteByCategoryId(long categoryId);
	
	/**
	 * 
	 * 方法描述：删除指定规格下的关联分类 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午5:26:06 <br/>
	 * @param categoryId
	 * @return
	 */
	@DataSource(value="designer")
	Integer deleteByMaterialAttrId(long materialAttrId);
	
	/**
	 * 
	 * 方法描述：批量新增物料分类、规格关联关系 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-17下午5:26:06 <br/>
	 * @param categoryId
	 * @return
	 */
	@DataSource(value="designer")
	Integer addBatch(List<DMaterialCategoryAttrRelation> list);
	
}