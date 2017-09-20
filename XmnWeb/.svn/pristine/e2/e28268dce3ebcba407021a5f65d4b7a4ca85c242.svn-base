package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGiftBag;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGiftBagSet;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 项目名称：XmnWeb_LVB
 * 
 * 类名称：TLiveGiftBagDao
 *
 * 类描述：礼物礼包Dao
 * 
 * 创建人： huang'tao
 * 
 * 创建时间：2016-8-29下午6:04:00
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveGiftBagDao extends BaseDao<TLiveGiftBag>{
	
	/**
	 * 
	 * 方法描述：根据礼包ID删除礼包
	 * 创建人： huang'tao
	 * 创建时间：2016-8-29下午6:07:30
	 * @param id
	 * @return
	 */
	@DataSource(value="master")
    int deleteBagSetById(Integer id);


    /**
     * 
     * 方法描述：根据礼包ID查询礼包
     * 创建人： huang'tao
     * 创建时间：2016-8-29下午6:05:32
     * @param id
     * @return
     */
    @DataSource(value = "slave")
    TLiveGiftBag selectBagById(Integer id);
    
    /**
     * 
     * 方法描述：获取第一条礼包记录
     * 创建人： huang'tao
     * 创建时间：2016-8-29下午6:05:32
     * @param id
     * @return
     */
    @DataSource(value = "slave")
    TLiveGiftBag selectTheFirstGiftBag();
    
    
    /**
     * 
     * 方法描述：添加礼包并返回礼包ID
     * 创建人： huang'tao
     * 创建时间：2016-8-30上午9:53:23
     * @param liveGiftBag
     * @return
     */
    @DataSource(value="master")
    Integer addBagReturnId(TLiveGiftBag liveGiftBag);
    
    /**
     * 
     * 方法描述：查询礼包数量
     * 创建人： huang'tao
     * 创建时间：2016-8-29下午6:05:32
     * @param id
     * @return
     */
    @DataSource(value = "slave")
    long selectBagCount(TLiveGiftBag liveGiftBag);
    
    /**
     * 
     * 方法描述：批量添加礼包礼物
     * 创建人： huang'tao
     * 创建时间：2016-8-30上午11:39:36
     * @param giftBagSet
     * @return
     */
    @DataSource(value="master")
    Integer addBatchGifts(List<TLiveGiftBagSet> giftBagSet);
    
    
    /**
     * 
     * 方法描述：查询礼包礼物数量
     * 创建人： huang'tao
     * 创建时间：2016-8-29下午6:05:32
     * @param id
     * @return
     */
    @DataSource(value = "slave")
    List<TLiveGiftBagSet> selectBagSetList(TLiveGiftBagSet liveGiftBagSet);
    
    /**
     * 
     * 方法描述：查询礼包礼物数量
     * 创建人： huang'tao
     * 创建时间：2016-8-29下午6:05:32
     * @param id
     * @return
     */
    @DataSource(value = "slave")
    long giftBagSetCount(TLiveGiftBagSet liveGiftBagSet);
    
    /**
     * 
     * 方法描述：更新礼包中某个礼物的数量
     * 创建人： huang'tao
     * 创建时间：2016-8-31上午10:07:53
     * @param liveGiftBagSet
     * @return
     */
    @DataSource(value="master")
    Integer updateGiftNums(TLiveGiftBagSet liveGiftBagSet);
    
    
}