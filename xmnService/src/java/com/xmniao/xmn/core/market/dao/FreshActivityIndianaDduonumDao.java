package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.entity.activity.indiana.FreshActivityIndianaDduonum;
import com.xmniao.xmn.core.market.entity.activity.indiana.IndianaRecord;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreshActivityIndianaDduonumDao {
    int deleteByPrimaryKey(Long id);

    int insert(FreshActivityIndianaDduonum record);

    @DataSource("joint")
    int insertSelective(FreshActivityIndianaDduonum record);

    FreshActivityIndianaDduonum selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FreshActivityIndianaDduonum record);

    int updateByPrimaryKey(FreshActivityIndianaDduonum record);

    @DataSource("joint")
    List<FreshActivityIndianaDduonum> selectRecentlyRecord(@Param("boutId") Long boutId, @Param("page") Page page);

    @DataSource("joint")
    List<IndianaRecord> selectByUser(@Param("uid") String uid, @Param("page") Page page);

    @DataSource("joint")
    List<String> selectIndianaNumbers(@Param("uid") String uid, @Param("boutId") Integer boutId);

    @DataSource("joint")
    Integer countUserBoutJoins(@Param("uid") String uid, @Param("boutId") Integer boutId);

    @DataSource("joint")
    int countRefund(@Param("uid") String uid, @Param("boutId") Integer boutId);
}