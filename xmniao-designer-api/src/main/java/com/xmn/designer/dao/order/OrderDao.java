package com.xmn.designer.dao.order;


import com.xmn.designer.base.Page;
import com.xmn.designer.entity.order.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     *
     * 方法描述：根据订单号查询订单
     * 创建人：jianming
     * 创建时间：2016年11月16日 下午6:01:20
     * @param orderNum
     * @param outId
     * @return
     */
	Order selectByOrderNum(@Param("orderNum")String orderNum,@Param("uid")Long uid);

    List<Order> selectBySelective(@Param("order")Order order, @Param("page") Page page);

    Order selectByOrderNoAndUid(Order order);
    
	int updatePayStatus(Order order1);

    int countOrder(@Param("order") Order order);
}