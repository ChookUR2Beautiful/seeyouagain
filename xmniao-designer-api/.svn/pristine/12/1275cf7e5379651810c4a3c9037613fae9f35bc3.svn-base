package com.xmn.designer.dao.material;

import java.util.List;

import com.xmn.designer.entity.material.MaterialCategoryTagRelation;

public interface MaterialCategoryTagRelationDao {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialCategoryTagRelation record);

    int insertSelective(MaterialCategoryTagRelation record);

    MaterialCategoryTagRelation selectByPrimaryKey(Long id);
    
    List<MaterialCategoryTagRelation> selectByCategoryId(Long id);
    
    List<MaterialCategoryTagRelation> selectByMaterialTagId(Long id);

    int updateByPrimaryKeySelective(MaterialCategoryTagRelation record);

    int updateByPrimaryKey(MaterialCategoryTagRelation record);
}