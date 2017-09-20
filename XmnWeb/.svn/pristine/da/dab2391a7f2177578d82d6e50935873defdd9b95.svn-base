package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;

import com.xmniao.xmn.core.live_anchor.entity.TLiveFloatAdvertisePosition;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TLiveFloatAdvertisePositionDao {
	@DataSource("slave")
	int deleteByPrimaryKey(Integer id);

	@DataSource("slave")
	int insert(TLiveFloatAdvertisePosition record);

	@DataSource("slave")
	int insertSelective(TLiveFloatAdvertisePosition record);

	@DataSource("slave")
	TLiveFloatAdvertisePosition selectByPrimaryKey(Integer id);

	@DataSource("slave")
	int updateByPrimaryKeySelective(TLiveFloatAdvertisePosition record);

	@DataSource("slave")
	int updateByPrimaryKey(TLiveFloatAdvertisePosition record);

	@DataSource("slave")
	public List<TLiveFloatAdvertisePosition> getLiveFloatAdvertisePositionList(
			Integer relationId);

	@DataSource("slave")
	public int deleteByRelationIds(Object[] objects);

	@DataSource("slave")
	public int deleteLiveFloatAdvertisePositionByRid(Integer id);

	@DataSource("slave")
	public int addBatchDetail(List<TLiveFloatAdvertisePosition> list);

}