package com.xmn.designer.dao.material;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xmn.designer.entity.material.MaterialCategory;

public interface MaterialCategoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialCategory record);

    int insertSelective(MaterialCategory record);

    MaterialCategory selectByPrimaryKey(Long id);
    
    List<MaterialCategory> findAll();
    
    List<MaterialCategory> findByKey(@Param("key")String key);

    int updateByPrimaryKeySelective(MaterialCategory record);

    int updateByPrimaryKey(MaterialCategory record);
}