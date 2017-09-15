package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.entity.activity.indiana.FreshActivityIndiana;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface FreshActivityIndianaDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FreshActivityIndiana record);

    int insertSelective(FreshActivityIndiana record);

    @DataSource("joint")
    FreshActivityIndiana selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreshActivityIndiana record);

    int updateByPrimaryKey(FreshActivityIndiana record);

    @DataSource("joint")
    ArrayList<FreshActivityIndiana> selectVaildActivity(@Param("page") Page page);

    @DataSource("joint")
    FreshActivityIndiana selectByBoutId(Integer boutId);
}