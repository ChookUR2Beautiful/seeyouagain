package com.xmn.saas.dao.celebrity;

import com.xmn.saas.entity.celebrity.CelebrityAriticleCarousel;

public interface CelebrityAriticleCarouselDao {
    int deleteByPrimaryKey(Long id);

    int insert(CelebrityAriticleCarousel record);

    int insertSelective(CelebrityAriticleCarousel record);

    CelebrityAriticleCarousel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CelebrityAriticleCarousel record);

    int updateByPrimaryKey(CelebrityAriticleCarousel record);
}