package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveEntertainment;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TLiveEntertainmentDao extends BaseDao<TLiveEntertainment> {
	@DataSource("slave")
	int deleteByPrimaryKey(Integer id);

	@DataSource("slave")
	int insert(TLiveEntertainment record);

	@DataSource("slave")
	int insertSelective(TLiveEntertainment record);

	@DataSource("slave")
	TLiveEntertainment selectByPrimaryKey(Integer id);

	@DataSource("slave")
	int updateByPrimaryKeySelective(TLiveEntertainment record);

	@DataSource("slave")
	int updateByPrimaryKey(TLiveEntertainment record);

	@DataSource("slave")
	public List<TLiveEntertainment> getLiveEntertainmentList(TLiveEntertainment record);

	@DataSource("slave")
	public Long countLiveEntertainment(TLiveEntertainment record);
}