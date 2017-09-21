package com.xmniao.dao.sellerOrder;

import java.util.List;
import java.util.Map;

import com.xmniao.domain.coupon.ActivityFullreduction;
import com.xmniao.domain.coupon.ActivityFullreductionRecord;

public interface ActivityFullreductionDao {

	/**
	 * 获取
	 */
    ActivityFullreduction getFullreductionInfo(Integer id);

    /**
     * 更新
     * @Title: updateFullreduction 
     * @Description:
     */
    int sendFullreduction(Map<String,Object> uMap);
    
    /**
     * 插入记录
     * @Title: insertFullreductionRecord 
     * @Description:
     */
    int insertFullreductionRecord(ActivityFullreductionRecord record);
    
    /**
     * 获取列表
     * @Title: getFullreductionRecord 
     * @Description:
     */
    List<ActivityFullreductionRecord> getFullreductionRecord(ActivityFullreductionRecord record);
}