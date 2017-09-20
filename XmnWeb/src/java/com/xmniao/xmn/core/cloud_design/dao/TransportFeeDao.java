/**
 * 
 */
package com.xmniao.xmn.core.cloud_design.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.cloud_design.entity.PostCondition;
import com.xmniao.xmn.core.cloud_design.entity.PostTemplate;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TransportFeeDao
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人： chenJie
 * 
 * 创建时间：2016年11月25日 下午5:59:24 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */

public interface TransportFeeDao {
	
	/**
	 * 
	 * 方法描述：获取运费模板列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月25日下午6:01:45 <br/>
	 * @return
	 */
	@DataSource(value="designer")
	List<PostTemplate> getList(PostTemplate postTemplate);
	
	/**
	 * 
	 * 方法描述：获取运费详情列表
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月25日下午6:02:38 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="designer")
	List<PostCondition> getConditions(Long id);
	
	/**
	 * 
	 * 方法描述：统计运费模板总数
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月26日下午3:27:46 <br/>
	 * @return
	 */
	@DataSource(value="designer")
	Long count();
	
	/**
	 * 
	 * 方法描述：获取全国省级区域
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月28日下午4:06:18 <br/>
	 * @return
	 */
	@DataSource(value="slave")
	List<Map<String,Object>> getAreaList();
	
	/**
	 * 
	 * 方法描述：新增运费模板
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月28日下午5:11:56 <br/>
	 * @param postTemplate
	 * @return
	 */
	@DataSource(value="designer")
	Integer addTemplate(PostTemplate postTemplate);
	
	/**
	 * 
	 * 方法描述：新增运费条件
	 * 创建人： chenJie <br/>
	 * 创建时间：2016年11月28日下午5:28:00 <br/>
	 * @param postCondition
	 * @return
	 */
	@DataSource(value="designer")
	Integer addCondition(PostCondition postCondition);
	
	@DataSource(value="designer")
	Integer deleteTemplate(PostTemplate postTemplate);
	
	@DataSource(value="designer")
	Integer deleteConditions(Long id);
	
	@DataSource(value="designer")
	Integer update(PostTemplate postTemplate);
	
	@DataSource(value="designer")
	Integer updateSupplier(PostTemplate postTemplate);
	
	@DataSource(value="designer")
	Integer deleteOldTemplate(PostTemplate postTemplate);
}
