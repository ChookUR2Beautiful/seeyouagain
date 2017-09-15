package com.xmniao.xmn.core.market.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuctionRecord;
import com.xmniao.xmn.core.util.dataSource.DataSource;

public interface FreshActivityAuctionRecordDao {
    @DataSource( "joint" )
    int deleteByPrimaryKey(Integer id);

    @DataSource( "joint" )
    int insert(FreshActivityAuctionRecord record);

    @DataSource( "joint" )
    int insertSelective(FreshActivityAuctionRecord record);

    @DataSource( "joint" )
    FreshActivityAuctionRecord selectByPrimaryKey(Integer id);
    
    @DataSource( "joint" )
    FreshActivityAuctionRecord selectByUidAndId(@Param("activityId")Integer id,@Param("uid")Integer uid);
    
    @DataSource( "joint" )
    List<FreshActivityAuctionRecord> selectByPage(@Param( "uid" ) Integer uid, @Param( "pageOffset" ) Integer pageOffset,
            @Param( "pageSize" ) Integer pageSize);
    
    @DataSource( "joint" )
    int selectByPageCount(@Param( "uid" ) Integer uid);

    @DataSource( "joint" )
    int updateByPrimaryKeySelective(FreshActivityAuctionRecord record);

    @DataSource( "joint" )
    int updateByPrimaryKey(FreshActivityAuctionRecord record);
}
