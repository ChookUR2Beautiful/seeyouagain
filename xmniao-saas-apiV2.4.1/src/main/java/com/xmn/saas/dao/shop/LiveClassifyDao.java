package com.xmn.saas.dao.shop;

import com.xmn.saas.entity.shop.LiveClassify;

import java.util.List;

public interface LiveClassifyDao {
    int deleteByPrimaryKey(Integer id);

    int insert(LiveClassify record);

    int insertSelective(LiveClassify record);

    LiveClassify selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LiveClassify record);

    int updateByPrimaryKey(LiveClassify record);

    List<LiveClassify> selectSellerTagClass();
}