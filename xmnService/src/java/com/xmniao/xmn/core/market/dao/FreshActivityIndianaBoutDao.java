package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.entity.activity.indiana.FreshActivityIndianaBout;
import com.xmniao.xmn.core.market.entity.activity.indiana.IndianaRecord;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreshActivityIndianaBoutDao {
    int deleteByPrimaryKey(Integer id);

    int insert(FreshActivityIndianaBout record);

    int insertSelective(FreshActivityIndianaBout record);

    @DataSource("joint")
    FreshActivityIndianaBout selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FreshActivityIndianaBout record);

    int updateByPrimaryKey(FreshActivityIndianaBout record);

    @DataSource("joint")
    FreshActivityIndianaBout selectByCurrentBout(@Param("activityId") Integer activityId);

    @DataSource("joint")
    void updatePoint(@Param("boutId") Integer boutId, @Param("point") Integer point);

    @DataSource("joint")
    IndianaRecord selectIndianaInfoByBout(@Param("boutId") Integer boutId);

    @DataSource("joint")
    List<FreshActivityIndianaBout> selectAliveBout();

    @DataSource("joint")
    List<FreshActivityIndianaBout> selectSaleoutBout();

    @DataSource("joint")
    void updateIndianaAndOldBout(@Param("bout") FreshActivityIndianaBout bout);
}