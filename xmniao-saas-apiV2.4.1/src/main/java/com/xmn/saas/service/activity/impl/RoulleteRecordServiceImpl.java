package com.xmn.saas.service.activity.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmn.saas.dao.activity.RoulleteRecordDao;
import com.xmn.saas.entity.activity.RoulleteRecord;
import com.xmn.saas.service.activity.RoulleteRecordService;
@Service
public class RoulleteRecordServiceImpl implements RoulleteRecordService {
	@Autowired
	private RoulleteRecordDao recordDao;

	@Override
	public List<RoulleteRecord> list(Integer activityId, Integer sellerId, Integer pageSize, Integer pageIndex) {
		return recordDao.list(activityId,sellerId,pageSize,pageIndex);
	}

	@Override
	public RoulleteRecord detail(Integer recordId, Integer activityId) {
		RoulleteRecord roulleteRecord =  recordDao.detail(recordId,activityId);
		Integer awardType=roulleteRecord.getAwardType();
		if(awardType==3||awardType==4){	//优惠券
		  return	recordDao.couponDetail(recordId,activityId);
		}
		return roulleteRecord;
	}

	@Override
	public Integer countRecord(Integer id) {
		return recordDao.countRecord(id);
	}
}
