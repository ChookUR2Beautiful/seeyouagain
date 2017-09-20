package com.xmniao.xmn.core.fresh.dao;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.fresh.entity.Indiana;

public interface IndianaDao extends BaseDao<Indiana>{
    int deleteByPrimaryKey(Integer id);

    int insert(Indiana record);

    int insertSelective(Indiana record);

    Indiana selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Indiana record);

    int updateByPrimaryKey(Indiana record);
}