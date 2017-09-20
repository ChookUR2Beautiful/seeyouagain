package com.xmn.designer.dao.material;

import com.xmn.designer.entity.material.OrderMaterialCarouselSource;

public interface OrderMaterialCarouselSourceDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderMaterialCarouselSource record);

    int insertSelective(OrderMaterialCarouselSource record);

    OrderMaterialCarouselSource selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderMaterialCarouselSource record);

    int updateByPrimaryKey(OrderMaterialCarouselSource record);
}