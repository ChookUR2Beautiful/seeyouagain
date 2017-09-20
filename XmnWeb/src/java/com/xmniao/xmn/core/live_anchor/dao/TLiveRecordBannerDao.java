package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRecordBanner;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveRecordBannerDao
 * 
 * 类描述： 直播通告封面轮播图Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-1 下午8:27:07 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveRecordBannerDao extends BaseDao<TLiveRecordBanner>{

	/**
	 * 
	 * 方法描述：获取直播通告轮播图封面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-1下午8:41:11 <br/>
	 * @param recordBanner
	 * @return
	 */
	@DataSource(value="slave")
	List<TLiveRecordBanner> getList(TLiveRecordBanner recordBanner);
	
	/**
	 * 
	 * 方法描述：删除直播通告封面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-1下午8:41:32 <br/>
	 * @param recordBanner
	 * @return
	 */
	@DataSource(value="slave")
	int delete(TLiveRecordBanner recordBanner);
	

	/**
	 * 
	 * 方法描述：批量添加直播通告封面 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-1下午8:41:32 <br/>
	 * @param recordBanner
	 * @return
	 */
	@DataSource(value="slave")
	Integer addBatch(List<TLiveRecordBanner> list);
}