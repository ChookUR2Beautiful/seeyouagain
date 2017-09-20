package com.xmniao.xmn.core.business_statistics.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmniao.xmn.core.business_statistics.dao.ApplicationOverviewDao;
import com.xmniao.xmn.core.business_statistics.entity.TApplicationOverview;
import com.xmniao.xmn.core.util.DateHelper;

@Service
public class ApplicationOverviewService  {
	@Autowired
	private ApplicationOverviewDao applicationOverviewDao;

	/*
	 * 应用概括统计数据
	 */
	public List<TApplicationOverview> getDataList() {
		Map<String, String> dateMap = new HashMap<String, String>(1);
		dateMap.put("now",DateHelper.getOtherDayByDay(1));
		dateMap.put("beforeYesterday", DateHelper.getOtherDayByDay(3));
		List<TApplicationOverview> list =applicationOverviewDao.getDataList(dateMap);
		return list;
		
	}


}
