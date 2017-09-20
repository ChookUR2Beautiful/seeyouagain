package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.live_anchor.entity.LiveRequestBean;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;



/**
 * 
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveRechargeAndRedPacketDao
 * 
 * 类描述： 直播充值和红包统计Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-31 上午11:27:59 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveRechargeAndRedPacketDao{
	
	
	/**
	 * 
	 * 方法描述：统计充值信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-19下午6:31:23 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="slave")
	Map<String,Object> getRechargeInfo(Map<String,Object> map);
	
	
	/**
	 * 
	 * 方法描述：根据充值金额分组统计充值信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-19下午6:31:23 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="slave")
	List<LiveRequestBean>  getRechargeGroupByPayment(LiveRequestBean requestBean);
	
	/**
	 * 
	 * 方法描述：统计红包信息 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-19下午6:31:23 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="slave")
	Map<String,Object> getRedPacketInfo(Map<String,Object> map);


	/**
	 * 方法描述：根据充值金额分组统计充值信息纪录数 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-20下午6:07:55 <br/>
	 * @param requestBean
	 * @return
	 */
	@DataSource(value="slave")
	long getRechargeGroupByPaymentCount(LiveRequestBean requestBean);
}