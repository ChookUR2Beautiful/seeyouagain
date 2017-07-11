package com.xmn.saas.dao.bill;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.bill.CouponDetail;
import com.xmn.saas.entity.bill.CouponDetailKey;

public interface CouponDetailDao {
    int deleteByPrimaryKey(CouponDetailKey key);

    int insert(CouponDetail record);

    int insertSelective(CouponDetail record);

    CouponDetail selectByPrimaryKey(CouponDetailKey key);
    
    CouponDetail selectBySerial(@Param("serial")String serial);

    int updateByPrimaryKeySelective(CouponDetail record);

    int updateByPrimaryKey(CouponDetail record);
}