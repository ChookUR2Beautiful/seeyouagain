package com.xmn.designer.dao.customize;


import com.xmn.designer.entity.customize.OrderMaterialCustomizeCarouselTemp;

public interface OrderMaterialCustomizeCarouselTempDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderMaterialCustomizeCarouselTemp record);

    int insertSelective(OrderMaterialCustomizeCarouselTemp record);

    OrderMaterialCustomizeCarouselTemp selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderMaterialCustomizeCarouselTemp record);

    int updateByPrimaryKey(OrderMaterialCustomizeCarouselTemp record);
}