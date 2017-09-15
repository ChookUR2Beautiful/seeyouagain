package com.xmniao.xmn.core.market.service.activity.impl;

import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.dao.BillFreshActivityDao;
import com.xmniao.xmn.core.market.entity.activity.BillFreshActivity;
import com.xmniao.xmn.core.market.entity.pay.ReceivingAddress;
import com.xmniao.xmn.core.market.exception.CustomException;
import com.xmniao.xmn.core.market.service.activity.OrderService;
import com.xmniao.xmn.core.personal.dao.ReceivingAddressDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by yang.qiang on 2017/2/27.
 */
@Service
public class OrderServiceImpl implements OrderService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private BillFreshActivityDao billFreshActivityDao;

    @Autowired
    private String fileUrl;
    @Autowired
    private ReceivingAddressDao receivingAddressDao;

    /**
     * 获取活动订单列表
     * @return
     * @param uid
     * @param activityType
     * @param page
     */
    @Override
    public List<BillFreshActivity> getOrderList(String uid, Integer activityType, Page page) {
        List<BillFreshActivity> activityList = billFreshActivityDao.selectByUid(uid,activityType,page);
        for (BillFreshActivity order : activityList) {
            order.setProductBreviary(fileUrl + order.getProductBreviary());
        }
        return activityList;
    }

    /**
     * 获取订单详情
     * @param orderId
     * @param uid
     * @return
     */
    @Override
    public BillFreshActivity getDetail(String orderId, String uid) {
        BillFreshActivity activity = billFreshActivityDao.selectByOrderIdAndUid(orderId, uid);
        activity.setProductBreviary(fileUrl + activity.getProductBreviary());
        return activity;
    }

    /**
     * 保存订单的收货信息
     * @param uid
     * @param order
     */
    @Override
    public void saveAddress(String uid, BillFreshActivity order) {
        BillFreshActivity activity = billFreshActivityDao.selectByPrimaryKey(order.getId());
        if ( activity==null || (!activity.getId().equals(order.getId()) )) {
            throw new CustomException("订单号错误");
        }
        order.setReceivingConfirm(2);
        billFreshActivityDao.updateByPrimaryKeySelective(order);
    }

    /**
     * 统计未结束的夺宝订单
     * @param uid
     * @return
     */
    @Override
    public int countUnliquidatedOrder(String uid) {
        return billFreshActivityDao.countUnliquidatedOrder(uid);
    }


    /**
     * 统计未提交收货信息的接口
     * @param uid
     * @param orderType
     * @return
     */
    @Override
    public int countUnSubmitAddressOrder(String uid, Integer orderType) {
        return billFreshActivityDao.countUnSubmitAddressOrder(uid,orderType);
    }

    @Override
    public List<BillFreshActivity> getUnliquidatedOrders(String uid) {
        List<BillFreshActivity> activityList = billFreshActivityDao.selectUnliquidatedOrder(uid);
        for (BillFreshActivity activity : activityList) {
            activity.setProductBreviary(fileUrl + activity.getProductBreviary());
        }
        return activityList;
    }

    @Override
    public List<BillFreshActivity> getUnSubmitAddressOrders(String uid, Integer orderType) {
        List<BillFreshActivity> activityList = billFreshActivityDao.selectUnSubmitAddressOrder(uid, orderType);
        for (BillFreshActivity activity : activityList) {
            activity.setProductBreviary(fileUrl + activity.getProductBreviary());
        }
        return activityList;
    }

    @Override
    public ReceivingAddress queryDefaultAddress(String uid) {
        return receivingAddressDao.selectUserDefaultAddress(uid);
    }


    /**
     * 确认收货
     * @return
     * @param orderId
     * @param uid
     */
    @Override
    public void confirm(String orderId, String uid) {
        billFreshActivityDao.updateStateByIdAndUserId(orderId,uid,4,3);
    }

    /**
     * 提醒发货
     * @return
     * @param orderId
     */
    @Override
    public String remind(String orderId) {
        BillFreshActivity record = new BillFreshActivity();
        record.setId(orderId);
        record.setLogisticsRemind(1);
        int i = billFreshActivityDao.updateByPrimaryKeySelective(record);
        if (i >= 0){
            return "提醒成功!";
        }else {
            return "已经提醒发货!";
        }
    }

    /**
     * 查询
     * @param orderType
     * @param uid
     * @return
     */
    @Override
    public List<BillFreshActivity> dialogInfo(Integer orderType, String uid) {
        List<BillFreshActivity> activitys = billFreshActivityDao.selectUnDialogInfo(orderType, uid);
        for (BillFreshActivity activity : activitys) {
            activity.setProductBreviary(fileUrl + activity.getProductBreviary());
        }
        return activitys;
    }

    @Override
    public void dialogConfirm(String orderId, String uid) {
        billFreshActivityDao.updateDialogConfirm(orderId,uid);
    }

    /**
     * 统计用户未完成订单
     * @param uid
     * @return
     */
    @Override
    public int countAliveOrder(String uid) {
        return billFreshActivityDao.countAliveOrder(uid);
    }
}
