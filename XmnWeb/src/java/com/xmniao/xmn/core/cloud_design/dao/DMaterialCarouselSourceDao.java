package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCarouselSource;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCarouselSourceDao
 * 
 * 类描述： 物料模板元数据Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-24 下午9:30:48 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface DMaterialCarouselSourceDao extends BaseDao<DMaterialCarouselSource>{
	
	
	/**
	 * 
	 * 方法描述：获取物料元数据列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-26上午11:22:10 <br/>
	 * @param materialId
	 * @return
	 */
	@DataSource(value="designer")
	List<DMaterialCarouselSource> getList(DMaterialCarouselSource carouselSource);
	
	/**
	 * 
	 * 方法描述：删除物料元数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-26上午11:29:44 <br/>
	 * @param carouselSource
	 * @return
	 */
	@DataSource(value="designer")
    int delete(DMaterialCarouselSource carouselSource);
	
	/**
	 * 
	 * 方法描述：批量添加物料元数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-26上午11:29:44 <br/>
	 * @param carouselSource
	 * @return
	 */
	@DataSource(value="designer")
	Integer addBatch(List<DMaterialCarouselSource> list);

	/**
	 * 方法描述：获取图片、文字框架信息(不包含banner、背景图) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-6上午10:10:01 <br/>
	 * @param carouselSourceReq
	 * @return
	 */
	@DataSource(value="designer")
	List<DMaterialCarouselSource> getSourceList( DMaterialCarouselSource carouselSourceReq);

}