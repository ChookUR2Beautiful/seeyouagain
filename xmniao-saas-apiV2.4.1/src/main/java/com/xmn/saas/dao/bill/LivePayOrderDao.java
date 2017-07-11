package com.xmn.saas.dao.bill;

import org.apache.ibatis.annotations.Param;

import com.xmn.saas.entity.bill.LivePayOrder;
import com.xmn.saas.entity.bill.LivePayOrderWithBLOBs;

public interface LivePayOrderDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LivePayOrderWithBLOBs record);

    int insertSelective(LivePayOrderWithBLOBs record);

    LivePayOrderWithBLOBs selectByPrimaryKey(Integer id);
    
    
    LivePayOrderWithBLOBs selectByOrderNo(@Param("orderNo") String orderNo);

    int updateByPrimaryKeySelective(LivePayOrderWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(LivePayOrderWithBLOBs record);

    int updateByPrimaryKey(LivePayOrder record);
}