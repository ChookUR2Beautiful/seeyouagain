package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttr;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialAttrDao
 * 
 * 类描述： 物料商品属性Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-24 下午3:53:08 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface DMaterialAttrDao extends BaseDao<DMaterialAttr>{
	
	
	/**
	 * 
	 * 方法描述：根据物料Id删除所有规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午4:07:01 <br/>
	 * @param materialId
	 * @return
	 */
	@DataSource(value="designer")
    int deleteByMaterialId(Long materialId);

	/**
	 * 
	 * 方法描述：批量插入物料商品规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午4:08:35 <br/>
	 * @param list
	 * @return
	 */
	@DataSource(value="designer")
    Integer addBatch(List<DMaterialAttr> list);
	
	/**
	 * 
	 * 方法描述：批量插入物料商品规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午4:08:35 <br/>
	 * @param materialAttr
	 * @return
	 */
	@DataSource(value="designer")
	void add(DMaterialAttr materialAttr);
	
	/**
	 * 
	 * 方法描述：获取物料商品规格列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午4:08:35 <br/>
	 * @param list
	 * @return
	 */
	@DataSource(value="designer")
	List<DMaterialAttr> getList(DMaterialAttr materialAttr);

}