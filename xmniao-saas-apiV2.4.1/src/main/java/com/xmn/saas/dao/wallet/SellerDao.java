package com.xmn.saas.dao.wallet;

import com.xmn.saas.entity.wallet.Seller;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SellerDao {
    int deleteByPrimaryKey(Integer sellerid);

    int insert(Seller record);

    int insertSelective(Seller record);

    Seller selectByPrimaryKey(Integer sellerid);
    
    Seller findBySellerid(Integer sellerid);

    int updateByPrimaryKeySelective(Seller record);

    int updateByPrimaryKey(Seller record);

    List<Seller> selectByAccount(@Param("account") String account);
}