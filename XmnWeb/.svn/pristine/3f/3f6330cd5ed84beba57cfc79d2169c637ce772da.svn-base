package com.xmniao.xmn.core.reward_dividends.dao;

import java.util.List;

import com.xmniao.xmn.core.reward_dividends.entity.TVerExcitationDetail;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface TVerExcitationDetailDao {
	@DataSource("slave")
	int deleteByPrimaryKey(Integer did);

	@DataSource("slave")
	int insert(TVerExcitationDetail record);

	@DataSource("slave")
	int insertSelective(TVerExcitationDetail record);

	@DataSource("slave")
	TVerExcitationDetail selectByPrimaryKey(Integer did);

	@DataSource("slave")
	int updateByPrimaryKeySelective(TVerExcitationDetail record);

	@DataSource("slave")
	int updateByPrimaryKey(TVerExcitationDetail record);

	@DataSource("slave")
	public int deleteByPid(Integer pid);

	@DataSource("slave")
	public int deleteByPids(String[] ids);

	@DataSource("slave")
	public int insertVerExcitationDetailBatch(List<TVerExcitationDetail> list);

	@DataSource("slave")
	public List<TVerExcitationDetail> getVerExcitationDetail();
}