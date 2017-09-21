package com.xmn.designer.dao.material;


import com.xmn.designer.entity.material.MaterialCategoryAttr;

import java.util.List;

public interface MaterialCategoryAttrDao {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialCategoryAttr record);

    int insertSelective(MaterialCategoryAttr record);

    MaterialCategoryAttr selectByPrimaryKey(Long id);
    
    List< MaterialCategoryAttr > findAll();

    int updateByPrimaryKeySelective(MaterialCategoryAttr record);

    int updateByPrimaryKey(MaterialCategoryAttr record);

    List<MaterialCategoryAttr> queryCustomStandard();

    List<MaterialCategoryAttr> selectByInIds(String customizeAttrIds);
}