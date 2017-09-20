package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.LoseGift;
import com.xmniao.xmn.core.live_anchor.entity.TLiveGivedGift;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


/**
 * 
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveGivedGiftMapper
 * 
 * 类描述： 直播打赏礼物记录Dao
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-27 上午11:44:52 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface TLiveGivedGiftDao  extends BaseDao<TLiveGivedGift>{
	
	
	/**
	 * 
	 * 方法描述：打赏礼物概况统计 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-28下午3:15:10 <br/>
	 * @param liveGivedGift
	 * @return
	 */
	@DataSource(value="slave")
	Map<String,Object> generalCount(TLiveGivedGift liveGivedGift);
	
	/**
	 * 
	 * 方法描述：礼物打赏统计 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-28下午3:15:10 <br/>
	 * @param liveGivedGift
	 * @return
	 */
	@DataSource(value="slave")
	List<Map<String,Object>> giftCount(TLiveGivedGift liveGivedGift);
	
	/**
	 * 
	 * 方法描述：主播获得打赏统计 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-28下午3:15:10 <br/>
	 * @param liveGivedGift
	 * @return
	 */
	@DataSource(value="slave")
	List<Map<String,Object>> anchorCount(TLiveGivedGift liveGivedGift);
	
	
	/**
	 * 
	 * 方法描述：鸟豆打赏区间人数统计 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-28下午3:15:10 <br/>
	 * @param liveGivedGift
	 * @return
	 */
	@DataSource(value="slave")
	List<Map<String,Object>> birdBeanZoneCount(TLiveGivedGift liveGivedGift);
	
	/**
	 * 
	 * 方法描述：打赏时间统计 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-30下午3:54:24 <br/>
	 * @param liveGivedGift
	 * @return
	 */
	@DataSource(value="slave")
	Map<String,Object> timeZoneCount(TLiveGivedGift liveGivedGift);

	/**
	 * 方法描述：获取丢失列表
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月28日上午10:24:21 <br/>
	 * @param gift
	 * @return
	 */
	List<LoseGift> getLoseGifts(LoseGift gift);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月28日上午11:24:47 <br/>
	 * @param gift
	 * @return
	 */
	Long countLostGift(LoseGift gift);

	/**
	 * 方法描述：检查礼物是否丢失
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月31日上午9:22:52 <br/>
	 * @param list
	 * @return
	 */
	Long checkGiveIsLose(List<String> list);

	/**
	 * 方法描述：在此处添加方法描述 <br/>
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月31日上午9:32:07 <br/>
	 * @param list
	 * @return
	 */
	List<LoseGift> getLoseGiftsByIds(List<String> list);

	/**
	 * 方法描述：将记录修改为已接受
	 * 创建人： jianming <br/>
	 * 创建时间：2017年5月31日上午10:01:22 <br/>
	 * @param id
	 * @return
	 */
	int updateBackState(Integer id);
	
}