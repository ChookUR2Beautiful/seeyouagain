package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.cloud_design.entity.DMaterialCategoryAttr;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：DMaterialCategoryAttrDao
 * 
 * 类描述： 物料规格DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-9-30 上午9:43:06 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface DMaterialCategoryAttrDao extends BaseDao<DMaterialCategoryAttr>{
	
	/**
	 * 
	 * 方法描述：根据主键Id删除物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29下午6:01:12 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
    int deleteById(Long id);

	/**
	 * 
	 * 方法描述：添加物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29下午6:04:16 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="designer")
    void add(DMaterialCategoryAttr record);

	/**
	 * 
	 * 方法描述：根据主键Id查询物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-29下午6:05:39 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
    DMaterialCategoryAttr selectById(Long id);
	
	/**
	 * 
	 * 方法描述：获取物料规格列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-8上午11:47:50 <br/>
	 * @param categoryAttr
	 * @return
	 */
	@DataSource(value="designer")
	List<DMaterialCategoryAttr> getList(DMaterialCategoryAttr categoryAttr);

	/**
	 * 
	 * 方法描述：更新物料规格 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-9-30上午9:45:39 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="designer")
    Integer update(DMaterialCategoryAttr record);

	/**
	 * 方法描述：统计物料规格数量 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-8下午2:31:21 <br/>
	 * @param categoryAttr
	 * @return
	 */
	@DataSource(value="designer")
	Long count(DMaterialCategoryAttr categoryAttr);

}