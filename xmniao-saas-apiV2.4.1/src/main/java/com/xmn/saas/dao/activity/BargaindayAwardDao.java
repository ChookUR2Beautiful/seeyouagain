package com.xmn.saas.dao.activity;

import com.xmn.saas.entity.activity.BargaindayAward;

public interface BargaindayAwardDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BargaindayAward record);

    int insertSelective(BargaindayAward record);

    BargaindayAward selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BargaindayAward record);

    int updateByPrimaryKey(BargaindayAward record);
}