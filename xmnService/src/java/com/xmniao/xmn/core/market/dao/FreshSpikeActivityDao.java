package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.entity.activity.spike.FreshSpikeActivity;
import com.xmniao.xmn.core.util.dataSource.DataSource;

public interface FreshSpikeActivityDao {
    @DataSource("joint")
    int deleteByPrimaryKey(Long id);

    @DataSource("joint")
    int insert(FreshSpikeActivity record);

    @DataSource("joint")
    int insertSelective(FreshSpikeActivity record);

    @DataSource("joint")
    FreshSpikeActivity selectByPrimaryKey(Long spikeId);

    @DataSource("joint")
    int updateByPrimaryKeySelective(FreshSpikeActivity record);

    @DataSource("joint")
    int updateByPrimaryKey(FreshSpikeActivity record);
}