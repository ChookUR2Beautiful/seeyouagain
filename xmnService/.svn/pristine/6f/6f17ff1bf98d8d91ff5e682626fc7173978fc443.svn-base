package com.xmniao.xmn.core.xmer.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xmniao.xmn.core.util.dataSource.DataSource;


@Repository
public interface ActivityDao {

	@DataSource("joint")
	Map<Object, Object> activityInfo(Map<Object,Object> map);

	//报名
	@DataSource("joint")
	void addAgencyActivityEntry(Map<Object, Object> param);

	//修改参加人数
	@DataSource("joint")
	void addActivityCount(Integer id);

	//查询是否已经参加过
	@DataSource("joint")
	int queryActivityEntryCount(Map<Object,Object> idRquest);
	
	//更多活动
	@DataSource("joint")
	List<Map<Object, Object>> moreActivity(Map<Object,Object> map);
	
	

}
