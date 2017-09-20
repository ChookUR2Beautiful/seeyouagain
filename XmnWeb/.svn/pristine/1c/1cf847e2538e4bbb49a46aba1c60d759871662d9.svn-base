package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TFansCouponIssueRef;
import com.xmniao.xmn.core.live_anchor.entity.TLiveCoupon;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TFansCouponIssueRefDao
 * 
 * 类描述： 直播粉丝券与抵用券配置关系Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-10-28 下午1:52:03 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TFansCouponIssueRefDao extends BaseDao<TFansCouponIssueRef> {
	
	/**
	 * 
	 * 方法描述：根据主键删除记录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-28下午2:27:47 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="master")
    int deleteByPrimaryKey(Integer id);

	/**
	 * 
	 * 方法描述：添加记录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-28下午2:29:48 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="master")
    int insertSelective(TFansCouponIssueRef record);

	/**
	 * 
	 * 方法描述：根据主键获取记录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-28下午2:31:14 <br/>
	 * @param id
	 * @return
	 */
	@DataSource(value="slave")
    TFansCouponIssueRef selectByPrimaryKey(Integer id);

	/**
	 * 
	 * 方法描述：更新记录 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-28下午2:35:58 <br/>
	 * @param record
	 * @return
	 */
	@DataSource(value="master")
    int updateByPrimaryKey(TFansCouponIssueRef record);
	
	/**
	 * 
	 * 方法描述：获取指定直播粉丝券下的抵用券列表 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-28下午3:10:59 <br/>
	 * @param coupon
	 * @return
	 */
	@DataSource(value="slave")
	List<TFansCouponIssueRef> getVoucherList(TLiveCoupon coupon);
	
	/**
	 * 
	 * 方法描述：删除抵用券 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-10-31下午2:50:49 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="master")
	int deleteVouchersByGiftIdNotIn(Map<String,Object> map);
	
	

}