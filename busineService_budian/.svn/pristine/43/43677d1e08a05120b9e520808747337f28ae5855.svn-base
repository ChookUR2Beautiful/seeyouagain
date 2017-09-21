package com.xmniao.dao.manor;

import com.xmniao.domain.manor.ManorCouponConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ManorCouponConfigMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManorCouponConfig record);

    int insertSelective(ManorCouponConfig record);

    ManorCouponConfig selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManorCouponConfig record);

    int updateByPrimaryKey(ManorCouponConfig record);

    List<ManorCouponConfig> selectByType(@Param("type") Integer type);
}