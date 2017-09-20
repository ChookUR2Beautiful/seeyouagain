package com.xmniao.xmn.core.businessman.dao;


import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSellerDetailed;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：SellerDetailedDao
 * 
 * @类描述： 商家详情
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月11日15时46分32秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface SellerDetailedDao extends BaseDao<TSellerDetailed>{
   
	
	public TSellerDetailed getSellerDetailed(Long sellerid);
	
	
	public void batchUpdateIsChain(TSellerDetailed sellerDetailed);
}
