package com.xmniao.xmn.core.reward_dividends.dao;

import com.xmniao.xmn.core.reward_dividends.entity.TReceivingAddress;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TReceivingAddressDao {
    @DataSource(value="slave")
    int deleteByPrimaryKey(Integer id);

    @DataSource(value="slave")
    int insert(TReceivingAddress record);

    @DataSource(value="slave")
    int insertSelective(TReceivingAddress record);

    @DataSource(value="slave")
    TReceivingAddress selectByPrimaryKey(Integer id);

    @DataSource(value="slave")
    int updateByPrimaryKeySelective(TReceivingAddress record);

    @DataSource(value="slave")
    int updateByPrimaryKey(TReceivingAddress record);
}