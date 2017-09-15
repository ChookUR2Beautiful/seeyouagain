package com.xmniao.xmn.core.market.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.market.entity.cart.SaleProperty;
import com.xmniao.xmn.core.util.dataSource.DataSource;

public interface SalePropertyDao {
    @DataSource( "joint" )
    int deleteByPrimaryKey(Integer id);

    @DataSource( "joint" )
    int insert(SaleProperty record);

    @DataSource( "joint" )
    int insertSelective(SaleProperty record);

    @DataSource( "joint" )
    SaleProperty selectByPrimaryKey(Integer id);

    @DataSource( "joint" )
    List<SaleProperty> selectByCodeId(@Param( "codeid" ) Long codeid);

    @DataSource( "joint" )
    int updateByPrimaryKeySelective(SaleProperty record);

    @DataSource( "joint" )
    int updateByPrimaryKey(SaleProperty record);
}
