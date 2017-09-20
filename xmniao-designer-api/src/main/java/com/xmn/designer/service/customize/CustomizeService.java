package com.xmn.designer.service.customize;

import com.xmn.designer.entity.account.User;
import com.xmn.designer.entity.customize.OrderMaterialCustomize;
import com.xmn.designer.entity.material.MaterialCategoryAttr;
import com.xmn.designer.entity.order.Order;
import com.xmn.designer.exception.CustomException;

import java.util.List;
import java.util.Map;

/**
 * create 2016/11/15
 * 定制订单服务类
 * @author yangQiang
 */

public interface CustomizeService {

    List<MaterialCategoryAttr> getCustomStandard();

    Map<String,Object> submitCustomize(User designerUser, OrderMaterialCustomize customizeOrder, Order order) throws CustomException;

    /**
     * 确认设计
     */
    void confirmDesign(String orderNo);
}
