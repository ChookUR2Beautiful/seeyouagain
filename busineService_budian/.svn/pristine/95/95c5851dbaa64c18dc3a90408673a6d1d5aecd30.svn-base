package com.xmniao.dao.coupon;

import com.xmniao.domain.coupon.TCoupon;
import com.xmniao.domain.coupon.TCouponWithBLOBs;

public interface TCouponMapper {
    int deleteByPrimaryKey(Integer cid);

    int insert(TCouponWithBLOBs record);

    int insertSelective(TCouponWithBLOBs record);

    TCouponWithBLOBs selectByPrimaryKey(Integer cid);

    int updateByPrimaryKeySelective(TCouponWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TCouponWithBLOBs record);

    int updateByPrimaryKey(TCoupon record);

    TCoupon selectByCid(Integer cid);
}