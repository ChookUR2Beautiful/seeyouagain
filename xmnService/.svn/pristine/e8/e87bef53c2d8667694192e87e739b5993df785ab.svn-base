package com.xmniao.xmn.core.catehome.dao;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.catehome.entity.SpecilTopicConfig;
import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface SpecilTopicConfigDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    int insert(SpecilTopicConfig record);

	@DataSource("joint")
    int insertSelective(SpecilTopicConfig record);

	@DataSource("joint")
    SpecilTopicConfig selectByPrimaryKey(Integer id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(SpecilTopicConfig record);

	@DataSource("joint")
    int updateByPrimaryKey(SpecilTopicConfig record);
}