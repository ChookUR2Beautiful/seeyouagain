/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.live_anchor.dao.TFansCouponAnchorRefDao;
import com.xmniao.xmn.core.live_anchor.entity.TFansCouponAnchorRef;

/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TFansCouponAnchorRefService
 * 
 * 类描述：  主播通告发放粉丝券配置Service
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-1 下午5:50:51 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
@Service
public class TFansCouponAnchorRefService extends BaseService<TFansCouponAnchorRef> {
	
	/**
	 * 注入 主播通告发放粉丝券配置服务
	 */
	@Autowired
	private TFansCouponAnchorRefDao couponARDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected BaseDao getBaseDao() {
		return couponARDao;
	}

	/**
	 * 方法描述：查询通告发放的粉丝券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-2下午5:09:38 <br/>
	 * @param couponAnchorRefParam
	 * @return
	 */
	public TFansCouponAnchorRef selectCouponAnchorRef(TFansCouponAnchorRef couponAnchorRefParam) {
		return couponARDao.selectCouponAnchorRef(couponAnchorRefParam);
	}

}
