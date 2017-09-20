package com.xmn.designer.dao.material;

import java.util.List;

import com.xmn.designer.entity.material.MaterialCarousel;

public interface MaterialCarouselDao {
    int deleteByPrimaryKey(Long id);

    int insert(MaterialCarousel record);

    int insertSelective(MaterialCarousel record);

    MaterialCarousel selectByPrimaryKey(Long id);
    
    List<MaterialCarousel> selectByMaterialId(Long materialId);

    int updateByPrimaryKeySelective(MaterialCarousel record);

    int updateByPrimaryKey(MaterialCarousel record);
}