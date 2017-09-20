package com.xmniao.xmn.core.reward_dividends.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationProject;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


public interface TVerExcitationProjectDao extends BaseDao<TVerExcitationProject>{
	@DataSource(value = "slave")
    int deleteByPrimaryKey(Integer id);

	@DataSource(value = "slave")
    Integer insert(TVerExcitationProject record);

	@DataSource(value = "slave")
    int insertSelective(TVerExcitationProject record);

	@DataSource(value = "slave")
    TVerExcitationProject selectByPrimaryKey(Integer id);

	@DataSource(value = "slave")
    int updateByPrimaryKeySelective(TVerExcitationProject record);

	@DataSource(value = "slave")
    int updateByPrimaryKey(TVerExcitationProject record);
    
	@DataSource(value = "slave")
    List<TVerExcitationProject> getExcitationProjectByProjectName(String projectName);
    
	@DataSource(value = "slave")
    public int deleteByIds(String[] ids);
    
}