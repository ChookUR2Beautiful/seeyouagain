package com.xmniao.xmn.core.live_anchor.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xmniao.xmn.core.live_anchor.entity.TLiveLevel;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


public interface TLiveLevelDao {
	@DataSource("burs")
    int deleteByPrimaryKey(Integer id);
	
    @DataSource("burs")
    int insertSelective(TLiveLevel record);
    
    @DataSource("burs")
    TLiveLevel selectByPrimaryKey(Integer id);
    
    @DataSource("burs")
    int updateByPrimaryKeySelective(TLiveLevel record);
	
	@DataSource("burs")
	public List<TLiveLevel> getLiveLevelList(TLiveLevel record);
	
	@DataSource("burs")
	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
	public Long countLiveLevel(TLiveLevel record);
	
	
	/**
	 * 校验唯一性
	 * @param account
	 * @return
	 */
	@DataSource("burs")
	public long checkLevelName(String levelName);
	
	
    @DataSource("burs")
    TLiveLevel getLiveLevelDetail(@Param("levelName") String levelName);
}

