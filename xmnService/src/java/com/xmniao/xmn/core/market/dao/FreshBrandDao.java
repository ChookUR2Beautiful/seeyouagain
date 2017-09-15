package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.entity.home.FreshBrand;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreshBrandDao {
    @DataSource("joint")
    int deleteByPrimaryKey(Integer id);

    int insert(FreshBrand record);

    int insertSelective(FreshBrand record);

    @DataSource("joint")
    FreshBrand selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreshBrand record);

    int updateByPrimaryKey(FreshBrand record);

    @DataSource("joint")
    List<FreshBrand> selectByTypeId(@Param("typeId") Integer typeId);
}