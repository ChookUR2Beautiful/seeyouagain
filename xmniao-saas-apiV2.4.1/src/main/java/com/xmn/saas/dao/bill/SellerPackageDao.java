package com.xmn.saas.dao.bill;

import com.xmn.saas.entity.bill.SellerPackage;
import com.xmn.saas.entity.bill.SellerPackageWithBLOBs;

public interface SellerPackageDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerPackageWithBLOBs record);

    int insertSelective(SellerPackageWithBLOBs record);

    SellerPackageWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SellerPackageWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(SellerPackageWithBLOBs record);

    int updateByPrimaryKey(SellerPackage record);
}