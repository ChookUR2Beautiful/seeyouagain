package com.xmniao.xmn.core.market.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.market.entity.cart.FreshActivityGroup;
import com.xmniao.xmn.core.util.dataSource.DataSource;

public interface FreshActivityGroupDao {
    @DataSource( "joint" )
    int deleteByPrimaryKey(Integer id);

    @DataSource( "joint" )
    int insert(FreshActivityGroup record);

    @DataSource( "joint" )
    int insertSelective(FreshActivityGroup record);

    @DataSource( "joint" )
    FreshActivityGroup selectByPrimaryKey(Integer id);

    @DataSource( "joint" )
    FreshActivityGroup selectByActivityIdAndCodeIdAndPvIds(@Param("activityId") Integer activityId ,@Param("codeid")Long codeid,@Param("pvIds")String pvIds);
    
    @DataSource( "joint" )
    List<FreshActivityGroup> selectByActivityIdAndCodeId(@Param("activityId") Integer activityId ,@Param("codeid")Long codeid);
    
    @DataSource( "joint" )
    List<FreshActivityGroup> selectByCodeId(@Param("codeid")Long codeid);

    @DataSource( "joint" )
    int updateByPrimaryKeySelective(FreshActivityGroup record);

    @DataSource( "joint" )
    int updateByPrimaryKey(FreshActivityGroup record);
    
    @DataSource( "joint" )
    FreshActivityGroup selectActivityStockByPvids(@Param("activityId") Integer activityId ,@Param("codeId")Long codeId,@Param("attrIds")String attrIds);
   
    @DataSource( "joint" )
    int updateActivityProductStock(@Param("activityId") Integer activityId ,@Param("codeId")Long codeId,@Param("attrIds")String attrIds,@Param("num")Integer num);
  
    //更新异常库存
    @DataSource( "joint" )
    int updateExceptionStock(@Param("activityId") Integer activityId ,@Param("codeId")Long codeId,@Param("attrIds")String attrIds,@Param("num")Integer num);

    @DataSource("joint")
    Integer sumProductSotreByFreshModule(@Param("codeid") Long codeid, @Param("activityId") Long activityId);
}
