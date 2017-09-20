package com.xmniao.xmn.core.common.dao;


import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.common.entity.TAdvertising;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * @项目名称：XmnWeb
 * 
 * @类名称：AdvertisingDao
 * 
 * @类描述： 广告轮播
 * 
 * @创建人：zhou'sheng
 * 
 * @创建时间：2014年11月19日10时27分38秒
 * 
 * @Copyright:广东寻蜜鸟网络技术有限公司
 */
public interface AdvertisingDao extends BaseDao<TAdvertising>{
	/*
	 * 用户客户端广告轮番图管理，在公共广告轮番图上的拓展
	 * 客户端只展示部分广告
	 * 对客户端广告进行统计，进行分页操作
	 * 添加人：@yangxu
	 */
	//添加：客户端广告所有数据方法
	@DataSource("slave")
	public List<TAdvertising> getUserADList(TAdvertising tAdvertising);
	//添加：客户端数据计数器，用于分页
	@DataSource("slave")
	public Long getUserADListcount(TAdvertising tAdvertising);
	
	public List<TAdvertising> getSellerADList(TAdvertising tAdvertising);
	
	/**
	 * 用户端广告轮番图片管理
	 * @param advertising
	 * @return
	 * 
	 * author：@wangzhimin
	 */
	@DataSource("slave")
	public List<TAdvertising> getADListForUser(TAdvertising advertising);
	
	public List<TAdvertising> getADList(TAdvertising advertising);
	
	public Long getADListcount(TAdvertising tAdvertising);
}
