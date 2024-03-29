package com.xmn.designer.service.customize.impl;

import com.google.gson.Gson;
import com.xmn.designer.constants.DesignerConsts;
import com.xmn.designer.dao.customize.OrderMaterialCustomizeCarouselDao;
import com.xmn.designer.dao.customize.OrderMaterialCustomizeDao;
import com.xmn.designer.dao.material.MaterialCategoryAttrDao;
import com.xmn.designer.dao.material.MaterialCategoryAttrValDao;
import com.xmn.designer.entity.account.User;
import com.xmn.designer.entity.customize.OrderMaterialCustomize;
import com.xmn.designer.entity.customize.OrderMaterialCustomizeCarousel;
import com.xmn.designer.entity.material.MaterialCategoryAttr;
import com.xmn.designer.entity.material.MaterialCategoryAttrVal;
import com.xmn.designer.entity.order.Order;
import com.xmn.designer.exception.CustomException;
import com.xmn.designer.service.customize.CustomizeService;
import com.xmn.designer.service.image.ImageService;
import com.xmn.designer.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * create 2016/11/15
 * 定制订单服务类
 * @author yangQiang
 */
@Service
class CustomizeServiceImpl implements CustomizeService {

    @Autowired
    private MaterialCategoryAttrDao materialCategoryAttrdao;

    @Autowired
    private MaterialCategoryAttrValDao materialCategoryAttrValDao;

    @Autowired
    private OrderMaterialCustomizeDao orderMaterialCustomizeDao;

    @Autowired
    private OrderMaterialCustomizeCarouselDao orderMaterialCustomizeCarouselDao;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ImageService imageService;

    @Override
    public List<MaterialCategoryAttr> getCustomStandard() {
        // 查询定制规格列表
        List<MaterialCategoryAttr> standardList  =  materialCategoryAttrdao.queryCustomStandard();

        // 查询每种定制规格的规格明细, 并填充到 规格列表
        for (MaterialCategoryAttr standard : standardList) {
            List<MaterialCategoryAttrVal> valList = materialCategoryAttrValDao.selectByCategoryAttrId(standard.getId());
            List<String> vals = new ArrayList<>();
            for (MaterialCategoryAttrVal val : valList) {
                vals.add(val.getVal());
            }
            standard.setVals(vals);

            // 是否自定义
            if (DesignerConsts.MATERIAL_CATEGORY_IS_CUSTIMIZE.equals(standard.getIsCustomizable())) {
                standard.setCustomizable(true);
            }else {
                standard.setCustomizable(false);
            }

            // 是否多选
            if (DesignerConsts.MATERIAL_CATEGORY_IS_MULTIPLE.equals(standard.getIsMultiple())) {
                standard.setMultipleChoice(true);
            }else {
                standard.setMultipleChoice(false);
            }
        }

        // 返回规格列表
        return standardList;
    }

    @Transactional
    @Override
    public Map<String, Object> submitCustomize(User designerUser, OrderMaterialCustomize customizeOrder, Order order) throws CustomException {
        Gson gson = new Gson();
        List<String> imageList = gson.fromJson(customizeOrder.getImageJson(), List.class);

        order.setUid(designerUser.getId());
        order.setType(DesignerConsts.ORDER_TYPE_CUSTOMIZE);
        order.setMainImage(imageList.get(0)!=null ? imageList.get(0) : null);

        // 封装定制订单数据
        customizeOrder.setTitle("定制设计");
        customizeOrder.setState("001");
        customizeOrder.setCreater(designerUser.getId()+"");
        customizeOrder.setUpdateTime(new Date());

        // 调用订单服务,创建订单
        OrderMaterialCustomize orderMaterialCustomize = orderService.placeOrderCustomize(order, customizeOrder);

        for (String image : imageList) {
            OrderMaterialCustomizeCarousel customizeCarousel = new OrderMaterialCustomizeCarousel();
            customizeCarousel.setPicUrl(image);
            customizeCarousel.setCustomizeMaterialId(orderMaterialCustomize.getId());
            customizeCarousel.setCreateTime(new Date());
            customizeCarousel.setCreater(designerUser.getId()+"");
            orderMaterialCustomizeCarouselDao.insertSelective(customizeCarousel);
            //TODO 标记图片已被使用, 否者图片会被清除
            imageService.useImage(image);
        }


        // 查询订单相册
        List<OrderMaterialCustomizeCarousel> carouselList = orderMaterialCustomizeCarouselDao
                .selectByCustomizeMaterialId(customizeOrder.getId());
        List<String> images = new ArrayList<>();
        for (OrderMaterialCustomizeCarousel carousel : carouselList) {
            images.add(carousel.getPicUrl());
        }

        // 封装响应参数
        HashMap<String, Object> result = new HashMap<>();
        result.put("images",imageList);
        result.put("orderNo",orderMaterialCustomize.getOrderNo());

        result.put("specList",orderService.setCustomizeHigh(customizeOrder.getSpecJson()));
        result.put("remark",customizeOrder.getRemark());
        result.put("finishTime",customizeOrder.getFinishTime());

        result.put("mobile",order.getMobile());
        result.put("consignee",order.getConsignee());

        return result;
    }

    @Override
    @Transactional
    public void confirmDesign(String orderNo) {

        OrderMaterialCustomize orderMaterialCustomize = orderMaterialCustomizeDao.selectByOrderNo(orderNo);

        OrderMaterialCustomize record = new OrderMaterialCustomize();
        record.setId(orderMaterialCustomize.getId());
        record.setState("004");
        int count = orderMaterialCustomizeDao.updateByPrimaryKeySelective(record);
        if (count < 1){
            throw new CustomException("确认设计失败!");
        }
    }


}
