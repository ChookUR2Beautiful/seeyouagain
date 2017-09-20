package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttrVal;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialAttrValDao
 * 
 * 类描述： 商品规格细项Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-25 上午11:48:50 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface DMaterialAttrValDao extends BaseDao<DMaterialAttrVal>{
	
	/**
	 * 
	 * 方法描述：删除指定物料下的规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-28下午6:09:37 <br/>
	 * @param materialId
	 * @return
	 */
	@DataSource(value="designer")
    int deleteByMaterialId(Long materialId);

	/**
	 * 
	 * 方法描述：批量新增物料规格细项 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-28下午6:09:37 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
    Integer addBatch(List<DMaterialAttrVal> list);

}