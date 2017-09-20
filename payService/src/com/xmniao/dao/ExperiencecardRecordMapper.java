package com.xmniao.dao;

import com.xmniao.entity.ExperiencecardRecord;

public interface ExperiencecardRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExperiencecardRecord record);

    int insertSelective(ExperiencecardRecord record);

    ExperiencecardRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExperiencecardRecord record);

    int updateByPrimaryKey(ExperiencecardRecord record);

    /**
     * 统计订单数量
     * @param exCardRecordRequest
     * @return
     */
    int countRecord(ExperiencecardRecord exCardRecordRequest);
}