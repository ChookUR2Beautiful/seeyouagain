package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGift;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveGiftMapper
 *
 * 类描述：直播礼物Dao
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-17下午2:10:28
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveGiftDao extends BaseDao<TLiveGift>{
    int deleteById(Integer id);

	/**
	 * 方法描述：根据礼物ID数据获取礼物
	 * 创建人： huang'tao
	 * 创建时间：2016-8-29下午8:54:45
	 * @param split
	 * @return
	 */
	List<TLiveGift> getListByIds(Object[] ids);
	
	/**
	 * 
	 * 方法描述：获取指定礼包可添加的礼物
	 * 创建人： huang'tao
	 * 创建时间：2016-8-31上午10:47:33
	 * @param giftBagId
	 * @return
	 */
	List<TLiveGift> getGiftListToAdd(TLiveGift liveGift);
	
	/**
	 * 
	 * 方法描述：获取指定礼包可添加的礼物数量
	 * 创建人： huang'tao
	 * 创建时间：2016-8-31上午11:17:40
	 * @param giftBagId
	 * @return
	 */
	Long getGiftToAddCount(TLiveGift liveGift);
	
	/**
	 * 
	 * 方法描述：获取礼物基础信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-7-26上午11:19:44 <br/>
	 * @param liveGift
	 * @return
	 */
	List<TLiveGift> getBaseList(TLiveGift liveGift);
}