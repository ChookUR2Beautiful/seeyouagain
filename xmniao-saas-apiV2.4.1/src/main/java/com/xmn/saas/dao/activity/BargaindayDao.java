package com.xmn.saas.dao.activity;

import com.xmn.saas.entity.activity.Bargainday;

public interface BargaindayDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Bargainday record);

    int insertSelective(Bargainday record);

    Bargainday selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bargainday record);

    int updateByPrimaryKey(Bargainday record);
}