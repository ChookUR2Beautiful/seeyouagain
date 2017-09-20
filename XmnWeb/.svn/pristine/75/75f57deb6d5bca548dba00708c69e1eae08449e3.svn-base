package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialAttrGroup;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialAttrGroupDao
 * 
 * 类描述： 物料规格分组Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-24 下午5:19:01 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface DMaterialAttrGroupDao  extends BaseDao<DMaterialAttrGroup>{
	
	/**
	 * 
	 * 方法描述：根据物料ID删除商品物料规格分组信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-24下午7:55:37 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
    int deleteByMaterialId(Long materialId);


    /**
     * 
     * 方法描述：在此处添加方法描述 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-24下午7:51:58 <br/>
     * @param record
     * @return
     */
    @DataSource(value="designer")
    Integer addBatch(List<DMaterialAttrGroup> list);

    /**
     * 
     * 方法描述：获取商品物料规格分组信息列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2016-11-24下午7:51:58 <br/>
     * @param record
     * @return
     */
    @DataSource(value="designer")
    List<DMaterialAttrGroup> getList(DMaterialAttrGroup attrGroup);

}