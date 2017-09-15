package com.xmniao.xmn.core.catehome.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.catehome.entity.SpecilTopicRelation;
import com.xmniao.xmn.core.util.dataSource.DataSource;

@Repository
public interface SpecilTopicRelationDao {
	
	@DataSource("joint")
    int deleteByPrimaryKey(Integer id);

	@DataSource("joint")
    int insert(SpecilTopicRelation record);

	@DataSource("joint")
    int insertSelective(SpecilTopicRelation record);

	@DataSource("joint")
    SpecilTopicRelation selectByPrimaryKey(Integer id);

	@DataSource("joint")
    int updateByPrimaryKeySelective(SpecilTopicRelation record);

	@DataSource("joint")
    int updateByPrimaryKey(SpecilTopicRelation record);
	
	/**
	 * 查询所有与专题相关的信息
	 * @param fid
	 * @return
	 */
	@DataSource("joint")
	List<SpecilTopicRelation> findAllByFid(@Param("fid")Integer fid,@Param("type")Integer type);
}