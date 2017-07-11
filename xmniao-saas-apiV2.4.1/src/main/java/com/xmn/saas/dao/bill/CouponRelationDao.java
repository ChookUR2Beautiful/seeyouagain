package com.xmn.saas.dao.bill;

import com.xmn.saas.entity.bill.CouponRelation;

public interface CouponRelationDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CouponRelation record);

    int insertSelective(CouponRelation record);

    CouponRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CouponRelation record);

    int updateByPrimaryKey(CouponRelation record);
}