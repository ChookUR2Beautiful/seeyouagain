/**
 * 
 */
package com.xmniao.xmn.core.fresh.dao;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.ActivityGroup;
import com.xmniao.xmn.core.fresh.entity.TSaleGroup;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 项目名称：XmnWeb
 * 
 * 类名称：SaleGroupDao
 *
 * 类描述：saleGroupDao
 * 
 * 创建人： lifeng
 * 
 * 创建时间：2016年8月6日下午5:56:02
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface SaleGroupDao extends BaseDao<TSaleGroup> {

	/**
	 * 方法描述：在此处添加方法描述
	 * 创建人：lifeng
	 * 创建时间：2016年8月9日上午9:28:10
	 * @param pid
	 */
	@DataSource("master")
	void deleteByCodeId(long codeId);

	/**
	 * 方法描述：根据编号和规格id查询
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月28日下午7:28:32 <br/>
	 * @param activityGroup
	 * @return
	 */
	@DataSource("slave")
	TSaleGroup findbyPvid(ActivityGroup activityGroup);

	/**
	 * 方法描述：转移库存
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月28日下午7:53:00 <br/>
	 * @param activityGroup
	 */
	@DataSource("master")
	void transferStore(ActivityGroup activityGroup);
	
	@DataSource("master")
	int transferStore(@Param("stock")Integer stock,@Param("codeId")Long codeId,@Param("pvIds")String pvIds);

	/**
	 * 方法描述：活动库存修改时更新实际库存
	 * 创建人： jianming <br/>
	 * 创建时间：2016年12月29日下午3:06:43 <br/>
	 * @param i
	 * @param string 
	 * @param long1 
	 * @return 
	 */
	@DataSource("master")
	int updateActivityGroup(@Param("stock")int stock, @Param("codeId")Long codeId,@Param("pvIds") String pvIds);
	
}
