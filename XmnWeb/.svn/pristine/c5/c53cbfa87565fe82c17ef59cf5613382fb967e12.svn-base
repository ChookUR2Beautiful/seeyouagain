/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.TFansCouponIssueRefDao;
import com.xmniao.xmn.core.live_anchor.entity.TFansCouponIssueRef;
import com.xmniao.xmn.core.live_anchor.entity.TLiveCoupon;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TFansCouponIssueRefService
 * 
 * 类描述： 在此处添加类描述
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-28 下午3:15:33 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TFansCouponIssueRefService extends BaseService<TFansCouponIssueRef> {
	
	/**
	 * 注入粉丝与抵用券配置关系服务
	 */
	@Autowired
	private TFansCouponIssueRefDao fansCouponIssueRefDao;

	/* (non-Javadoc)
	 * @see com.xmniao.xmn.core.base.BaseService#getBaseDao()
	 */
	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return fansCouponIssueRefDao;
	}
	
	
	public List<TFansCouponIssueRef> getVoucherList(TLiveCoupon liveCoupon){
		return fansCouponIssueRefDao.getVoucherList(liveCoupon);
	}

}
