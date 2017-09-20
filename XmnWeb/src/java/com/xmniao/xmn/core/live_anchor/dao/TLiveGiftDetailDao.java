package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGiftDetail;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveGiftDetailDao
 * 
 * 类描述： 礼物详情信息DAO
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-29 上午11:56:15 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveGiftDetailDao extends BaseDao<TLiveGiftDetail>{
	/**
	 * 
	 * 方法描述：根据主键查询礼物详情 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-29上午11:56:35 <br/>
	 * @param id
	 * @return
	 */
	TLiveGiftDetail selectById(Long id);
	
	/**
	 * 
	 * 方法描述：根据礼物ID获取商户分类信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-31下午5:03:32 <br/>
	 * @param gid
	 * @return
	 */
	TLiveGiftDetail selectByGid(Long gid);
	
	/**
	 * 
	 * 方法描述：根据主键删除礼物详情 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-3-29上午11:56:56 <br/>
	 * @param id
	 * @return
	 */
    int deleteById(Long id);
    
    /**
     * 
     * 方法描述：通过礼物ID删除礼物详细信息 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-3-31下午3:23:26 <br/>
     * @param gid
     * @return
     */
    int deleteByGid(Long gid);
    
    /**
     * 
     * 方法描述：通过礼物ID获取礼物详细信息列表 <br/>
     * 创建人：  huang'tao <br/>
     * 创建时间：2017-3-31下午3:16:57 <br/>
     * @param gid
     * @return
     */
    List<TLiveGiftDetail> getListByGid(Long gid);

    
}