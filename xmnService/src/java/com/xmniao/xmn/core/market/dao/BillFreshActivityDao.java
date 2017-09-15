package com.xmniao.xmn.core.market.dao;


import com.xmniao.xmn.core.market.base.Page;
import com.xmniao.xmn.core.market.entity.activity.BillFreshActivity;
import com.xmniao.xmn.core.order.entity.OrderExpress;
import com.xmniao.xmn.core.util.dataSource.DataSource;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface BillFreshActivityDao {
    @DataSource("joint")
    int deleteByPrimaryKey(Long id);

    @DataSource("joint")
    int insert(BillFreshActivity record);

    @DataSource("joint")
    int insertSelective(BillFreshActivity record);

    @DataSource("joint")
    BillFreshActivity selectByPrimaryKey(String id);

    @DataSource("joint")
    int updateByPrimaryKeySelective(BillFreshActivity record);

    @DataSource("joint")
    int updateByPrimaryKey(BillFreshActivity record);

    @DataSource("joint")
    List<BillFreshActivity> selectByUid(@Param("uid") String uid, @Param("activityType") Integer activityType, @Param("page") Page page);

    @DataSource("joint")
    BillFreshActivity selectByOrderIdAndUid(@Param("orderId") String orderId, @Param("uid") String uid);

    @DataSource("joint")
    int countUnSubmitAddressOrder(@Param("uid") String uid, @Param("orderType") Integer orderType);

    @DataSource("joint")
    int countUnliquidatedOrder(@Param("uid") String uid);

    @DataSource("joint")
    List<BillFreshActivity> selectUnliquidatedOrder(@Param("uid") String uid);

    @DataSource("joint")
    List<BillFreshActivity> selectUnSubmitAddressOrder(@Param("uid") String uid, @Param("orderType") Integer orderType);

    @DataSource("joint")
    BillFreshActivity selectByUidAndActivityId(@Param("boutId") Integer boutId, @Param("uid") String uid);

    @DataSource("joint")
    void updateStateByIdAndUserId(@Param("orderId") String orderId, @Param("uid") String uid, @Param("state") int state, @Param("oldState") int oldState);

    @DataSource("joint")
    List<BillFreshActivity> selectUnDialogInfo(@Param("orderType") Integer orderType, @Param("uid") String uid);

    @DataSource("joint")
    void updateDialogConfirm(@Param("orderId") String orderId, @Param("uid") String uid);

    @DataSource("joint")
    int countAliveOrder(@Param("uid") String uid);
    
    @DataSource("joint")
    public OrderExpress queryOrderExpressview(Map<Object, Object>  params);
}