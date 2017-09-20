package com.xmniao.xmn.core.reward_dividends.dao;

import com.xmniao.xmn.core.base.Page;
import com.xmniao.xmn.core.reward_dividends.controller.vo.RechargeOrderListRequest;
import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationGoodsOrder;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TVerExcitationGoodsOrderDao {
    @DataSource(value = "slave")
    int deleteByPrimaryKey(Integer id);

    @DataSource(value = "slave")
    int insert(TVerExcitationGoodsOrder record);

    @DataSource(value = "slave")
    int insertSelective(TVerExcitationGoodsOrder record);

    @DataSource(value = "slave")
    TVerExcitationGoodsOrder selectByPrimaryKey(Integer id);

    @DataSource(value = "slave")
    int updateByPrimaryKeySelective(TVerExcitationGoodsOrder record);

    @DataSource(value = "slave")
    int updateByPrimaryKey(TVerExcitationGoodsOrder record);

    @DataSource(value = "slave")
    long countOrders(@Param("param") RechargeOrderListRequest param);

    @DataSource(value = "slave")
    List<TVerExcitationGoodsOrder> selectOrder(@Param("param") RechargeOrderListRequest param, @Param("page") Page page);
}