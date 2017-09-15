package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.entity.home.FreshModule;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreshModuleDao {
    @DataSource("joint")
    int deleteByPrimaryKey(Long id);

    int insert(FreshModule record);

    int insertSelective(FreshModule record);

    @DataSource("joint")
    FreshModule selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FreshModule record);

    int updateByPrimaryKey(FreshModule record);

    @DataSource("joint")
    List<FreshModule> selectModuleListByModelTypeAndTypeId(@Param("moduleType") Integer moduleType, @Param("typeId") Long typeId);

    @DataSource("joint")
    List<FreshModule> selectActivityModule();

    @DataSource("joint")
    FreshModule selectModuleByModelTypeAndTypeId(Integer moduleType, Long typeId);
}