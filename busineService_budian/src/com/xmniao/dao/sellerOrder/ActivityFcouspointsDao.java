package com.xmniao.dao.sellerOrder;

import java.util.List;

import com.xmniao.domain.coupon.ActivityFcouspoints;
import com.xmniao.domain.coupon.ActivityFcouspointsPoints;
import com.xmniao.domain.coupon.ActivityFcouspointsRecord;

/**
 * 
 * 
 * 项目名称：busineService
 * 
 * 类名称：ActivityFcouspointsDao
 * 
 * 类描述： 商家聚点活动
 * 
 * 创建人： Chen Bo
 * 
 * 创建时间：2016年12月8日 下午2:21:35 
 * 
 * Copyright (c) 广东寻蜜鸟网络技术有限公司
 */
public interface ActivityFcouspointsDao {

	/**
	 * 获取商家的聚点活动
	 * @Title: getActivityFcouspointsList 
	 * @Description:
	 */
    List<ActivityFcouspoints> getActivityFcouspointsList(ActivityFcouspoints activityFcouspoints);

    /**
     * 更新商家聚点活动
     * @Title: updateActivityFcouspoints 
     * @Description:
     */
    int updateActivityFcouspoints(ActivityFcouspoints record);
    
    /**
     * 添加聚点活动参与记录
     * @Title: insertActivityFcouspointsRecord 
     * @Description:
     */
    int insertActivityFcouspointsRecord(ActivityFcouspointsRecord record);
    
    /**
     * 统计聚点活动参与记录
     * @Title: insertActivityFcouspointsRecord 
     * @Description:
     */
    long countActivityFcouspointsRecord(ActivityFcouspointsRecord record);

    /**
     * 更新聚点活动
     * @Title: updateActivityFcouspointsPoints 
     * @Description:
     */
    int updateActivityFcouspointsPoints(ActivityFcouspointsPoints record);
    
    /**
     * 插入聚点活动
     * @Title: updateActivityFcouspointsPoints 
     * @Description:
     */
    int insertActivityFcouspointsPoints(ActivityFcouspointsPoints record);
    
    /**
     * 插入聚点活动
     * @Title: updateActivityFcouspointsPoints 
     * @Description:
     */
    ActivityFcouspointsPoints getActivityFcouspointsPoints(ActivityFcouspointsPoints record);
}