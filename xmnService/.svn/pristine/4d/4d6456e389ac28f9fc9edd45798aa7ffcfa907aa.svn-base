package com.xmniao.xmn.core.market.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuctionBidding;
import com.xmniao.xmn.core.util.dataSource.DataSource;

public interface FreshActivityAuctionBiddingDao {
    @DataSource( "joint" )
    int deleteByPrimaryKey(Integer id);

    @DataSource( "joint" )
    int insert(FreshActivityAuctionBidding record);

    @DataSource( "joint" )
    int insertSelective(FreshActivityAuctionBidding record);
    
    @DataSource( "joint" )
    int selectCountByActivity(@Param( "activityId" ) Integer activityId);
    
    @DataSource( "joint" )
    BigDecimal selectMaxByActivity(@Param( "activityId" ) Integer activityId);
    
    @DataSource( "joint" )
    BigDecimal selectMaxByUid(@Param( "activityId" ) Integer activityId ,@Param( "uid" ) Integer uid);
    
    @DataSource( "joint" )
    List<FreshActivityAuctionBidding> selectListByActivity(@Param( "activityId" ) Integer activityId );

    @DataSource( "joint" )
    FreshActivityAuctionBidding selectByPrimaryKey(Integer id);

    @DataSource( "joint" )
    int updateByPrimaryKeySelective(FreshActivityAuctionBidding record);

    @DataSource( "joint" )
    int updateByPrimaryKey(FreshActivityAuctionBidding record);
}
