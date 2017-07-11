package com.xmn.saas.dao.micro;

import com.xmn.saas.entity.micro.MicroBill;


public interface MicroBillDao {
    int deleteByPrimaryKey(Integer id);

    int insert(MicroBill record);

    int insertSelective(MicroBill record);

    MicroBill selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MicroBill record);

    int updateByPrimaryKey(MicroBill record);
}