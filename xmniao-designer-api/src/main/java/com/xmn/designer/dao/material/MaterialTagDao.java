package com.xmn.designer.dao.material;

import java.util.List;

import com.xmn.designer.entity.material.MaterialTag;

public interface MaterialTagDao {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialTag record);

    int insertSelective(MaterialTag record);

    MaterialTag selectByPrimaryKey(Long id);
    
    List<MaterialTag> findAll();

    int updateByPrimaryKeySelective(MaterialTag record);

    int updateByPrimaryKey(MaterialTag record);
}