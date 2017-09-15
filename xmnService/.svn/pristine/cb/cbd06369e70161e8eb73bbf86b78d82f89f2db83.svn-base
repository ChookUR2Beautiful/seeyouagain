package com.xmniao.xmn.core.market.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.market.entity.activity.FreshActivityAuction;
import com.xmniao.xmn.core.util.dataSource.DataSource;

public interface FreshActivityAuctionDao {
    @DataSource( "joint" )
    int deleteByPrimaryKey(Integer id);

    @DataSource( "joint" )
    int insert(FreshActivityAuction record);

    @DataSource( "joint" )
    int insertSelective(FreshActivityAuction record);

    @DataSource( "joint" )
    FreshActivityAuction selectByPrimaryKey(Integer id);

    @DataSource( "joint" )
    int updateByPrimaryKeySelective(FreshActivityAuction record);

    @DataSource( "joint" )
    int updateByPrimaryKey(FreshActivityAuction record);

    @DataSource( "joint" )
    List<FreshActivityAuction> findByPage(@Param( "pageOffset" ) Integer pageOffset,
            @Param( "pageSize" ) Integer pageSize);

    @DataSource( "joint" )
    int findByPageCount();
}
