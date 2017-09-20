package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveReferrerCensus;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
 * 
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveReferrerCensusDao
 * 
 * 类描述： 直播企业推荐人统计Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-26 下午7:30:40 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveReferrerCensusDao extends BaseDao<TLiveReferrerCensus>{
	
	
	/**
	 * 
	 * 方法描述：根据企业级uid分组统计下线数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-26下午8:35:57 <br/>
	 * @return
	 */
	@DataSource(value="slave")
	List<TLiveReferrerCensus> countJuniorInfoGroupByEuid(Map<String,Object> map);
	
	
	/**
	 * 
	 * 方法描述：下线累计充值统计(已作废) <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-3上午10:54:40 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="slave")
	Map<String,Object> juniorRechargeCount(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：下线累计打赏统计 (已作废)<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-3上午10:54:40 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="slave")
	Map<String,Object> juniorRewardCount(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：下线累计消费统计 (已作废)<br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-1-3上午10:54:40 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="slave")
	Map<String,Object> juniorConsumeCount(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：根据uid获取下级uid数组 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午2:07:23 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="burs")
	List<Map<String,Object>> getJuniorUidsByUid(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：统计下线累计充值 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午2:11:35 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="slave")
	Map<String,Object> juniorRechargeCountByUids(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：统计下线累计打赏 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午2:11:35 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="slave")
	Map<String,Object> juniorRewardCountByUids(Map<String,Object> map);
	
	/**
	 * 
	 * 方法描述：统计下线累计消费 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午2:11:35 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="slave")
	Map<String,Object> juniorConsumeCountByUids(Map<String,Object> map);
	
	
	/**
	 * 
	 * 方法描述：统计下线充值总金额 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2017-2-9下午2:11:35 <br/>
	 * @param map
	 * @return
	 */
	@DataSource(value="burs")
	Map<String,Object> juniorAmountCountByUids(Map<String,Object> map);
	
}