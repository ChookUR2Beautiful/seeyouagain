package com.xmniao.dao.order;

import java.util.List;

import com.xmniao.domain.order.FreshActivityAuction;

public interface FreshActivityAuctionDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FreshActivityAuction record);

    int insertSelective(FreshActivityAuction record);

    FreshActivityAuction selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreshActivityAuction record);

    
    List<FreshActivityAuction> selectEndActvityGroup();
    
    /**
     * 未开始的任务
     * @return
     */
    List<FreshActivityAuction> selectStartActvityGroup();
    
    List<FreshActivityAuction> selectPayFaultActvityGroup();
    
    List<FreshActivityAuction> selectHandEndActvityGroup();
    
    int getCount(Integer id);
}