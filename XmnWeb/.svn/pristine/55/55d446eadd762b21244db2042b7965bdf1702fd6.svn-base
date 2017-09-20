package com.xmniao.xmn.core.marketingmanagement.dao;


import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.marketingmanagement.entity.TActivityPrize;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

/**
* 
* @项目名称：XmnWeb
* 
* @类名称：TActivityDao
* 
* @类描述： 营销活动管理
* 
* @创建人：yang'xu
* 
* @创建时间：2015年01月14日10时12分24秒
* 
* @Copyright©广东寻蜜鸟网络技术有限公司
*/
public interface ActivityPrizeDao extends BaseDao<TActivityPrize> {
	
	
	/**
	 * 活动数据统计
	 * @param activityPrize
	 * @return
	 */
	@DataSource("slave")
	public List<Map<String, Object>> getStatistics(TActivityPrize activityPrize);
	
	/**
	 * 活动数据统计条数
	 * @param activityPrize
	 * @return
	 */
	@DataSource("slave")
	public Long getStatisticsCount(TActivityPrize activityPrize);

}
