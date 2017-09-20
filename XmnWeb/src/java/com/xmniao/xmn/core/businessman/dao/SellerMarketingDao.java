package com.xmniao.xmn.core.businessman.dao;


import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSellerMarketing;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：SellerMarketingDao
 * 
 * @类描述： 商家营销关系
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月11日15时56分48秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface SellerMarketingDao extends BaseDao<TSellerMarketing>{
	/**
	 * 查询指定商家是否指定参加指定活动
	 * @param map {aid:活动编号,item:商家编号(数组)}
	 * @return
	 */
	@DataSource("slave")
	List<Integer> getSellerMarketingBySellerid(Map<String, Object> map);
    
	@DataSource("slave")
	Long getActivtyListCount(TSellerMarketing tSellerMarketing);
	
	int addBatch(TSellerMarketing tSellerMarketing);
}
