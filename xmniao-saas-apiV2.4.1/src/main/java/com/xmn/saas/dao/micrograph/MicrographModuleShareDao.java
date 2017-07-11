package com.xmn.saas.dao.micrograph;

import com.xmn.saas.entity.micrograph.MicrographModuleShare;

public interface MicrographModuleShareDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MicrographModuleShare record);

    int insertSelective(MicrographModuleShare record);

    MicrographModuleShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MicrographModuleShare record);

    int updateByPrimaryKey(MicrographModuleShare record);
}