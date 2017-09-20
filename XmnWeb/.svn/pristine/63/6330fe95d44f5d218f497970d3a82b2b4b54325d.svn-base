/**
 * 
 */
package com.xmniao.xmn.core.live_anchor.quartz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.xmniao.xmn.core.live_anchor.constant.LiveConstant;
import com.xmniao.xmn.core.live_anchor.dao.BLiverDao;
import com.xmniao.xmn.core.live_anchor.dao.TLiveReferrerCensusDao;
import com.xmniao.xmn.core.live_anchor.entity.BLiver;
import com.xmniao.xmn.core.live_anchor.entity.TLiveReferrerCensus;
import com.xmniao.xmn.core.util.DateUtil;

/**
 * 
 * 项目名称：XmnWeb_LIVE_170105
 * 
 * 类名称：TLiveReferrerQuartzService
 * 
 * 类描述： 直播企业推荐人统计下线数据QuartzService
 * 
 * 创建人：  huang'tao
 * 
 * 创建时间：2016-12-31 下午3:53:33 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司 
 */
public class TLiveReferrerQuartzService {
	
	
	/**
	 * 注入直播企业推荐人下线统计服务
	 */
	@Autowired
	private TLiveReferrerCensusDao  referrerCensusDao;
	
	/**
	 * 注入直播用户服务
	 */
	@Autowired
	private BLiverDao liverDao;
	
	/**
	 * 
	 * 方法描述：按天统计企业推荐人下线统计数据 <br/>
	 * 创建人：  huang'tao <br/>
	 * 创建时间：2016-12-31下午3:59:07 <br/>
	 * @throws Exception 
	 */
	public void liveReferrerCensusByDay() throws Exception{
		long start = System.currentTimeMillis();
		List<TLiveReferrerCensus> censusList= new ArrayList<TLiveReferrerCensus>();
		BLiver liver=new BLiver();
		liver.setReferrerType(LiveConstant.REFERRER_TYPE.ENTERPRISE);
		List<BLiver> liverList = liverDao.getList(liver);
		Map<String,Object> parameterMap=new HashMap<String,Object>();
		String countDate = DateUtil.getSpecifiedDate(DateUtil.YESTERDAY);
		Date censusDate = DateUtil.formatStringToDate(countDate, DateUtil.Y_M_D);
		String nextDate = DateUtil.getNow(DateUtil.Y_M_D);
		BigDecimal zero=new BigDecimal(0);
		for(BLiver liverBean:liverList){
			Integer uid = liverBean.getUid();
			parameterMap.put("uid", uid);
			parameterMap.put("countDate", countDate);
			parameterMap.put("nextDate", nextDate);
			Map<String, Object> juniorRechargeCount = referrerCensusDao.juniorRechargeCount(parameterMap);
			Map<String, Object> juniorRewardCount = referrerCensusDao.juniorRewardCount(parameterMap);
			Map<String, Object> juniorConsumeCount = referrerCensusDao.juniorConsumeCount(parameterMap);
			BigDecimal recharge = (BigDecimal) juniorRechargeCount.get("recharge");
			BigDecimal reward = (BigDecimal) juniorRewardCount.get("reward");
			BigDecimal consume = (BigDecimal) juniorConsumeCount.get("consume");
			TLiveReferrerCensus census=new TLiveReferrerCensus();
			if(recharge.compareTo(zero)==0 && reward.compareTo(zero)==0 && consume.compareTo(zero)==0 ){
				continue;
			}

			census.setEnterpriseUid(new Long(uid));
			census.setJuniorRecharge(recharge);
			census.setJuniorReward(reward);
			census.setJuniorConsume(consume);
			census.setCensusDate(censusDate);
			census.setCreateTime(new Date());
			censusList.add(census);
		}
		
		if(censusList!=null && censusList.size()>0){
			referrerCensusDao.addBatch(censusList);
		}
		
		long end = System.currentTimeMillis();
		System.out.println("TLiveReferrerQuartzService===>liveReferrerCensusByDay方法执行结束,耗时:"+(end-start));
	}
	
}
