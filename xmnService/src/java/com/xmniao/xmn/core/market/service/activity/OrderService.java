package com.xmniao.xmn.core.market.service.activity;

import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.entity.activity.BillFreshActivity;
import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;

import java.util.List;

/**
 * Created by yang.qiang on 2017/2/27.
 */
public interface OrderService {
    List<BillFreshActivity> getOrderList(String uid, Integer activityType, Page page);

    BillFreshActivity getDetail(String orderId, String uid);

    void saveAddress(String uid, BillFreshActivity order);

    int countUnliquidatedOrder(String uid);

    int countUnSubmitAddressOrder(String uid, Integer orderType);

    List<BillFreshActivity> getUnliquidatedOrders(String uid);

    List<BillFreshActivity> getUnSubmitAddressOrders(String uid, Integer orderType);

    ReceivingAddress queryDefaultAddress(String uid);

    void confirm(String orderId, String uid);

    String remind(String orderId);

    List<BillFreshActivity> dialogInfo(Integer orderType, String uid);

    void dialogConfirm(String orderId, String uid);

    int countAliveOrder(String uid);
}
