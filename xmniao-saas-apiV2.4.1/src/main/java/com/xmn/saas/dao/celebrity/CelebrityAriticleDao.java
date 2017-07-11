package com.xmn.saas.dao.celebrity;

import com.xmn.saas.base.Page;
import com.xmn.saas.entity.celebrity.CelebrityAriticle;
import com.xmn.saas.entity.common.SellerAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CelebrityAriticleDao {
    int deleteByPrimaryKey(Long id);

    int insert(CelebrityAriticle record);

    int insertSelective(CelebrityAriticle record);

    CelebrityAriticle selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CelebrityAriticle record);

    int updateByPrimaryKey(CelebrityAriticle record);

    CelebrityAriticle selectByMaxViewAriticel(Long reviewerId);

    List<CelebrityAriticle> querySelective(@Param("record") CelebrityAriticle record, @Param("page") Page page);

    Integer countAriticelBySellerId(SellerAccount sellerAccount);

    String selectHtmlContentById(Long articleId);
}