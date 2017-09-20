package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.businessman.entity.TSeller;
import com.xmniao.xmn.core.live_anchor.entity.TSellerPackage;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TSellerPackageDao extends BaseDao<TSellerPackage>{
	
	@DataSource("slave")
    int deleteByPrimaryKey(Integer id);
    
    @DataSource("master")
    int insertSelective(TSellerPackage record);


    @DataSource("slave")
    int updateByPrimaryKeySelective(TSellerPackage record);
    
	/**
	 * 获取专题信息列表
	 */
	@DataSource("slave")
	public List<TSellerPackage> getSellerPackageList(TSellerPackage sellerPackage);
	
	/**
	 * 获取专题信息列表specialTopicCount
	 */
	@DataSource("slave")
	public Long sellerPackageCount(TSellerPackage sellerPackage);
	
	/**
	 * 方法描述：上下架操作，推荐操作<br/>
	 * 创建人： caiyl <br/>
	 * 创建时间：2017年2月28日下午4:52:16 <br/>
	 * @param seller
	 * @return
	 */
	@DataSource("slave")
	Integer updateStatusOption(TSellerPackage sellerPackage);

}