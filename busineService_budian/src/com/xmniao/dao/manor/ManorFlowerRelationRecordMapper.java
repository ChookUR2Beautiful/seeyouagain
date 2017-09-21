package com.xmniao.dao.manor;

import com.xmniao.domain.manor.ManorFlowerRelationRecord;

public interface ManorFlowerRelationRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ManorFlowerRelationRecord record);

    int insertSelective(ManorFlowerRelationRecord record);

    ManorFlowerRelationRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ManorFlowerRelationRecord record);

    int updateByPrimaryKey(ManorFlowerRelationRecord record);
}