package com.xmn.saas.dao.celebrity;

import com.xmn.saas.entity.celebrity.CelebrityCarousel;

public interface CelebrityCarouselDao {
    int deleteByPrimaryKey(Long id);

    int insert(CelebrityCarousel record);

    int insertSelective(CelebrityCarousel record);

    CelebrityCarousel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CelebrityCarousel record);

    int updateByPrimaryKey(CelebrityCarousel record);
}