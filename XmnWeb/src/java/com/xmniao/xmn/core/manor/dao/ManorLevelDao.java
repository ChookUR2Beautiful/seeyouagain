package com.xmniao.xmn.core.manor.dao;

import java.util.List;

import com.xmniao.xmn.core.manor.entity.ManorLevel;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

public interface ManorLevelDao {

	@DataSource("burs")
	public void add(ManorLevel t);

	@DataSource("burs")
	public Long count(ManorLevel t);

	@DataSource("burs")
	public ManorLevel getObject(Long id);
		

	@DataSource("burs")
	public Integer update(ManorLevel t);

	@DataSource("burs")
	public List<ManorLevel> getList(ManorLevel t);
	
	@DataSource("burs")
	public ManorLevel getMaxObject();
}