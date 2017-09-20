package com.xmniao.xmn.core.live_anchor.dao;

import java.util.List;
import java.util.Map;

import com.xmniao.xmn.core.base.BaseDao;
import com.xmniao.xmn.core.live_anchor.entity.TLiveFloatAdvertise;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TLiveFloatAdvertiseDao extends BaseDao<TLiveFloatAdvertise> {
	@DataSource("slave")
	int deleteByPrimaryKey(Integer id);

	@DataSource("slave")
	int insert(TLiveFloatAdvertise record);

	@DataSource("slave")
	int insertSelective(TLiveFloatAdvertise record);

	@DataSource("slave")
	TLiveFloatAdvertise selectByPrimaryKey(Integer id);

	@DataSource("slave")
	int updateByPrimaryKeySelective(TLiveFloatAdvertise record);

	@DataSource("slave")
	int updateByPrimaryKey(TLiveFloatAdvertise record);

	@DataSource("slave")
	public List<TLiveFloatAdvertise> getLiveFloatAdvertiseList(TLiveFloatAdvertise record);

	@DataSource("slave")
	public Long countLiveFloatAdvertise(TLiveFloatAdvertise record);
	
	@DataSource("slave")
	public int updateOptionStatus(Map<String, Object> map);
}