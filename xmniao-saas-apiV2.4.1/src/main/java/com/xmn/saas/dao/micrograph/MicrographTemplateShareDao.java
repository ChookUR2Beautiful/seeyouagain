package com.xmn.saas.dao.micrograph;

import com.xmn.saas.entity.micrograph.MicrographTemplateShare;

public interface MicrographTemplateShareDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MicrographTemplateShare record);

    int insertSelective(MicrographTemplateShare record);

    MicrographTemplateShare selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MicrographTemplateShare record);

    int updateByPrimaryKey(MicrographTemplateShare record);
}