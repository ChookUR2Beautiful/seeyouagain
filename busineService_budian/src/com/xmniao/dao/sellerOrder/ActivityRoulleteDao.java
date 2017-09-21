package com.xmniao.dao.sellerOrder;

import java.util.List;
import java.util.Map;

import com.xmniao.domain.coupon.ActivityRoullete;

public interface ActivityRoulleteDao {

    List<ActivityRoullete> getActivityRoulleteList(ActivityRoullete activityRoullete);
    
    /**
     * 
     * 方法描述：查询视图t_activity_view 中商家活动状态有变化的商家
     * 创建人： ChenBo
     * 创建时间：2016年12月22日
     * @return List<Map<String,Object>>
     */
    List<Integer> getSellerActivityViewLst(Map<String,Object> activityMap);
}