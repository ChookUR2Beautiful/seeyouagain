package com.xmniao.xmn.core.coupon.dao;

import com.xmniao.xmn.core.coupon.entity.CouponRelation;

public interface CouponRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CouponRelation record);

    int insertSelective(CouponRelation record);

    CouponRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponRelation record);

    int updateByPrimaryKey(CouponRelation record);
}