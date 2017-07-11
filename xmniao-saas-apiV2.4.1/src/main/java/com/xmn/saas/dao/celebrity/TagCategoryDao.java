package com.xmn.saas.dao.celebrity;

import com.xmn.saas.entity.celebrity.TagCategory;

public interface TagCategoryDao {
    int deleteByPrimaryKey(Long id);

    int insert(TagCategory record);

    int insertSelective(TagCategory record);

    TagCategory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TagCategory record);

    int updateByPrimaryKey(TagCategory record);
}