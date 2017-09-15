package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.entity.home.FreshHotBrand;
import com.xmniao.xmn.core.util.dataSource.DataSource;

import java.util.List;

public interface FreshHotBrandDao {
    int deleteByPrimaryKey(Long id);

    int insert(FreshHotBrand record);

    int insertSelective(FreshHotBrand record);

    FreshHotBrand selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FreshHotBrand record);

    int updateByPrimaryKey(FreshHotBrand record);

    @DataSource("joint")
    List<FreshHotBrand> selectByTypeId(Integer typeId);
}