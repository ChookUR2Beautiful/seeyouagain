package com.xmniao.xmn.core.market.dao;

import com.xmniao.xmn.core.market.entity.home.FreshImage;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FreshImageDao {
    int deleteByPrimaryKey(Long id);

    int insert(FreshImage record);

    int insertSelective(FreshImage record);

    FreshImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(FreshImage record);

    int updateByPrimaryKey(FreshImage record);

    @DataSource("joint")
    List<FreshImage> selectByType(@Param("typeId") Integer typeId, @Param("type") Integer freshImageTypeBanner);
}