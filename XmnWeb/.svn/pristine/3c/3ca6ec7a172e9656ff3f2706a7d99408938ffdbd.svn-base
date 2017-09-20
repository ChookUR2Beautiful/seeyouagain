package com.xmniao.xmn.core.live_anchor.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TFansCouponAnchorRef;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TFansCouponAnchorRefDao
 * 
 * 类描述： 主播通告发放粉丝券配置Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-11-1 下午5:49:34 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TFansCouponAnchorRefDao extends BaseDao<TFansCouponAnchorRef> {
	
	/**
	 * 
	 * 方法描述：查询指定通告粉丝券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-11-2下午5:16:06 <br/>
	 * @param couponAnchorRefParam
	 * @return
	 */
	@DataSource(value="slave")
	TFansCouponAnchorRef selectCouponAnchorRef(TFansCouponAnchorRef couponAnchorRefParam);

}