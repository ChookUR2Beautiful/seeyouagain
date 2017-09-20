package com.xmniao.xmn.core.reward_dividends.service;

import com.xmniao.xmn.core.base.Page;
import com.xmniao.xmn.core.reward_dividends.controller.vo.RechargeOrderListRequest;
import com.xmniao.xmn.core.reward_dividends.dao.TReceivingAddressDao;
import com.xmniao.xmn.core.reward_dividends.dao.TVerExcitationGoodsOrderDao;
import com.xmniao.xmn.core.reward_dividends.entity.TReceivingAddress;
import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationGoodsOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang.qiang on 2017/5/31.
 */
@Service
public class RechargeOrderService {
    @Autowired
    private TVerExcitationGoodsOrderDao orderMapper;
    @Autowired
    private TReceivingAddressDao addressMapper;


    /**
     * 统计订单数量
     * @param param
     * @return
     */
    public long countOrders(RechargeOrderListRequest param) {
        return orderMapper.countOrders(param);
    }

    /**
     * 查询订单列表
     * @param param
     * @param page
     * @return
     */
    public List<TVerExcitationGoodsOrder> queryOrders(RechargeOrderListRequest param, Page page) {
        return orderMapper.selectOrder(param,page);
    }

    /**
     * 查询地址
     * @param rid
     * @return
     */
    public TReceivingAddress queryAddress(Integer rid) {
        return addressMapper.selectByPrimaryKey(rid);
    }

    /**
     * 更新状态码
     * @param status
     * @param orderId
     */
    public int updateStatus(Integer status, Integer orderId) {
        TVerExcitationGoodsOrder record = new TVerExcitationGoodsOrder();
        record.setId(orderId);
        record.setStatus(status.byteValue());
        return orderMapper.updateByPrimaryKeySelective(record);
    }
}
