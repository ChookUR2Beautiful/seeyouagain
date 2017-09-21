package com.xmniao.dao.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.domain.order.FreshActivityAuctionRecord;

public interface FreshActivityAuctionRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(FreshActivityAuctionRecord record);

    FreshActivityAuctionRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreshActivityAuctionRecord record);

    
    //********************自定义区域**************************
    FreshActivityAuctionRecord getMaxPriceRecordByActivityId(Integer activityId);
    
    int updateAuctionRecordState(@Param("id")Integer id, @Param("activityId")int activityId, @Param("state")Integer state);
    
    List<FreshActivityAuctionRecord> getFaultRecordByActivityId(@Param("id")Integer id, @Param("activityId")int activityId);
    
    FreshActivityAuctionRecord selectByActivityIdAndOrderId(@Param("activityId")int activityId, @Param("finishOrder")String finishOrder);
    
}