package com.xmn.saas.dao.wallet;

import com.xmn.saas.entity.wallet.Orderinvoice;

public interface OrderinvoiceDao {
    int deleteByPrimaryKey(Long id);

    int insert(Orderinvoice record);

    boolean insertSelective(Orderinvoice record);

    Orderinvoice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Orderinvoice record);

    int updateByPrimaryKey(Orderinvoice record);
}