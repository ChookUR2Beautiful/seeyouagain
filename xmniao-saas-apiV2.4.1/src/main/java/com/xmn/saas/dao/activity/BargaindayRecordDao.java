package com.xmn.saas.dao.activity;

import com.xmn.saas.entity.activity.BargaindayRecord;

public interface BargaindayRecordDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BargaindayRecord record);

    int insertSelective(BargaindayRecord record);

    BargaindayRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BargaindayRecord record);

    int updateByPrimaryKey(BargaindayRecord record);
}