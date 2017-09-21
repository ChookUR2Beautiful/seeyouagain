package com.xmniao.dao.manor;


import com.xmniao.domain.manor.ManorConfig;
import org.apache.ibatis.annotations.Param;

public interface ManorConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int insert(ManorConfig record);

    int insertSelective(ManorConfig record);

    ManorConfig selectByPrimaryKey(Integer configId);

    int updateByPrimaryKeySelective(ManorConfig record);

    int updateByPrimaryKey(ManorConfig record);

    ManorConfig selectByType(@Param("type") int type);
}