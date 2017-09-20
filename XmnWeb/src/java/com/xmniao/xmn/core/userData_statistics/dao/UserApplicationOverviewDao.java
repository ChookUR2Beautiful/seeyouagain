package com.xmniao.xmn.core.userData_statistics.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.userData_statistics.entity.TUserApplicationOverview;
import com.xmniao.xmn.core.util.proxy.annotation.DataSource;

@Repository
public interface UserApplicationOverviewDao {
	
	/**
	 * 列表
	 * @return
	 */
	@DataSource("slave")
	List<TUserApplicationOverview> getList(TUserApplicationOverview applicationOverview);
}
