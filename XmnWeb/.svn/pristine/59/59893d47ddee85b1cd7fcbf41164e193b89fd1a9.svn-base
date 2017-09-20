package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveRechargeCombo;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TLiveRechargeComboDao
 * 
 * 类描述： 直播鸟币充值套餐Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-8-31 下午6:10:08 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveRechargeComboDao extends BaseDao<TLiveRechargeCombo>{
	
	
	/**
	 * 
	 * 方法描述：根据ID删除直播鸟币重置套餐
	 * 创建人：  huang'tao
	 * 创建时间：2016-8-31下午6:11:28
	 * @param id
	 * @return
	 */
	@DataSource(value = "master")
    int deleteById(Integer id);
	
	
	/**
	 * 
	 * 方法描述：获取粉丝级别关联充值套餐金额
	 * 创建人：  huang'tao
	 * 创建时间：2016-8-31下午6:11:28
	 * @param id
	 * @return
	 */
	@DataSource(value = "master")
	List<TLiveRechargeCombo> getRechargeOfFansRankId();

}