package com.xmniao.dao.coupon;

import com.xmniao.domain.coupon.TCouponDetail;
import com.xmniao.domain.coupon.TCouponDetailKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TCouponDetailMapper {
    int deleteByPrimaryKey(TCouponDetailKey key);

    int insert(TCouponDetail record);

    int insertSelective(TCouponDetail record);

    TCouponDetail selectByPrimaryKey(TCouponDetailKey key);

    int updateByPrimaryKeySelective(TCouponDetail record);

    int updateByPrimaryKey(TCouponDetail record);

    void insertBatch(@Param("couponDetails") List<TCouponDetail> couponDetails);
}