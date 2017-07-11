package com.xmn.saas.dao.bill;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.bill.SellerPackageOrder;

public interface SellerPackageOrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerPackageOrder record);

    int insertSelective(SellerPackageOrder record);

    SellerPackageOrder selectByPrimaryKey(Integer id);
    
    SellerPackageOrder selectByOrderNo(@Param("orderNo")String orderNo);

    int updateByPrimaryKeySelective(SellerPackageOrder record);

    int updateByPrimaryKeyWithBLOBs(SellerPackageOrder record);

    int updateByPrimaryKey(SellerPackageOrder record);
}