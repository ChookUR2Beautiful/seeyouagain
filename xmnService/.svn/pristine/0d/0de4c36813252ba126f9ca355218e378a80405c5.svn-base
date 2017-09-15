package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.entity.activity.indiana.FreshIndianaRobot;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface FreshIndianaRobotDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FreshIndianaRobot record);

    int insertSelective(FreshIndianaRobot record);

    FreshIndianaRobot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreshIndianaRobot record);

    int updateByPrimaryKey(FreshIndianaRobot record);

    @DataSource("joint")
    Integer queryUidById(@Param("winnerUid") Integer winnerUid);

    @DataSource("joint")
    Map<Object,Object> selectById(@Param("uid") Integer anchorId);
}