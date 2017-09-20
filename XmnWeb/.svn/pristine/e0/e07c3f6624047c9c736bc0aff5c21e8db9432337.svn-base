package com.xmniao.xmn.core.businessman.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSellerAgio;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：SellerAgioDao
 * 
 * @类描述： 商户折扣设置
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月11日15时40分23秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface SellerAgioDao extends BaseDao<TSellerAgio> {
	/**
	 * 根据商家id查询商家当前正在使用的常规折扣.
	 * 
	 * @param sellerid
	 * @return
	 */
	@DataSource("slave")
	public TSellerAgio getUsingCommonAgion(Long sellerid);

	public Integer updatebysellerid(TSellerAgio tSellerAgio);

	public Integer updatebyselleridAndstatusAndtype(TSellerAgio tSellerAgio);
}
