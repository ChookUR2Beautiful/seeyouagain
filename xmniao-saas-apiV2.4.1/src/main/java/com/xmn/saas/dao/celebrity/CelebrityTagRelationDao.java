package com.xmn.saas.dao.celebrity;

import com.xmn.saas.entity.celebrity.CelebrityTagRelation;

public interface CelebrityTagRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(CelebrityTagRelation record);

    int insertSelective(CelebrityTagRelation record);

    CelebrityTagRelation selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CelebrityTagRelation record);

    int updateByPrimaryKey(CelebrityTagRelation record);
}