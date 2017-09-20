package com.xmn.designer.dao.material;

import org.apache.ibatis.annotations.Param;

import com.xmn.designer.entity.material.MaterialAttrGroup;

public interface MaterialAttrGroupDao {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialAttrGroup record);

    int insertSelective(MaterialAttrGroup record);

    MaterialAttrGroup selectByPrimaryKey(Long id);
    
    MaterialAttrGroup findMaterialAttrGroup(@Param("materialId")Long materialId,@Param("materialAttrIds")String materialAttrIds,@Param("materialAttrVals")String materialAttrVals);

    int updateByPrimaryKeySelective(MaterialAttrGroup record);

    int updateByPrimaryKey(MaterialAttrGroup record);
}