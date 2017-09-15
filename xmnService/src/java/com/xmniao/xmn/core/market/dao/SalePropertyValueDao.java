package com.xmniao.xmn.core.market.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.market.entity.cart.SalePropertyValue;
import com.xmniao.xmn.core.util.dataSource.DataSource;

public interface SalePropertyValueDao {
    @DataSource( "joint" )
    int deleteByPrimaryKey(Integer id);

    @DataSource( "joint" )
    int insert(SalePropertyValue record);

    @DataSource( "joint" )
    int insertSelective(SalePropertyValue record);

    @DataSource( "joint" )
    SalePropertyValue selectByPrimaryKey(Integer id);

    @DataSource( "joint" )
    List<SalePropertyValue> selectByPropertyId(@Param( "propertyId" ) Integer propertyId);

    @DataSource( "joint" )
    int updateByPrimaryKeySelective(SalePropertyValue record);

    @DataSource( "joint" )
    int updateByPrimaryKey(SalePropertyValue record);
}
