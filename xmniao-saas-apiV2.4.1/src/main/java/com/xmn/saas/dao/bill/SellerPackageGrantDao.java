package com.xmn.saas.dao.bill;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.bill.SellerPackageGrant;

public interface SellerPackageGrantDao {
    int deleteByPrimaryKey(Integer id);

    int insert(SellerPackageGrant record);

    int insertSelective(SellerPackageGrant record);

    SellerPackageGrant selectByPrimaryKey(Integer id);
    
    SellerPackageGrant selectBySerial(@Param("serial")String serial,@Param("sellerid")Integer sellerid);

    int updateByPrimaryKeySelective(SellerPackageGrant record);

    int updateByPrimaryKey(SellerPackageGrant record);
}