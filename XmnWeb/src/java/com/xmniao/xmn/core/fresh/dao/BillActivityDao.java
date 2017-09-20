package com.xmniao.xmn.core.fresh.dao;

import com.xmniao.xmn.core.fresh.entity.BillActivity;

public interface BillActivityDao {
    int deleteByPrimaryKey(Long id);

    int insert(BillActivity record);

    int insertSelective(BillActivity record);

    BillActivity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillActivity record);

    int updateByPrimaryKey(BillActivity record);
}