package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.entity.FreshLabel;
import com.xmniao.xmn.core.util.dataSource.DataSource;

import java.util.List;

public interface FreshLabelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FreshLabel record);

    int insertSelective(FreshLabel record);

    FreshLabel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreshLabel record);

    int updateByPrimaryKey(FreshLabel record);

    @DataSource("joint")
    List<FreshLabel> selectAliveLabel();
}