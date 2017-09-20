package com.xmn.designer.dao.material;

import java.util.List;

import com.xmn.designer.entity.material.MaterialAttrVal;

public interface MaterialAttrValDao {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialAttrVal record);

    int insertSelective(MaterialAttrVal record);

    MaterialAttrVal selectByPrimaryKey(Long id);
    
    List<MaterialAttrVal> selectByMaterialAttrId(Long materialAttrId);

    int updateByPrimaryKeySelective(MaterialAttrVal record);

    int updateByPrimaryKey(MaterialAttrVal record);
}