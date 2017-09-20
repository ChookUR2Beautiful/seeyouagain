package com.xmniao.xmn.core.business_statistics.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.business_statistics.entity.TApplicationOverview;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;


@Repository
public interface ApplicationOverviewDao {
	@DataSource("slave")
	public List<TApplicationOverview> getDataList (Map<String, String> map);
	
	
}
