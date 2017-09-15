package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.entity.home.FreshType;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreshTypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FreshType record);

    int insertSelective(FreshType record);

    FreshType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreshType record);

    int updateByPrimaryKey(FreshType record);

    @DataSource("joint")
    List<FreshType> selectByType(@Param("typeId") Integer typeId);
}