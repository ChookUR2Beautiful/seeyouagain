package com.xmniao.xmn.core.market.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmniao.xmn.core.market.entity.cart.SaleGroup;
import com.xmniao.xmn.core.util.dataSource.DataSource;


public interface SaleGroupDao {
	
    @DataSource("joint")
    int deleteByPrimaryKey(Integer id);

    @DataSource("joint")
    int insert(SaleGroup record);

    @DataSource("joint")
    int insertSelective(SaleGroup record);

    @DataSource("joint")
    SaleGroup selectByPrimaryKey(Integer id);
    
    @DataSource("joint")
    SaleGroup selectByAttr(@Param("codeid") Integer codeid ,@Param("pvIds") String pvIds );
    
    @DataSource("joint")
    List<SaleGroup> selectByCodeId(@Param("codeid") Integer codeid );

    @DataSource("joint")
    int updateByPrimaryKeySelective(SaleGroup record);
    
    @DataSource("joint")
    int updateStock(@Param("codeid") Integer codeid ,@Param("attrIds") String attrIds ,@Param("num") Integer num);
    
    //处理异常库存
    @DataSource("joint")
    int updateExceptionStock(@Param("codeid") Integer codeid ,@Param("attrIds") String attrIds ,@Param("num") Integer num);

    @DataSource("joint")
    int updateByPrimaryKey(SaleGroup record);
    
    @DataSource("joint")
    int selectStockByCoodeIdAndPvIds(@Param("codeid") String codeid ,@Param("attrIds") String attrIds);

    @DataSource("joint")
    Integer sumProductStoreByCodeId(Long codeid);
}