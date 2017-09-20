package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveDelicious;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TLiveDeliciousDao extends BaseDao<TLiveDelicious> {
	@DataSource("slave")
	int deleteByPrimaryKey(Integer id);

	@DataSource("slave")
	int insert(TLiveDelicious record);

	@DataSource("slave")
	int insertSelective(TLiveDelicious record);

	@DataSource("slave")
	TLiveDelicious selectByPrimaryKey(Integer id);

	@DataSource("slave")
	int updateByPrimaryKeySelective(TLiveDelicious record);

	@DataSource("slave")
	int updateByPrimaryKey(TLiveDelicious record);

	@DataSource("slave")
	public List<TLiveDelicious> getLiveDeliciousList(TLiveDelicious record);

	@DataSource("slave")
	public Long countLiveDelicious(TLiveDelicious record);

}