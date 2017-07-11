package com.xmn.saas.dao.micrograph;

import com.xmn.saas.entity.micrograph.MicrographPageShare;

public interface MicrographPageShareDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MicrographPageShare record);

    int insertSelective(MicrographPageShare record);

    MicrographPageShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MicrographPageShare record);

    int updateByPrimaryKey(MicrographPageShare record);
}