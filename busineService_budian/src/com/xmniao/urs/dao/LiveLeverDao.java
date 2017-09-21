package com.xmniao.urs.dao;

import java.util.List;

import com.xmniao.domain.live.LiveLever;

public interface LiveLeverDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LiveLever record);

    int insertSelective(LiveLever record);

    LiveLever selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LiveLever record);

    int updateByPrimaryKey(LiveLever record);

	List<LiveLever> getList();
}