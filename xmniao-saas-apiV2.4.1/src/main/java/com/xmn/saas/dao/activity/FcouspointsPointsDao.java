package com.xmn.saas.dao.activity;

import com.xmn.saas.entity.activity.FcouspointsPoints;

public interface FcouspointsPointsDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FcouspointsPoints record);

    int insertSelective(FcouspointsPoints record);

    FcouspointsPoints selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FcouspointsPoints record);

    int updateByPrimaryKey(FcouspointsPoints record);
}