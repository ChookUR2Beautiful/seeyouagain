package com.xmn.designer.dao.customize;


import com.xmn.designer.entity.customize.OrderMaterialCustomizeAttr;

public interface OrderMaterialCustomizeAttrDao {
    int deleteByPrimaryKey(Long id);

    int insert(OrderMaterialCustomizeAttr record);

    int insertSelective(OrderMaterialCustomizeAttr record);

    OrderMaterialCustomizeAttr selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderMaterialCustomizeAttr record);

    int updateByPrimaryKey(OrderMaterialCustomizeAttr record);
}