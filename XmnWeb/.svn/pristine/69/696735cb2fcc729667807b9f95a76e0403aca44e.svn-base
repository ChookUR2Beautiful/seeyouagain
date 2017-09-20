/**
 * 
 */
package com.xmniao.xmn.core.fresh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.fresh.dao.TFreshActivityAuctionBiddingDao;
import com.xmniao.xmn.core.fresh.entity.TFreshActivityAuctionBidding;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：FreshActivityAuctionBiddingService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2017-3-1 下午5:42:03 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class FreshActivityAuctionBiddingService extends BaseService<TFreshActivityAuctionBidding> {
	
	@Autowired
	private TFreshActivityAuctionBiddingDao auctionBiddingDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return auctionBiddingDao;
	}

}
