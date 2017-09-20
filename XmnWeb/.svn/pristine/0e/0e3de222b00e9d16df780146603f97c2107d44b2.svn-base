package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttrVal;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryAttrValDao
 * 
 * 类描述： 物料规格细项Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-30 下午3:02:14 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface DMaterialCategoryAttrValDao {
	
	/**
	 * 
	 * 方法描述：删除物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:04:44 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
    int deleteById(Long id);
	
	/**
	 * 
	 * 方法描述：根据物料规格ID删除物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:04:44 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
	int deleteByAttrId(Long categoryAttrId);


	/**
	 * 
	 * 方法描述：添加物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:05:35 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="designer")
    int add(DMaterialCategoryAttrVal record);
	
	/**
	 * 
	 * 方法描述：批量添加物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:05:35 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="designer")
	int addBatch(List<DMaterialCategoryAttrVal> list);

	/**
	 * 
	 * 方法描述：查询物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:05:35 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="designer")
    DMaterialCategoryAttrVal selectById(Long id);
	
	/**
	 * 
	 * 方法描述：查询物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:05:35 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="designer")
	List<DMaterialCategoryAttrVal> getList(DMaterialCategoryAttrVal categoryAttrVal);

	/**
	 * 
	 * 方法描述：更新物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30下午3:05:35 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="designer")
    int update(DMaterialCategoryAttrVal record);

}