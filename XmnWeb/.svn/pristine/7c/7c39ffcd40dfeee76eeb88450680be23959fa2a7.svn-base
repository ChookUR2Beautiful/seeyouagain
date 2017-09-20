package com.xmniao.xmn.core.marketingmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.base.BaseService;
import com.xmniao.xmn.core.marketingmanagement.dao.ActivityPrizeDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityPrize;
/**
 * 
 * 项目名称：XmnWeb
 * 
 * 类名称：TActivityService
 * 
 * 类描述：营销活动管理
 * 
 * 创建人： yang'xu
 * 
 * 创建时间：2015年01月14日10时15分24秒
 * 
 * Copyright©广东寻蜜鸟网络技术有限公司
 */
@Service
public class ActivityPrizeService extends BaseService<TActivityPrize> {
	
	@Autowired
	public ActivityPrizeDao activityPrizeDao;

	@Override
	protected BaseDao getBaseDao() {
		return activityPrizeDao;
	}

	public List<Map<String, Object>> getStatistics(TActivityPrize activityPrize) {
		return activityPrizeDao.getStatistics(activityPrize);
	}
	
	public Long getStatisticsCount(TActivityPrize activityPrize){
		return activityPrizeDao.getStatisticsCount(activityPrize);
	}
	
}
